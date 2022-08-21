package net.fabricmc.example;

import java.util.Arrays;

import eu.pb4.polymer.api.entity.PolymerEntityUtils;
import eu.pb4.polymer.ext.blocks.api.BlockModelType;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

public class ItemRegister {

        public static final EntityType<SeatEntity> SEAT_ENTITY = Registry
                        .register(Registry.ENTITY_TYPE, new Identifier("polymertest", "seat_ent"),
                                        FabricEntityTypeBuilder.<SeatEntity>create(SpawnGroup.MISC,
                                                        SeatEntity::new).trackRangeBlocks(10).disableSummon()
                                                        .fireImmune().dimensions(EntityDimensions.fixed(0f, 0f))
                                                        .build());

    public static final Block KYBER_ORE = regKyberOre(BlockModelType.FULL_BLOCK, "kyber_ore");

    private static ConfiguredFeature<?, ?> KYBER_ORE_CONFIGURED_FEATURE = new ConfiguredFeature(Feature.ORE,
            new OreFeatureConfig(OreConfiguredFeatures.NETHERRACK, KYBER_ORE.getDefaultState(), 4));

    public static PlacedFeature KYBER_ORE_PLACED_FEATURE = new PlacedFeature(
            RegistryEntry.of(KYBER_ORE_CONFIGURED_FEATURE), Arrays.asList(
                    CountPlacementModifier.of(20),
                    SquarePlacementModifier.of(),
                    HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));

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

    public static Block regKyberOre(BlockModelType type, String modelId) {
            var id = new Identifier("test", modelId);
            var block = Registry.register(Registry.BLOCK, id,
                            new KyberOre(FabricBlockSettings.copy(Blocks.DIAMOND_ORE), type, "block/" + modelId));

            Registry.register(Registry.ITEM, id,
                            new KyberOreItem(new Item.Settings().group(ItemGroup.BUILDING_BLOCKS), block,
                                            "block/" + modelId));

            return block;
    }

    public static Block regSeatBlock(BlockModelType type, String modelId) {
            var id = new Identifier("test", modelId);
            var block = Registry.register(Registry.BLOCK, id,
                            new Seat(FabricBlockSettings.copy(Blocks.ACACIA_FENCE_GATE), type, "block/" + modelId));

            Registry.register(Registry.ITEM, id,
                            new SeatItem(block, new Item.Settings().group(ItemGroup.DECORATIONS),
                                            "block/" + modelId));

        return block;
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

        // Register Ore and add to Generation.
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("test", "kyber_ore"),
                KYBER_ORE_CONFIGURED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("test", "kyber_ore"),
                KYBER_ORE_PLACED_FEATURE);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES,
                        RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier("test", "kyber_ore")));

        // Register Furniture
        regSeatBlock(BlockModelType.TRANSPARENT_BLOCK, "seat_white");
        regSeatBlock(BlockModelType.TRANSPARENT_BLOCK, "seat_orange");
        regSeatBlock(BlockModelType.TRANSPARENT_BLOCK, "seat_magenta");
        regSeatBlock(BlockModelType.TRANSPARENT_BLOCK, "seat_light_blue");
        regSeatBlock(BlockModelType.TRANSPARENT_BLOCK, "seat_yellow");
        regSeatBlock(BlockModelType.TRANSPARENT_BLOCK, "seat_lime");
        regSeatBlock(BlockModelType.TRANSPARENT_BLOCK, "seat_pink");
        regSeatBlock(BlockModelType.TRANSPARENT_BLOCK, "seat_gray");
        regSeatBlock(BlockModelType.TRANSPARENT_BLOCK, "seat_light_gray");
        regSeatBlock(BlockModelType.TRANSPARENT_BLOCK, "seat_cyan");
        regSeatBlock(BlockModelType.TRANSPARENT_BLOCK, "seat_purple");
        regSeatBlock(BlockModelType.TRANSPARENT_BLOCK, "seat_blue");
        regSeatBlock(BlockModelType.TRANSPARENT_BLOCK, "seat_brown");
        regSeatBlock(BlockModelType.TRANSPARENT_BLOCK, "seat_green");
        regSeatBlock(BlockModelType.TRANSPARENT_BLOCK, "seat_red");
        regSeatBlock(BlockModelType.TRANSPARENT_BLOCK, "seat_black");

        // Register Funiture Entities
        PolymerEntityUtils.registerType(SEAT_ENTITY);
    }

}
