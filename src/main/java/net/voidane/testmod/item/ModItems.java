package net.voidane.testmod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidane.testmod.TestMod;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TestMod.MOD_ID);

    public static final RegistryObject<Item> MONEY = ITEMS.register("money", () -> new Item((new Item.Properties()
            .stacksTo(1).rarity(Rarity.EPIC))));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
