package net.fabricmc.example;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class SaberMaterial implements ToolMaterial {

    public static final SaberMaterial INSTANCE = new SaberMaterial();

    @Override
    public float getAttackDamage() {
        return 8.5F;
    }

    @Override
    public int getDurability() {

        return 4062;
    }

    @Override
    public int getEnchantability() {

        return 15;
    }

    @Override
    public int getMiningLevel() {

        return 0;
    }

    @Override
    public float getMiningSpeedMultiplier() {

        return 5.0F;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.NETHER_STAR);
    }

}
