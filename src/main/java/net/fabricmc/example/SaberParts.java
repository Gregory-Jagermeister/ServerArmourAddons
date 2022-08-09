package net.fabricmc.example;

import org.jetbrains.annotations.Nullable;

import eu.pb4.polymer.api.item.PolymerItem;
import eu.pb4.polymer.api.resourcepack.PolymerModelData;
import eu.pb4.polymer.api.resourcepack.PolymerRPUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class SaberParts extends Item implements PolymerItem {

    private final PolymerModelData itemModel;

    public SaberParts(Settings settings, Identifier model) {
        super(settings);
        this.itemModel = PolymerRPUtils.requestModel(Items.STICK, model);
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return PolymerRPUtils.hasPack(player) ? this.itemModel.item() : Items.STICK;
    }

    @Override
    public int getPolymerCustomModelData(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return PolymerRPUtils.hasPack(player) ? this.itemModel.value() : -1;
    }

}
