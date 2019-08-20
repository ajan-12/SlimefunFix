package top.ageofelysian.slimefunfix;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class SlimefunFix extends JavaPlugin implements CommandExecutor, Listener {

    private static SlimefunFix instance;

    private int rows;
    private String inventoryName = ChatColor.RED + "Put your broken sf items inside.";

    @Override
    public void onEnable() {
        if (Bukkit.getServer().getPluginManager().getPlugin("CS-CoreLib") == null || Bukkit.getServer().getPluginManager().getPlugin("Slimefun") == null) {
            Bukkit.getLogger().warning("The plugins, CS-CoreLib and Slimefun are needed for this plugin!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        instance = this;
        saveDefaultConfig();

        rows = getConfig().getInt("gui-row-amount");

        getCommand("slimefunfix").setExecutor(this);
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 1) {
                switch (args[0]) {
                    case "help":
                        String pluginTag = ChatColor.WHITE + "[" + ChatColor.DARK_AQUA + "SFFIX" + ChatColor.WHITE + "] ";
                        p.sendMessage(pluginTag + ChatColor.GREEN + "Put the broken Slimefun head blocks that has a texture inside and your fixed items will appear in your inventory or will be dropped. Any undefined item will not be touched. When you close the gui the items that were inside may get lost.");
                        p.sendMessage(pluginTag + ChatColor.GREEN + "Please note that the broken items that may have the same texture wth another sf machine (f.e capacitors and gps transmitters) will give you the lowest possible item.");
                        return true;
                    case "gui":
                        Inventory inventory = Bukkit.createInventory(null, rows * 9, inventoryName);
                        p.openInventory(inventory);
                        return true;
                    default:
                        return false;
                }
            } else if (args.length == 0){
                String pluginTag = ChatColor.WHITE + "[" + ChatColor.DARK_AQUA + "SFFIX" + ChatColor.WHITE + "] ";
                p.sendMessage(pluginTag + ChatColor.YELLOW + "/slimefunfix help " + ChatColor.GREEN + " to see how to fix your broken slimefun items.");
                p.sendMessage(pluginTag + ChatColor.YELLOW + "/slimefunfix gui " + ChatColor.GREEN + " to fix your broken slimefun items.");
                return true;
            } else return false;
        } else {
            sender.sendMessage("This command can only be applied in-game!");
            return true;
        }
    }

    @EventHandler
    public void onInventoryPutItemClick(InventoryClickEvent event) {
        //Doing this thing async because, server crashes otherwise.
        SlimefunFixTask task = new SlimefunFixTask(event.getWhoClicked(), event.getInventory());
        Bukkit.getScheduler().runTaskAsynchronously(this, task);
    }

    @EventHandler
    public void onInventoryPutItemDrag(InventoryDragEvent event) {
        //Doing this thing async because, server crashes otherwise.
        SlimefunFixTask task = new SlimefunFixTask(event.getWhoClicked(), event.getInventory());
        Bukkit.getScheduler().runTaskAsynchronously(this, task);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Inventory gui = event.getInventory();
        Inventory inv = event.getPlayer().getInventory();


    }

    public static SlimefunFix getInstance() {return instance;}
}