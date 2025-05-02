package net.minecraft.server;

public class UltraEntityCreeper extends EntityCreeper {

    private int bp;
    private int fuseTicks;
    private int maxFuseTicks = 4; /////////// Explosion timer here (Original value = 30)
    private int explosionRadius = 30; //////// Increase blast radius here (Original Value = 3)  

    public void die(DamageSource damagesource) {
        super.die(damagesource);
        if (damagesource.getEntity() instanceof EntitySkeleton) {
            int i = Item.getId(Items.RECORD_1);
            int j = Item.getId(Items.RECORD_12);
            int k = i + this.random.nextInt(j - i + 1);

            this.a(Item.getById(k), 1);
        }
    }

}
