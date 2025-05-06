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

    public UltraEntityCreeper(World world) {
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int cb() {
        return this.datawatcher.getByte(16);
    }

    public void a(int i) {
        this.datawatcher.watch(16, Byte.valueOf((byte) i));
    }

    public void a(EntityLightning entitylightning) {
        super.a(entitylightning);
        this.datawatcher.watch(17, Byte.valueOf((byte) 1));
    }

    protected boolean a(EntityHuman entityhuman) {
        ItemStack itemstack = entityhuman.inventory.getItemInHand();

        if (itemstack != null && itemstack.getItem() == Items.FLINT_AND_STEEL) {
            this.world.makeSound(this.locX + 0.5D, this.locY + 0.5D, this.locZ + 0.5D, "fire.ignite", 1.0F, this.random.nextFloat() * 0.4F + 0.8F);
            entityhuman.ba();
            if (!this.world.isStatic) {
                this.cd();
                itemstack.damage(1, entityhuman);
                return true;
            }
        }

        return super.a(entityhuman);
    }

    private void ce() {
        if (!this.world.isStatic) {
            boolean flag = this.world.getGameRules().getBoolean("mobGriefing");

            if (this.isPowered()) {
                this.world.explode(this, this.locX, this.locY, this.locZ, (float) (this.explosionRadius * 2), flag); ///////Increase blast radius of powered explosion here (this.explosionRadius * 2)
            } else {
                this.world.explode(this, this.locX, this.locY, this.locZ, (float) this.explosionRadius, flag);
            }

            this.die();
        }
    }

    public boolean cc() {
        return this.datawatcher.getByte(18) != 0;
    }

    public void cd() {
        this.datawatcher.watch(18, Byte.valueOf((byte) 1));
    }
    
}
