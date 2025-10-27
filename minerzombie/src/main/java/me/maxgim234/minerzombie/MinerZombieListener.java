package me.maxgim234.minerzombie;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.entity.ZombieVillager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.block.Block;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Villager;
import java.util.List;
import java.util.Random;

public class MinerZombieListener implements Listener {

    private final Minerzombie plugin;
    private final Random random = new Random();

    public MinerZombieListener(Minerzombie plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onSpawn(CreatureSpawnEvent event) {
        if (event.getEntityType() != EntityType.ZOMBIE) return;

        Zombie zombie = (Zombie) event.getEntity();
        Location loc = zombie.getLocation();
        if (loc.getBlockY() >= 40) return;
        if (loc.getBlock().getLightLevel() > 0) return;

        double chance = plugin.getConfig().getDouble("natural-spawn-chance", 0.3);
        if (random.nextDouble() > chance) return;


        World world = loc.getWorld();
        zombie.remove();

        ZombieVillager villager = (ZombieVillager) world.spawnEntity(loc, EntityType.ZOMBIE_VILLAGER);
        Villager.Profession[] professions = {
                Villager.Profession.ARMORER,
                Villager.Profession.FARMER,
                Villager.Profession.MASON,
                Villager.Profession.WEAPONSMITH
        };
        villager.setVillagerProfession(professions[random.nextInt(professions.length)]);


        double speedMultiplier = plugin.getConfig().getDouble("speed-multiplier", 1.5);
        villager.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.23 * speedMultiplier);


        villager.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_PICKAXE));
        villager.getEquipment().setItemInMainHandDropChance(0.0f);
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        if (event.getEntityType() != EntityType.ZOMBIE_VILLAGER) return;

        LivingEntity entity = event.getEntity();
        if (!(entity instanceof ZombieVillager)) return;

        FileConfiguration config = plugin.getConfig();
        List<String> dropList = config.getStringList("drops");
        int maxDrops = config.getInt("max-drops", 10);

        if (dropList.isEmpty()) return;

        int dropCount = random.nextInt(maxDrops) + 1;
        for (int i = 0; i < dropCount; i++) {
            String matName = dropList.get(random.nextInt(dropList.size()));
            Material mat = Material.getMaterial(matName);
            if (mat != null) {
                entity.getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(mat, 1));
            }
        }
    }
}
