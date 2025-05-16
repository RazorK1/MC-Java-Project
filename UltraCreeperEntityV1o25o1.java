package com.example.ultracreeper;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UltraCreeperEntity extends CreeperEntity {

    public UltraCreeperEntity(EntityType<? extends CreeperEntity> entityType, World world) {
        super(entityType, world);

        super(type, world);
	xpReward = 0;
	setNoAi(false);
	    
	setPersistenceRequired(); ////// Prevents despawning when idle
        
    }

    @Override
    public int getFuseTime() {
        return 4; // Shorter fuse time
    }

    @Override
    public float getExplosionRadius() {
        return 30.0F; // Larger explosion radius
    }

    @Override
    protected void dropLoot(DamageSource source, boolean causedByPlayer) {
        super.dropLoot(source, causedByPlayer);
        List<Item> lootTable = new ArrayList<>();
        lootTable.add(Items.DIAMOND_PICKAXE);
        lootTable.add(Items.GUNPOWDER);
        lootTable.add(Items.GOLD_INGOT);
        lootTable.add(Items.IRON_SWORD);
        lootTable.add(Items.APPLE);
        lootTable.add(Items.TNT);

        Random random = new Random();
        Item droppedItem = lootTable.get(random.nextInt(lootTable.size()));
        this.dropItem(droppedItem);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////// Command thy here!
    public final AnimationState animationState0 = new AnimationState();

	@Override
	protected void registerGoals() {
		super.registerGoals();

		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false) {

			@Override
			protected boolean canPerformAttack(LivingEntity entity) {
				return this.isTimeToAttack() && this.mob.distanceToSqr(entity) < (this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth()) && this.mob.getSensing().hasLineOfSight(entity);
			}

		});
		this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1));
		this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(5, new FloatGoal(this));

	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("entity.generic.hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("entity.generic.death"));
	}

	@Override
	public void tick() {
		super.tick();
		if (this.level().isClientSide()) {
			this.animationState0.animateWhen(true, this.tickCount);
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Breathe underwater function
	@Override
	public boolean canDrownInFluidType(FluidType type) {
		double x = this.getX();
		double y = this.getY();
		double z = this.getZ();
		Level world = this.level();
		Entity entity = this;
		return false;
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void init(RegisterSpawnPlacementsEvent event) {
		event.register(DemoModModEntities.ULTRACREEPER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos, random) -> (world.getDifficulty() != Difficulty.PEACEFUL && Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)),
				RegisterSpawnPlacementsEvent.Operation.REPLACE);
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.2);
		builder = builder.add(Attributes.MAX_HEALTH, 10);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
		builder = builder.add(Attributes.FOLLOW_RANGE, 12);

		builder = builder.add(Attributes.STEP_HEIGHT, 0.6);

		return builder;
	}

}
