package net.gecko.rolpol.item.custom;


import net.gecko.rolpol.entity.ModEntities;
import net.gecko.rolpol.entity.custom.IsopodEntity;
import net.gecko.rolpol.item.ModDataComponentTypes;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class BugnetItem extends Item {


    public BugnetItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (isFull(stack)) {
            tooltip.add(Text.literal("Full"));
        } else {
            int count = getSlotNumber(stack);
            tooltip.add(Text.literal(count + "/9"));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        ItemStack bugnet = user.getStackInHand(hand);
        NbtCompound data = new NbtCompound();
        ComponentType<NbtComponent> slot = getSlot(stack);
        if (!isFull(stack)) {
            entity.writeNbt(data);
            NbtComponent component = NbtComponent.of(data);
            bugnet.set(slot, component);
            entity.discard();
        }
        return ActionResult.PASS;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos().offset(context.getSide());
        PlayerEntity user = context.getPlayer();
        Hand hand = user.getActiveHand();
        ItemStack bugnet = user.getStackInHand(hand);
        ComponentType<NbtComponent> nextSlot = getNext(bugnet);


        if (!world.isClient() && nextSlot != null){
            NbtComponent component = bugnet.get(nextSlot);

            NbtCompound data = component.copyNbt();

            IsopodEntity netted = ModEntities.ISOPOD.create(world);
            netted.readNbt(data);


            netted.refreshPositionAndAngles(pos.getX() + 0.5f, pos.getY(), pos.getZ() + 0.5, 0,0);

            world.spawnEntity(netted);

            bugnet.set(nextSlot, null);

            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    static ComponentType<NbtComponent> getSlot(ItemStack stack) {
        ComponentType<NbtComponent>[] slots = new ComponentType[]{
                ModDataComponentTypes.BUGNET_SLOT_1,
                ModDataComponentTypes.BUGNET_SLOT_2,
                ModDataComponentTypes.BUGNET_SLOT_3,
                ModDataComponentTypes.BUGNET_SLOT_4,
                ModDataComponentTypes.BUGNET_SLOT_5,
                ModDataComponentTypes.BUGNET_SLOT_6,
                ModDataComponentTypes.BUGNET_SLOT_7,
                ModDataComponentTypes.BUGNET_SLOT_8,
                ModDataComponentTypes.BUGNET_SLOT_9,
        };

        for (ComponentType<NbtComponent> slot : slots) {
            if (stack.get(slot) == null) {
                return slot;
            }
        }

        return null;
    }

    static ComponentType<NbtComponent> getNext(ItemStack stack) {
        ComponentType<NbtComponent>[] slots = new ComponentType[]{
                ModDataComponentTypes.BUGNET_SLOT_9,
                ModDataComponentTypes.BUGNET_SLOT_8,
                ModDataComponentTypes.BUGNET_SLOT_7,
                ModDataComponentTypes.BUGNET_SLOT_6,
                ModDataComponentTypes.BUGNET_SLOT_5,
                ModDataComponentTypes.BUGNET_SLOT_4,
                ModDataComponentTypes.BUGNET_SLOT_3,
                ModDataComponentTypes.BUGNET_SLOT_2,
                ModDataComponentTypes.BUGNET_SLOT_1,

        };

        for (ComponentType<NbtComponent> slot : slots) {
            if (stack.get(slot) != null) {
                return slot;
            }
        }

        return null;
    }
    static int getSlotNumber(ItemStack stack) {
        int count = 0;
        ComponentType<NbtComponent>[] slots = new ComponentType[]{
                ModDataComponentTypes.BUGNET_SLOT_1,
                ModDataComponentTypes.BUGNET_SLOT_2,
                ModDataComponentTypes.BUGNET_SLOT_3,
                ModDataComponentTypes.BUGNET_SLOT_4,
                ModDataComponentTypes.BUGNET_SLOT_5,
                ModDataComponentTypes.BUGNET_SLOT_6,
                ModDataComponentTypes.BUGNET_SLOT_7,
                ModDataComponentTypes.BUGNET_SLOT_8,
                ModDataComponentTypes.BUGNET_SLOT_9,
        };

        for (ComponentType<NbtComponent> slot : slots) {
            if (stack.get(slot) != null) {
                ++count;
            }
        }
        return count;
    }

    static boolean isFull(ItemStack stack) {
        return (getSlot(stack) == null);
    }
}
