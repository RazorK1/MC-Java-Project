package net.minecraft.server;

public class UltraEntityCreeper extends EntityCreeper {

    private int bp;
    private int fuseTicks;
    private int maxFuseTicks = 4; /////////// Explosion timer here (Original value = 30)
    private int explosionRadius = 30; //////// Increase blast radius here (Original Value = 3)  

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
