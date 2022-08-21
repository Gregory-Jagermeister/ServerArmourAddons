package net.fabricmc.example;

import eu.pb4.polymer.ext.blocks.api.BlockModelType;
import eu.pb4.polymer.ext.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.ext.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.ext.blocks.api.PolymerTexturedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.util.math.Direction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Seat extends Block implements PolymerTexturedBlock {

    private final BlockState polymerBlockState;
    public static final Logger LOGGER = LogManager.getLogger("modid");
    private Entity SEAT_ENTITY;

    public Seat(Settings settings, BlockModelType type, String modelID) {
        super(settings);
        this.polymerBlockState = PolymerBlockResourceUtils.requestBlock(type,
                PolymerBlockModel.of(new Identifier("polymertest", modelID)));
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {

        super.onPlaced(world, pos, state, placer, itemStack);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
            PlayerEntity player, Hand hand,
            BlockHitResult hit) {
        if (!world.isClient && hand == Hand.MAIN_HAND && player.getStackInHand(hand).isEmpty()
                && hit.getSide() != Direction.DOWN) {
            SEAT_ENTITY = new SeatEntity(world, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);
            LOGGER.info(SEAT_ENTITY);
            if (!world.spawnEntity(SEAT_ENTITY)) {
                throw new AssertionError(SEAT_ENTITY + " invaild");
            }
            player.startRiding(SEAT_ENTITY);
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    @Override
    public Block getPolymerBlock(BlockState state) {
        return this.polymerBlockState.getBlock();
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state) {
        return this.polymerBlockState;
    }

}
