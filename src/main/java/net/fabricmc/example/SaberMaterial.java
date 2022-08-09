package net.fabricmc.example;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class SaberMaterial implements ToolMaterial {

    public static final SaberMaterial INSTANCE = new SaberMaterial();

    @Override
    public float getAttackDamage() {
        // TODO Auto-generated method stub
        return 8.5F;
    }

    @Override
    public int getDurability() {
        // TODO Auto-generated method stub
        return 4062;
    }

    @Override
    public int getEnchantability() {
        // TODO Auto-generated method stub
        return 15;
    }

    @Override
    public int getMiningLevel() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        // TODO Auto-generated method stub
        return 5.0F;
    }

    @Override
    public Ingredient getRepairIngredient() {
        // TODO Auto-generated method stub
        return Ingredient.ofItems(Items.NETHER_STAR);
    }

}
