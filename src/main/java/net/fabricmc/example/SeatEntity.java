package net.fabricmc.example;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import eu.pb4.polymer.api.entity.PolymerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntityAttributesS2CPacket;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import static net.minecraft.entity.decoration.ArmorStandEntity.ARMOR_STAND_FLAGS;

public class SeatEntity extends Entity implements PolymerEntity {

    private static final EntityAttributeInstance MAX_HEALTH_NULL = new EntityAttributeInstance(
            EntityAttributes.GENERIC_MAX_HEALTH, discard -> {
            });
    private static final Collection<EntityAttributeInstance> MAX_HEALTH_NULL_SINGLE = (Collection<EntityAttributeInstance>) Collections
            .singleton(MAX_HEALTH_NULL);

    static {
        MAX_HEALTH_NULL.setBaseValue(0D);
    }

    /** Initialises the seat to be invisible and to have no gravity. */
    public SeatEntity(EntityType<? extends SeatEntity> type, World world) {
        super(type, world);
        this.setInvisible(true);
        this.setNoGravity(true);
    }

    public SeatEntity(World world, double x, double y, double z) {
        this(ItemRegister.SEAT_ENTITY, world);
        this.setPosition(x, y, z);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
    }

    /**
     * We're an armor stand now. Used to set an immovable & invisible 0x0x0 entity
     * at a block.
     */
    @Override
    public EntityType<?> getPolymerEntityType() {
        return EntityType.ARMOR_STAND;
    }

    /**
     * Tells the client that we're a marker armor stand, and that we have no health.
     */
    @Override
    public void modifyTrackedData(List<DataTracker.Entry<?>> data, ServerPlayerEntity player) {
        data.add(new DataTracker.Entry<>(ARMOR_STAND_FLAGS, (byte) 16));
        // This must be manually sent as there's no other mechanism we can use to send
        // this.
        player.networkHandler.sendPacket(new EntityAttributesS2CPacket(getId(), MAX_HEALTH_NULL_SINGLE));
    }

    @Override
    protected void initDataTracker() {
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }

    /** Only save if being ridden. */
    @Override
    public boolean shouldSave() {
        var reason = getRemovalReason();
        if (reason != null && !reason.shouldSave()) {
            return false;
        }
        return hasPassengers();
    }

    /** Discard self when passengers are dismounted. */
    @Override
    public void removeAllPassengers() {
        super.removeAllPassengers();
        discard();
    }

    /** Discard self when the passenger is dismounted. */
    @Override
    protected void removePassenger(Entity passenger) {
        super.removePassenger(passenger);
        discard();
    }

    /**
     * Automatic cleanup and syncing yaw with the passenger.
     */
    @Override
    public void tick() {
        // There's absolutely no reason for this entity to even move.
        super.tick();
        var passenger = getFirstPassenger();
        if (passenger == null || (((world.getTime() + hashCode()) & 31) == 0 && getBlockStateAtPos().isAir())) {
            discard();
            return;
        }
        setYaw(passenger.getYaw());
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

}
