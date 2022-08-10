package net.fabricmc.example;

import eu.pb4.polymer.ext.blocks.api.BlockModelType;
import eu.pb4.polymer.ext.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.ext.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.ext.blocks.api.PolymerTexturedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;

public class KyberOre extends Block implements PolymerTexturedBlock {

    private final BlockState polymerBlockState;

    public KyberOre(Settings settings, BlockModelType type, String modelID) {
        super(settings);

        this.polymerBlockState = PolymerBlockResourceUtils.requestBlock(type,
                PolymerBlockModel.of(new Identifier("polymertest", modelID)));
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
