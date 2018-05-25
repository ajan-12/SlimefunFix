package top.ageofelysian.SlimefunFix;

import java.io.File;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import de.tr7zw.itemnbtapi.NBTCompound;
import de.tr7zw.itemnbtapi.NBTItem;
import de.tr7zw.itemnbtapi.NBTListCompound;
import de.tr7zw.itemnbtapi.NBTType;


public final class SlimefunFix extends JavaPlugin {


    private void fixItems(PlayerInventory inventory, String key, ItemStack skull, YamlConfiguration fileItems1, Player player) {
    	String value = fileItems1.getString("items." + key);
        for (int i = 0; i < inventory.getSize(); i++) {
            //get the ItemStack at slot i
            ItemStack itm = inventory.getItem(i);
            int itemAmount = itm.getAmount();
            //make sure the item is not null, and check if it's equal to skull
            if (skull == itm) {
                NBTItem nbti = new NBTItem(itm);
                NBTCompound skullCpd = nbti.addCompound("SkullOwner");
                NBTListCompound texture = skullCpd.addCompound("Properties").getList("textures", NBTType.NBTTagCompound).addCompound();
                String textureString = texture.toString();
                if (value == textureString) {
                	//set the item in the player's inventory at slot i to "itm" if the amount
                    //is > 0, and to null if it is <= 0
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "sf give" + player + key + itemAmount);
                    player.sendMessage(ChatColor.AQUA + "Your item" + ChatColor.RED + key + ChatColor.AQUA + "is fixed.");
                    Bukkit.getLogger().info("Fixed player " + player + "'s item" + key + ".");
                    inventory.setItem(i, null);
                    //update the player's inventory
                    player.updateInventory();
                    break;
                }
            }
        }
    }


    @Override
    public void onEnable() {
        getLogger().info(ChatColor.DARK_RED + "SlimefunFix loaded.");
        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }
            File file = new File(getDataFolder(), "items.yml");
            if (!file.exists()) {
                getLogger().info("Items.yml not found, creating!");
                file.createNewFile();
                saveDefaultConfig();
            } else {
                getLogger().info("Items.yml found, loading!");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("sffix")) { // If the player typed /sffix then do the following
            // If the command has no args
            if (args.length == 0) {
                //If the sender is a player
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    // If the player has the permission node sffix.use
                    if (player.hasPermission("sffix.use")) {
                        // Setting the variables
                        // itemAmount, inventory, skull(old glitchedItem), skullMeta, nbti, skullCpd, file, fileItems1
                        PlayerInventory inventory = player.getInventory();
                        File file = new File(getDataFolder(), "items.yml");
                        new YamlConfiguration();
                        YamlConfiguration fileItems1 = YamlConfiguration.loadConfiguration(file);
                        ItemStack skull = new ItemStack(Material.SKULL_ITEM);
                        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
                        // If the player has skull and its display name equals to CSCoreLib
                        if (inventory.contains(skull) && skullMeta.getOwningPlayer().toString().equals("CSCoreLib's Head")) {
                            for (String key : fileItems1.getConfigurationSection("items").getKeys(false)) {
                            	fixItems (inventory, key, skull, fileItems1, player);
                                return true;
                            }
                            return true;
                        } else {
                            player.sendMessage(ChatColor.DARK_RED + "Couldn't find any items to fix!");
                            if (getConfig().getString("debug").equalsIgnoreCase("true")) {
                                Bukkit.getLogger().info("Tried to fix player " + player + "'s items but none found.");
                            } else {
                                Bukkit.getLogger().info("Tried to fix items but none found.");
                            }
                        }
                    } else {
                        player.sendMessage(ChatColor.DARK_RED + "You do not have enough permissions to perform this command.");
                        return false;
                    }
                } else {
                    getLogger().warning(ChatColor.DARK_RED + "This command can only be applied in game!");
                    return false;
                }
            }
            // If the 1st arg is "help"
            if (args[0] == "help") {
                // If the sender is a player
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    // If player has the permission node sffix.help
                    if (player.hasPermission("sffix.help")) {
                        String help1 = "/sffix to fix your items.";
                        player.sendMessage(ChatColor.AQUA + help1);
                        return true;
                    } else {
                        player.sendMessage(ChatColor.DARK_RED + "You do not have enough permissions to perform this command.");
                        return false;
                    }
                } else {
                    Bukkit.getLogger().warning(ChatColor.DARK_RED + "This command can only be applied in game!");
                    return false;
                }
            }
            // If the 1st arg is "reload"
            if (args[0] == "reload") {
                // If the sender is a player
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    // If player has the permission node sffix.reload
                    if (player.hasPermission("sffix.reload")) {
                        reloadConfig();
                        player.sendMessage(ChatColor.DARK_GREEN + "Plugin reloaded.");
                        return true;
                    } else {
                        player.sendMessage(ChatColor.DARK_RED + "You do not have enough permissions to perform this command.");
                        return false;
                    }
                } else {
                    reloadConfig();
                    return true;
                }
            }
        }

        if (cmd.getName().equalsIgnoreCase("lightningbolt")) {
            // If there is no args
            if (args.length == 0) {
                // If the sender is a player
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    // If the player has the permission node sffix.use
                    if (player.hasPermission("sffix.lightning")) {
                        // Creates a bolt of lightning at a given location. In this case, that location is where the player is looking.
                        // Can only create lightning up to 200 blocks away.
                        player.getWorld().strikeLightning(player.getTargetBlock((Set<Material>) null, 200).getLocation());
                    }
                    return true;
                }
                return true;
            }
        }
        return true;
    }
}