package net.gecko.rolpol.entity;

import net.gecko.rolpol.RolyPoly;
import net.gecko.rolpol.entity.custom.IsopodEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<IsopodEntity> ISOPOD = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(RolyPoly.MOD_ID, "isopod"),
            EntityType.Builder.create(IsopodEntity::new, SpawnGroup.UNDERGROUND_WATER_CREATURE)
                    .dimensions(1f,1f).build());


    public static void registerModEntities() {
        RolyPoly.LOGGER.info("Registering Mod Entities for " + RolyPoly.MOD_ID);
    }
}
