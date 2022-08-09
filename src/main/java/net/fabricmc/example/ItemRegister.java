package net.fabricmc.example;

import net.minecraft.entity.EquipmentSlot;
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

    }

}
