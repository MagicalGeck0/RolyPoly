package net.gecko.rolpol.item.custom;


import net.gecko.rolpol.entity.ModEntities;
import net.gecko.rolpol.entity.custom.IsopodEntity;
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
        if (stack.get(DataComponentTypes.CUSTOM_DATA) != null) {
            tooltip.add(Text.literal("Full"));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        ItemStack bugnet = user.getStackInHand(hand);
        NbtCompound data = new NbtCompound();
        if (bugnet.get(DataComponentTypes.CUSTOM_DATA) == null) {
            if (entity.getCustomName() != null) {
                bugnet.set(DataComponentTypes.CUSTOM_NAME, entity.getCustomName());
            }
            entity.writeNbt(data);
            NbtComponent component = NbtComponent.of(data);
            bugnet.set(DataComponentTypes.CUSTOM_DATA, component);
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

        if (!world.isClient() && bugnet.get(DataComponentTypes.CUSTOM_DATA) != null){
            NbtComponent component = bugnet.get(DataComponentTypes.CUSTOM_DATA);

            NbtCompound data = component.copyNbt();

            IsopodEntity netted = ModEntities.ISOPOD.create(world);
            netted.readNbt(data);
            if (bugnet.get(DataComponentTypes.CUSTOM_NAME) != null) {
                Text name = bugnet.get(DataComponentTypes.CUSTOM_NAME);
                netted.setCustomName(name);
            }

            netted.refreshPositionAndAngles(pos.getX() + 0.5f, pos.getY(), pos.getZ() + 0.5, 0,0);

            world.spawnEntity(netted);

            bugnet.set(DataComponentTypes.CUSTOM_DATA, null);
            bugnet.set(DataComponentTypes.CUSTOM_NAME, null);

            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
}
