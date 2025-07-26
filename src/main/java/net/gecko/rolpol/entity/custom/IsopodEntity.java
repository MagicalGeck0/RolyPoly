package net.gecko.rolpol.entity.custom;

import net.gecko.rolpol.RolyPoly;
import net.gecko.rolpol.entity.ModEntities;
import net.gecko.rolpol.goal.EatCropsGoal;
import net.gecko.rolpol.sound.ModSounds;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static java.lang.Math.random;

public class IsopodEntity extends TameableEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState rollAnimationState = new AnimationState();
    public int idleAnimationTimeout = 0;
    public int rollAnimationTimeout = 0;
    public int squeakTimer = 0;
    public int pushBackTimer = 0;
    public int shellTimer = 0;
    public boolean shelled = false;


    public IsopodEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));

        this.goalSelector.add(1, new EatCropsGoal(this, 1.5D, 10));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.150));
        this.goalSelector.add(3, new TemptGoal(this, 1.250, Ingredient.fromTag(ItemTags.PARROT_FOOD), true));

        this.goalSelector.add(4, new FollowParentGoal(this, 1.10));

        this.goalSelector.add(5, new FollowOwnerGoal(this, 2, 5.0f, 20.0f));
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 4.0f));
        this.goalSelector.add(8, new LookAroundGoal(this));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20)
                .add(EntityAttributes.GENERIC_SCALE, 0.75f)
                .add(EntityAttributes.GENERIC_ARMOR, 1.5f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.1f);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = (int) (random() * 201);
            this.idleAnimationState.start(this.age);

        } else {
            --this.idleAnimationTimeout;
        }

        if (this.rollAnimationTimeout > 0) {
            --this.rollAnimationTimeout;

        } else {
            this.rollAnimationState.stop();
        }
    }

    private void setupSqueak() {
        if (this.squeakTimer <= 0 && !shelled) {
            int radius = 15;
            this.squeakTimer = (int) (random() * 201);
            List<HostileEntity> nearbyEntities = this.getWorld().getEntitiesByClass(
                    HostileEntity.class,
                    this.getBoundingBox().expand(radius),
                    entity -> true
            );
            if (!nearbyEntities.isEmpty()) {
                getWorld().playSound(null, getBlockPos(), ModSounds.ENTITY_ISOPOD_SQUEAK, SoundCategory.NEUTRAL);
            }
        } else {
            --this.squeakTimer;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getWorld().isClient()) {
            this.setupAnimationStates();
        }

        if (pushBackTimer <= 0) {
            pushBack();
            pushBackTimer = 5;
        } else {
            --pushBackTimer;
        }

        if (shellTimer <= 0) {
            shelled = false;
        } else {
            --shellTimer;
        }
        eatCrops();
        setupSqueak();
    }

    public void pushBack() {

        if (!this.getWorld().isClient()) {
            double radius = 8.0D;

            List<LivingEntity> nearbyEntities = this.getWorld().getEntitiesByClass(
                    LivingEntity.class,
                    this.getBoundingBox().expand(radius),
                    this::shouldPush
            );

            for (LivingEntity entity : nearbyEntities) {
                if (entity == this) continue;
                if (!entity.isOnGround()) continue;
                double dx = entity.getX() - this.getX();
                double dz = entity.getZ() - this.getZ();
                double distance = Math.sqrt(dx * dx + dz * dz);

                if (distance < 0.0001) continue;

                double strength = 0.5;

                double pushX = dx / distance * strength;
                double pushZ = dz / distance * strength;

                entity.addVelocity(pushX, 0, pushZ);
                entity.velocityModified = true;

                float yaw = (float)(MathHelper.atan2(pushZ, pushX) * (180.0 / Math.PI)) - 90.0F;
                entity.setYaw(yaw);
            }
        }
    }

    public boolean shouldPush(LivingEntity entity) {
        return entity instanceof ZombieEntity || entity instanceof BoggedEntity || entity instanceof CreeperEntity;
    }

    public void eatCrops(){
        World world = this.getWorld();
        if (world.getBlockState(this.getBlockPos()).isIn(BlockTags.CROPS)) {
            world.breakBlock(this.getBlockPos(), false);
        }
    }

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (!this.isTamed() && IsIsopodFood(itemStack)) {
            itemStack.decrementUnlessCreative(1, player);
            if (!this.isSilent()) {
                this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_PARROT_EAT, this.getSoundCategory(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
            }

            if (!this.getWorld().isClient) {
                if (this.random.nextInt(10) == 0) {
                    this.setOwner(player);
                    this.getWorld().sendEntityStatus(this, (byte) 7);
                } else {
                    this.getWorld().sendEntityStatus(this, (byte) 6);
                }
            }

            return ActionResult.success(this.getWorld().isClient);
        }
        return super.interactMob(player, hand);
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (shelled) return false;
        this.rollAnimationState.start(this.age);
        this.rollAnimationTimeout = 100;
        this.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 255, false,false));
        shelled = true;
        shellTimer = 100;
        return super.damage(source, amount);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return IsIsopodFood(stack);
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.ISOPOD.create(world);
    }


    public boolean IsIsopodFood(ItemStack stack){
        return stack.isOf(Items.SWEET_BERRIES)
                || stack.isOf(Items.GLOW_BERRIES)
                || stack.isOf(Items.WHEAT_SEEDS)
                || stack.isOf(Items.POTATO)
                || stack.isOf(Items.BEETROOT)
                || stack.isOf(Items.CARROT)
                || stack.isOf(Items.MELON_SLICE)
                || stack.isOf(Items.APPLE)
                || stack.isOf(Items.CHORUS_FRUIT)
                || stack.isOf(Items.POISONOUS_POTATO)
                || stack.isOf(Items.ROTTEN_FLESH)
                || stack.isOf(Items.DRIED_KELP);
    }
}
