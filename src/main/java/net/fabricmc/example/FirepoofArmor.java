package net.fabricmc.example;

import org.jetbrains.annotations.Nullable;

import eu.pb4.polymer.api.item.PolymerItem;
import eu.pb4.polymer.api.resourcepack.PolymerArmorModel;
import eu.pb4.polymer.api.resourcepack.PolymerModelData;
import eu.pb4.polymer.api.resourcepack.PolymerRPUtils;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class FirepoofArmor extends ArmorItem implements PolymerItem {

    private final PolymerModelData itemModel;
    private final PolymerArmorModel armorModel;
    private final Item itemDefault;

    public FirepoofArmor(EquipmentSlot slot, Identifier model, Identifier armor) {

        super(ArmorMaterials.NETHERITE, slot, new Settings().fireproof().group(ItemGroup.COMBAT));
        this.itemDefault = getItemFor(slot, false);
        this.itemModel = PolymerRPUtils.requestModel(getItemFor(slot, true), model);
        this.armorModel = PolymerRPUtils.requestArmor(armor);
    }

    private Item getItemFor(EquipmentSlot slot, boolean b) {
        if (b) {
            return switch (slot) {
                case HEAD -> Items.LEATHER_HELMET;
                case CHEST -> Items.LEATHER_CHESTPLATE;
                case LEGS -> Items.LEATHER_LEGGINGS;
                case FEET -> Items.LEATHER_BOOTS;
                default -> Items.STONE;
            };
        } else {
            return switch (slot) {
                case HEAD -> Items.IRON_HELMET;
                case CHEST -> Items.IRON_CHESTPLATE;
                case LEGS -> Items.IRON_LEGGINGS;
                case FEET -> Items.IRON_BOOTS;
                default -> Items.STONE;
            };
        }
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return PolymerRPUtils.hasPack(player) ? this.itemModel.item() : this.itemDefault;
    }

    @Override
    public int getPolymerArmorColor(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return PolymerRPUtils.hasPack(player) ? this.armorModel.value() : -1;
    }

    @Override
    public int getPolymerCustomModelData(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return PolymerRPUtils.hasPack(player) ? this.itemModel.value() : -1;
    }

}
