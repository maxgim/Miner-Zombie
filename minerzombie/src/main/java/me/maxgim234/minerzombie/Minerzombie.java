package me.maxgim234.minerzombie;

import org.bukkit.plugin.java.JavaPlugin;

public final class Minerzombie extends JavaPlugin {

    private static Minerzombie instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new MinerZombieListener(this), this);
        getCommand("spawnminer").setExecutor(new MinerZombieCommand(this));

        getLogger().info("MinerZombie plugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("MinerZombie plugin disabled.");
    }

    public static Minerzombie getInstance() {
        return instance;
    }
}
