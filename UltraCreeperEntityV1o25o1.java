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
}
