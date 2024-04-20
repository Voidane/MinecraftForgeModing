package net.voidane.testmod.datagen.loot;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import net.voidane.testmod.block.ModBlocks;
import net.voidane.testmod.item.ModItems;
import net.voidane.testmod.item.custom.StrawberryCropBlock;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {

    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.SAPPHIRE_BLOCK.get());
        this.dropSelf(ModBlocks.SOUND_BLOCK.get());

        this.add(ModBlocks.SAPPHIRE_ORE.get(),
                block -> createOreDrops(ModBlocks.SAPPHIRE_ORE.get(), ModItems.RAW_SAPPHIRE.get(),
                        2.0f, 5.0f));
        this.add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(),
                block -> createOreDrops(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(), ModItems.RAW_SAPPHIRE.get(),
                        3.0f, 6.0f));
        this.add(ModBlocks.NETHER_SAPPHIRE_ORE.get(),
                block -> createOreDrops(ModBlocks.NETHER_SAPPHIRE_ORE.get(), ModItems.RAW_SAPPHIRE.get(),
                        2.0f, 5.0f));
        this.add(ModBlocks.ENDSTONE_SAPPHIRE_ORE.get(),
                block -> createOreDrops(ModBlocks.ENDSTONE_SAPPHIRE_ORE.get(), ModItems.RAW_SAPPHIRE.get(),
                        4.0f, 5.0f));

        this.dropSelf(ModBlocks.SAPPHIRE_STAIRS.get());
        this.dropSelf(ModBlocks.SAPPHIRE_BUTTON.get());
        this.dropSelf(ModBlocks.SAPPHIRE_PRESSURE_PLATE.get());
        this.dropSelf(ModBlocks.SAPPHIRE_TRAPDOOR.get());
        this.dropSelf(ModBlocks.SAPPHIRE_FENCE.get());
        this.dropSelf(ModBlocks.SAPPHIRE_FENCE_GATE.get());
        this.dropSelf(ModBlocks.SAPPHIRE_WALL.get());
        this.add(ModBlocks.SAPPHIRE_SLAB.get(), block -> createSlabItemTable(ModBlocks.SAPPHIRE_SLAB.get()));
        this.add(ModBlocks.SAPPHIRE_DOOR.get(), block -> createDoorTable(ModBlocks.SAPPHIRE_DOOR.get()));

        LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.STRAWBERRY_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StrawberryCropBlock.AGE, 5));

        this.add(ModBlocks.STRAWBERRY_CROP.get(), createCropDrops(ModBlocks.STRAWBERRY_CROP.get(), ModItems.STRAWBERRY.get(),
                ModItems.STRAWBERRY_SEEDS.get(), lootitemcondition$builder));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    protected LootTable.Builder createOreDrops(Block pBlock, Item item, float minDropm, float maxDrop) {
        return createSilkTouchDispatchTable(pBlock, this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDropm, maxDrop)))
                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }
}
