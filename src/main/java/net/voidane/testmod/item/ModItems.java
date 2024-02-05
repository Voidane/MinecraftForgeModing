package net.voidane.testmod.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidane.testmod.TestMod;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TestMod.MOD_ID);

    public static final RegistryObject<Item> MONEY = ITEMS.register("money", () -> new Item((new Item.Properties())));

    public static final RegistryObject<Item> RAW_SAPPHIRE = ITEMS.register("raw_sapphire", () -> new Item((new Item.Properties())));
    public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire", () -> new Item((new Item.Properties())));

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
        // Demo
    }
}
