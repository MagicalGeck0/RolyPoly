package net.gecko.rolpol.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.gecko.rolpol.RolyPoly;
import net.gecko.rolpol.entity.ModEntities;
import net.gecko.rolpol.item.custom.BugnetItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item ISOPOD_SPAWN_EGG = registerItem("isopod_spawn_egg", new SpawnEggItem(ModEntities.ISOPOD, 0xbebebe, 0xbcdc694, new Item.Settings()));
    public static final Item BUGNET = registerItem("bugnet", new BugnetItem(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(RolyPoly.MOD_ID, name), item);
    }

    public static void registerModItems() {
        RolyPoly.LOGGER.info("Registering Mod Items for " + RolyPoly.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> {
            entries.add(ISOPOD_SPAWN_EGG);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(BUGNET);
        });
    }
}
