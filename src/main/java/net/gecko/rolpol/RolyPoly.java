package net.gecko.rolpol;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.gecko.rolpol.entity.ModEntities;
import net.gecko.rolpol.entity.custom.IsopodEntity;
import net.gecko.rolpol.item.ModItems;
import net.gecko.rolpol.sound.ModSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RolyPoly implements ModInitializer {
	public static final String MOD_ID = "rolpol";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModEntities.registerModEntities();
		ModItems.registerModItems();
		ModSounds.registerSounds();

		FabricDefaultAttributeRegistry.register(ModEntities.ISOPOD, IsopodEntity.createAttributes());
	}
}