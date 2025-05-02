import java.util.ArrayList;
import java.util.Random;

package net.minecraft.server;

public class UltraEntityCreeper extends EntityCreeper {

    private int bp;
    private int fuseTicks;
    private int maxFuseTicks = 4; /////////// Explosion timer here (Original value = 30)
    private int explosionRadius = 30; //////// Increase blast radius here (Original Value = 3)  

    public void die(DamageSource damagesource) { ////////// (This isn't needed)
        super.die(damagesource);
        if (damagesource.getEntity() instanceof EntitySkeleton) {
            int i = Item.getId(Items.RECORD_1);
            int j = Item.getId(Items.RECORD_12);
            int k = i + this.random.nextInt(j - i + 1);

            this.a(Item.getById(k), 1);
        }
    }

    ///////////////// (OLD getLoot() function)
    protected Item getLoot() {        
        return Items.DIAMOND_PICKAXE; /////////// Set loot here (Original Value = SULPHUR)
    }

    /////////////////// (NEW getLoot() function with an arraylist)
    protected Item getLoot() {
    ArrayList<Item> lootTable = new ArrayList<>();
    
    //////// Adding possible loot items here
    lootTable.add(Items.DIAMOND_PICKAXE);
    lootTable.add(Items.SULPHUR);
    lootTable.add(Items.GOLD_INGOT);
    lootTable.add(Items.IRON_SWORD);
    lootTable.add(Items.APPLE);

    lootTable.add(Item.getById(Block.getId(Blocks.TNT))); /////// DROPS TNT!!!!!!!!

    Random random = new Random();
    return lootTable.get(random.nextInt(lootTable.size()));

}
