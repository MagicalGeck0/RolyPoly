package net.gecko.rolpol.item.custom;


import net.gecko.rolpol.entity.ModEntities;
import net.gecko.rolpol.entity.custom.IsopodEntity;
import net.gecko.rolpol.item.ModDataComponentTypes;
import net.minecraft.component.ComponentType;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class BugnetItem extends Item {


    public BugnetItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (isFull(stack)) {
            tooltip.add(Text.literal("Full"));
        } else {
            int count = getSlotNumber(stack);
            tooltip.add(Text.literal(count + "/" + getMax(stack)));
        }
        if (getMax(stack) == 9) {
            tooltip.add(Text.literal("§9Bugs Bliss"));
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

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (hand == Hand.MAIN_HAND && user.getOffHandStack().isOf(Items.WIND_CHARGE) && user.getStackInHand(hand).get(ModDataComponentTypes.BLISSED) == null) {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(user.getStackInHand(hand));
        }

        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR; // same as trident (shows charging animation)
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 72000;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        int useTime = this.getMaxUseTime(stack, user) - remainingUseTicks;

        if (user instanceof PlayerEntity player) {
            if (player.getOffHandStack().isOf(Items.WIND_CHARGE)) {
                if (useTime >= 20) {
                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_AMETHYST_BLOCK_PLACE, SoundCategory.PLAYERS, 1f, 1f);
                    player.getOffHandStack().decrementUnlessCreative(1, player);
                    NbtCompound data = new NbtCompound();
                    data.putBoolean("isBlissed", true);
                    NbtComponent bliss = NbtComponent.of(data);
                    stack.set(ModDataComponentTypes.BLISSED, bliss);
                }
            }
        }
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

        for (int i = 0; i < Math.min(getMax(stack), slots.length); i++) {
            ComponentType<NbtComponent> slot = slots[i];

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
        int min = slots.length - getMax(stack);
        for (int i = min; i < Math.min(slots.length, min + getMax(stack)); i++) {
            ComponentType<NbtComponent> slot = slots[i];

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

        for (int i = 0; i < Math.min(getMax(stack), slots.length); i++) {
            ComponentType<NbtComponent> slot = slots[i];

            if (stack.get(slot) != null) {
                ++count;
            }
        }
        return count;
    }

    static int getMax(ItemStack stack) {
        if (stack.get(ModDataComponentTypes.BLISSED) != null) {
            if (stack.get(ModDataComponentTypes.BLISSED).copyNbt().getBoolean("isBlissed")) {
                return 9;
            }
        }
        return 6;
    }

    static boolean isFull(ItemStack stack) {
        return (getSlot(stack) == null);
    }
}
