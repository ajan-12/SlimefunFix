package me.ajan12.slimefunfix;

import io.github.thebusybiscuit.cscorelib2.config.Config;
import org.bukkit.ChatColor;

class Messages {

    static void setupMessages(Config config) {
        for (final Message message : Message.values()) {
            final String newMessage = config.getString("messages." + message.name().toLowerCase().replaceAll("_", "-").replaceAll("ı", "i"));
            if (newMessage != null) message.setMessage(newMessage);
        }
    }

    public enum Message {
        OUTDATED_SLIMEFUN("You are using an outdated version of Slimefun!"),

        GUI_ERROR("An error occurred while trying to create the GUI."),
        GUI_INPUT(ChatColor.AQUA + "Put your broken items here."),
        GUI_OUTPUT(ChatColor.RED + "Take your fixed items from here."),
        GUI_HELP_NAME(ChatColor.YELLOW + "How to use this?"),
        GUI_HELP_LORE_1(ChatColor.AQUA + "Put a broken slimefun machine inside."),
        GUI_HELP_LORE_2(ChatColor.DARK_AQUA + "Example broken slimefun machine:"),

        RELOAD_BEGIN("Plugin reload is starting."),
        RELOAD_SUCCESS("Successfully reloaded the plugin."),

        COMMAND_DESCRIPTION("Opens a gui where players can fix their broken slimefun items."),
        COMMAND_RELOAD(ChatColor.GREEN + "Reloading the plugin."),
        COMMAND_HELP_1(ChatColor.YELLOW + "/slimefunfix help" + ChatColor.GREEN + " to see how to fix your broken slimefun items."),
        COMMAND_HELP_2(ChatColor.YELLOW + "/slimefunfix gui" + ChatColor.GREEN + " to fix your broken slimefun items."),
        COMMAND_FAIL_1(ChatColor.DARK_RED + "An error occurred while trying to create the GUI."),
        COMMAND_FAIL_2(ChatColor.DARK_RED + "Please report this to an Administrator."),
        PERMISSION_FAIL(ChatColor.DARK_RED + "You do not have permission to do this."),

        COMMAND_SUCCESS_DEBUG("Giving player %PLAYER%, %AMOUNT% amount of %ITEM%");

        private String message;

        Message(String defaultMessage) {
            this.message = ChatColor.translateAlternateColorCodes('&', defaultMessage);
        }

        public void setMessage(String message) { this.message = ChatColor.translateAlternateColorCodes('&', message); }
        public String getMessage() { return message; }
    }
}
