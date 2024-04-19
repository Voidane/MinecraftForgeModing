package net.voidane.testmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.voidane.testmod.TestMod;
import net.voidane.testmod.item.ModItems;
import net.voidane.testmod.loot.AddItemModifier;
import net.voidane.testmod.loot.AddSusSandItemModifier;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {

    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, TestMod.MOD_ID);
    }

    @Override
    protected void start() {

        // Allow grass to drop from grass with a 5% chance drop rate.
        add("strawberry_from_grass", new AddItemModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.GRASS).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()
                }   , ModItems.STRAWBERRY.get()));

        // Allow creepers to drop strawberries every time we kill them
        add("strawberry_from_creeper", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("entities/creeper")).build()
                }   , ModItems.STRAWBERRY.get()));

        // Add a metal detector to a jungle temple chest with 100% drop rate
        add("metal_detector_from_jungle_temples", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/jungle_temple")).build()
        }, ModItems.METAL_DETECTOR.get()));

        // Sus sand can drop a metal detector 50% chance
        add("metal_detector_from_suspicious_sand", new AddSusSandItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("archaeology/desert_pyramid")).build()
        }, ModItems.METAL_DETECTOR.get()));
    }
}
