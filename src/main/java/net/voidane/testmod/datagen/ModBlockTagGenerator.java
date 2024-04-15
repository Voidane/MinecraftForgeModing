package net.voidane.testmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.voidane.testmod.TestMod;
import net.voidane.testmod.block.ModBlocks;
import net.voidane.testmod.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {

    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TestMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        this.tag(ModTags.Blocks.METAL_DETECTOR_VALUABLES).add(ModBlocks.SAPPHIRE_ORE.get()).addTag(Tags.Blocks.ORES);
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(
                ModBlocks.SAPPHIRE_BLOCK.get(),
                ModBlocks.SAPPHIRE_ORE.get(),
                ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(),
                ModBlocks.NETHER_SAPPHIRE_ORE.get(),
                ModBlocks.ENDSTONE_SAPPHIRE_ORE.get()
                );
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                ModBlocks.SAPPHIRE_BLOCK.get(),
                ModBlocks.SAPPHIRE_ORE.get(),
                ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(),
                ModBlocks.NETHER_SAPPHIRE_ORE.get(),
                ModBlocks.ENDSTONE_SAPPHIRE_ORE.get()
        );

        // this.tag(BlockTags.NEEDS_DIAMOND_TOOL).add(/*None so far*/);
        // this.tag(BlockTags.NEEDS_STONE_TOOL).add(/*None so far*/);
        // this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL).add(/*None so far*/);

        this.tag(BlockTags.FENCES).add(
                ModBlocks.SAPPHIRE_FENCE.get()
        );

        this.tag(BlockTags.FENCE_GATES).add(
                ModBlocks.SAPPHIRE_FENCE_GATE.get()
        );

        this.tag(BlockTags.WALLS).add(
                ModBlocks.SAPPHIRE_WALL.get()
        );
    }
}
