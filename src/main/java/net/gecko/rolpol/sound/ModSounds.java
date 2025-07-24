package net.gecko.rolpol.sound;

import net.gecko.rolpol.RolyPoly;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {

    public static final SoundEvent ENTITY_ISOPOD_SQUEAK = registerSoundEvent("entity_isopod_squeak");

    private static SoundEvent registerSoundEvent(String name){
        Identifier id = Identifier.of(RolyPoly.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        RolyPoly.LOGGER.info("Registering Mod Sounds for "+ RolyPoly.MOD_ID);
    }
}
