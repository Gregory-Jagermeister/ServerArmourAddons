package net.fabricmc.example.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class ExampleMixin extends Entity {
	public ExampleMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	public LivingEntity thisEnt = ((LivingEntity) (Object) this);
	@Shadow
	@Final
	private DefaultedList<ItemStack> syncedArmorStacks;

	@Shadow
	@Final
	protected boolean jumping;

	@Inject(at = @At("HEAD"), method = "baseTick()V")
	private void init(CallbackInfo info) {

		ItemStack helmetStack = syncedArmorStacks.get(3);
		ItemStack chestplateStack = syncedArmorStacks.get(2);
		ItemStack leggingStack = syncedArmorStacks.get(1);
		ItemStack bootsStack = syncedArmorStacks.get(0);

		// Night Vision Googles Logic
		if (helmetStack.getItem().toString().equals("night_vision_goggles")
				&& thisEnt.hasStackEquipped(EquipmentSlot.HEAD)) {
			StatusEffectInstance effect = new StatusEffectInstance(StatusEffects.NIGHT_VISION, 210, 1, false,
					false,
					false);
			effect.setPermanent(true);
			thisEnt.addStatusEffect(effect, thisEnt);

		}

		// Fireproof Armor Logic.
		if (helmetStack.getItem().toString().equals("fireproof_helmet")
				&& chestplateStack.getItem().toString().equals("fireproof_chestplate")
				&& leggingStack.getItem().toString().equals("fireproof_leggings")
				&& bootsStack.getItem().toString().equals("fireproof_boots")) {
			ServerWorld world = (ServerWorld) thisEnt.getWorld();
			world.spawnParticles(ParticleTypes.FLAME,
					thisEnt.getX() + (this.random.nextDouble() - 0.5) * (double) thisEnt.getWidth(),
					thisEnt.getY() + (this.random.nextDouble() - 0.30) * (double) thisEnt
							.getHeight(),
					thisEnt.getZ() + (this.random.nextDouble() - 0.5) * (double) thisEnt
							.getWidth(),
					1, 0.3,
					0.15, 0.3, -0.0065);
			StatusEffectInstance effect = new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 10, 0, false,
					false,
					false);
			effect.setPermanent(true);
			thisEnt.addStatusEffect(effect, thisEnt);
		}

		// Miners Armor
		if (helmetStack.getItem().toString().startsWith("miners")
				|| chestplateStack.getItem().toString().startsWith("miners")
				|| leggingStack.getItem().toString().startsWith("miners")
				|| bootsStack.getItem().toString().startsWith("miners")) {
			int hasteCount = -1;
			for (int i = 0; i < 4; i++) {
				ItemStack armorItem = syncedArmorStacks.get(i);
				if (armorItem.getItem().toString().startsWith("miners")) {
					hasteCount++;
				}
			}
			StatusEffectInstance effect = new StatusEffectInstance(StatusEffects.HASTE, 5, hasteCount / 3, false,
					false,
					false);
			effect.setPermanent(true);
			thisEnt.addStatusEffect(effect, thisEnt);
		}
	}
}
