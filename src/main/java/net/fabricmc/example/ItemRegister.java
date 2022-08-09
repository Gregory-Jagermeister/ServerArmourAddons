package net.fabricmc.example;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemRegister {

    private static void regTestArmor(EquipmentSlot slot, String main, String id) {
        Registry.register(Registry.ITEM, new Identifier("test", main + "_" + id), new TestArmor(slot,
                new Identifier("polymertest", "item/" + main + "_" + id), new Identifier("polymertest", main)));
    }

    private static void regFireproofArmor(EquipmentSlot slot, String main, String id) {
        Registry.register(Registry.ITEM, new Identifier("test", main + "_" + id), new FirepoofArmor(slot,
                new Identifier("polymertest", "item/" + main + "_" + id), new Identifier("polymertest", main)));
    }

    private static void regMinersArmor(EquipmentSlot slot, String main, String id) {
        Registry.register(Registry.ITEM, new Identifier("test", main + "_" + id), new MinersArmor(slot,
                new Identifier("polymertest", "item/" + main + "_" + id), new Identifier("polymertest", main)));
    }

    private static void regSaberSwords(String main, String id) {
        Registry.register(Registry.ITEM, new Identifier("test", main + "_" + id), new SaberSword(SaberMaterial.INSTANCE,
                8, -0.20F, new Item.Settings().group(ItemGroup.COMBAT),
                new Identifier("polymertest", "item/" + main + "_" + id)));
    }

    private static void regSaberParts(String main, String id) {
        Registry.register(Registry.ITEM, new Identifier("test", main + "_" + id),
                new SaberParts(new Item.Settings().group(ItemGroup.MATERIALS),
                        new Identifier("polymertest", "item/" + main + "_" + id)));
    }

    public static void register() {

        // Register Night vision Goggles.
        regTestArmor(EquipmentSlot.HEAD, "night_vision", "goggles");

        // Register Fireproof Armor
        regFireproofArmor(EquipmentSlot.HEAD, "fireproof", "helmet");
        regFireproofArmor(EquipmentSlot.CHEST, "fireproof", "chestplate");
        regFireproofArmor(EquipmentSlot.LEGS, "fireproof", "leggings");
        regFireproofArmor(EquipmentSlot.FEET, "fireproof", "boots");

        // Register Miners Armor
        regMinersArmor(EquipmentSlot.HEAD, "miners", "helmet");
        regMinersArmor(EquipmentSlot.CHEST, "miners", "chestplate");
        regMinersArmor(EquipmentSlot.LEGS, "miners", "leggings");
        regMinersArmor(EquipmentSlot.FEET, "miners", "boots");

        // Register Sabers
        regSaberSwords("saber", "blue");
        regSaberSwords("saber", "red");
        regSaberSwords("saber", "green");
        regSaberSwords("saber", "purple");
        regSaberSwords("saber", "yellow");
        regSaberSwords("saber", "orange");

        // Register Saber Parts
        regSaberParts("saber", "hilt");
        regSaberParts("kyber", "blue");
        regSaberParts("kyber", "red");
        regSaberParts("kyber", "green");
        regSaberParts("kyber", "purple");
        regSaberParts("kyber", "yellow");
        regSaberParts("kyber", "orange");
    }

}
