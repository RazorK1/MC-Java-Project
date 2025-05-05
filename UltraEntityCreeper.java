import java.util.ArrayList;
import java.util.Random;

package net.minecraft.server;

public class UltraEntityCreeper extends EntityCreeper {

    //////////////////////////////////////////////////////
    //private int bp;
    //private int fuseTicks;
    //private int maxFuseTicks = 4; /////////// Explosion timer here (Original value = 30)
    //private int explosionRadius = 30; //////// Increase blast radius here (Original Value = 3)  
    //////////////////////////////////////////////////////////////

    maxFuseTicks = 4;
    explosionRadius = 30;

    EntityCreeper(World world) {
        super(world);
        this.goalSelector.a(1, new PathfinderGoalFloat(this));
        this.goalSelector.a(2, new PathfinderGoalSwell(this));
        this.goalSelector.a(3, new PathfinderGoalAvoidPlayer(this, EntityOcelot.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, 1.0D, false));
        this.goalSelector.a(5, new PathfinderGoalRandomStroll(this, 0.8D));
        this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(6, new PathfinderGoalRandomLookaround(this));
        this.targetSelector.a(1, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, 0, true));
        this.targetSelector.a(2, new PathfinderGoalHurtByTarget(this, false));
    }

    //////////////////////////////////////////////////////////////////////////////
    public void die(DamageSource damagesource) { ////////// (This isn't needed)
        super.die(damagesource);
        if (damagesource.getEntity() instanceof EntitySkeleton) {
            int i = Item.getId(Items.RECORD_1);
            int j = Item.getId(Items.RECORD_12);
            int k = i + this.random.nextInt(j - i + 1);

            this.a(Item.getById(k), 1);
        }
    }

    //////////////////////////////////////////////////////////////////////////////
    ///////////////// (OLD getLoot() function)
    //protected Item getLoot() {        
    //    return Items.DIAMOND_PICKAXE; /////////// Set loot here (Original Value = SULPHUR)
    }
    /////////////////////////////////////////////////////////////////////////////////

    /////////////////// (NEW getLoot() function with an arraylist)
    protected Item getLoot() {
    ArrayList<Item> lootTable = new ArrayList<>();
    
    //////// Adding possible loot items here
    lootTable.add(Items.DIAMOND_PICKAXE);
    lootTable.add(Items.SULPHUR);
    lootTable.add(Items.GOLD_INGOT);
    lootTable.add(Items.IRON_SWORD);
    lootTable.add(Items.APPLE);

    lootTable.add(Item.getById(Block.getId(Blocks.TNT))); /////// DROPS TNT!!!!!!

    Random random = new Random();
    return lootTable.get(random.nextInt(lootTable.size()));

}
