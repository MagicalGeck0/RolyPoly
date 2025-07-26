package net.gecko.rolpol.item;

import net.gecko.rolpol.RolyPoly;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes extends DataComponentTypes {
    public static ComponentType<NbtComponent> registerModDataComponentType(String id) {
        return register(id, builder -> builder.codec(NbtComponent.CODEC));
    }
    public static final ComponentType<NbtComponent> BUGNET_SLOT_1 = registerModDataComponentType("bugnet_slot_1");
    public static final ComponentType<NbtComponent> BUGNET_SLOT_2 = registerModDataComponentType("bugnet_slot_2");
    public static final ComponentType<NbtComponent> BUGNET_SLOT_3 = registerModDataComponentType("bugnet_slot_3");
    public static final ComponentType<NbtComponent> BUGNET_SLOT_4 = registerModDataComponentType("bugnet_slot_4");
    public static final ComponentType<NbtComponent> BUGNET_SLOT_5 = registerModDataComponentType("bugnet_slot_5");
    public static final ComponentType<NbtComponent> BUGNET_SLOT_6 = registerModDataComponentType("bugnet_slot_6");
    public static final ComponentType<NbtComponent> BUGNET_SLOT_7 = registerModDataComponentType("bugnet_slot_7");
    public static final ComponentType<NbtComponent> BUGNET_SLOT_8 = registerModDataComponentType("bugnet_slot_8");
    public static final ComponentType<NbtComponent> BUGNET_SLOT_9 = registerModDataComponentType("bugnet_slot_9");

    public static void registerModDataComponentTypes(){
        RolyPoly.LOGGER.info("Registering Mod Data Component Types for " + RolyPoly.MOD_ID);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static <T> ComponentType<T> register(String id, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        // Builder<Object> returned by builder() is safely treated as Builder<T> because T is only used within builderOperator
        ComponentType.Builder<T> builder = (ComponentType.Builder<T>) (ComponentType.Builder) ComponentType.builder();
        return Registry.register(Registries.DATA_COMPONENT_TYPE, id, builderOperator.apply(builder).build());
    }
}
