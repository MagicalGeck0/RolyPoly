package net.gecko.rolpol.world.gen;


import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.gecko.rolpol.entity.ModEntities;
import net.gecko.rolpol.entity.custom.IsopodEntity;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.Heightmap;

public class ModEntitySpawns {
    public static void addSpawns() {
        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(),
                SpawnGroup.AMBIENT, ModEntities.ISOPOD, 5, 1, 2);

        SpawnRestriction.register(ModEntities.ISOPOD, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
    }
}
