package net.gecko.rolpol.goal;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;


public class EatCropsGoal extends MoveToTargetPosGoal {
    private final TagKey<Block> blockTag;

    public EatCropsGoal(PathAwareEntity mob, double speed, int range) {
        super(mob, speed, range, 1);
        this.blockTag = BlockTags.CROPS;
    }

    @Override
    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        return state.isIn(blockTag);
    }
}
