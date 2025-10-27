package me.maxgim234.minerzombie;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.ZombieVillager;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class MinerZombieCommand implements CommandExecutor {

    private final Minerzombie plugin;
    private final Random random = new Random();

    public MinerZombieCommand(Minerzombie plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        int amount = 1;
        if (args.length == 1) {
            try {
                amount = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                player.sendMessage("§cInvalid number.");
                return true;
            }
        }

        for (int i = 0; i < amount; i++) {
            spawnMinerZombie(player.getLocation());
        }

        player.sendMessage("§aSpawned " + amount + " Miner Zombie(s)!");
        return true;
    }

    private void spawnMinerZombie(Location loc) {
        ZombieVillager villager = loc.getWorld().spawn(loc, ZombieVillager.class);
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
}
