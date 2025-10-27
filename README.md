# Miner-Zombie
The MinerZombie plugin adds a new underground mob called miner zombie.

## Features
Naturally spawns below Y=40 and in total darkness.
It always spawns as a Zombie Villager with profession.
Holds an Iron Pickaxe.
Increased movement speed.
Drops configurable through config.yml.
Optional admin command to manually spawn Miner Zombies (/spawnminer [amount]).
Drops, drop ammount, mob speed, mob spawn chance can be configured in config.yml.

## 🧾 Setup Instructions
1. Download the latest release of `MinerZombie.jar`.
2. Place it inside your server’s `/plugins` folder.
3. Start or reload the server to generate the default configuration.
4. Edit `config.yml` as desired, then restart or reload the server.

## Configuration (`config.yml`)
```yaml
# MinerZombie by maxgim234

# Max number of ores dropped when MinerZombie is killed
max-drops: 10

# Chance (0.0 to 1.0) that a zombie underground becomes a Miner Zombie
natural-spawn-chance: 0.3

# Speed of MinerZombie (1.0 = normal speed)
speed-multiplier: 1.5

# Drops of MinerZombie (material names)
drops:
  - RAW_IRON
  - RAW_GOLD
  - DIAMOND
  - EMERALD


