package cf.bugodev.dontjump;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import cf.bugodev.dontjump.events.Jump;
import cf.bugodev.dontjump.events.PaperJump;

public class DontJump extends JavaPlugin implements Listener {
	
	FileConfiguration config;
	File cfile;
	
	public static boolean isEnabled;
	
	@Override
	public void onEnable() {
		config = getConfig();
		config.options().copyDefaults(true);
		saveDefaultConfig();
		cfile = new File(getDataFolder(), "config.yml");
		isEnabled = false;
		PluginManager pm = getServer().getPluginManager();
		if(this.getServer().getName().toLowerCase().contains("paper")) {
			pm.registerEvents(new PaperJump(), this);
		} else {
			pm.registerEvents(new Jump(), this);
		}
		getLogger().info("DontJump has been successfully enabled!");
	}
	@Override
	public void onDisable() {
		isEnabled = false;
		getLogger().info("DontJump has been successfully disabled!");
	}
	
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		String prefix = this.getConfig().getString("prefix");
		getConfig();
		if(label.equalsIgnoreCase("dontjump")) {
			if (args.length == 0) {
				sender.sendMessage("[P]&r &cInvalid Syntax, Please use '/dontjump <on|off|reload>'".replace("[P]", prefix).replace("&", "§"));
			} else {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.hasPermission("dontjump.toggle") || player.hasPermission("dontjump.reload")) {
					if(args[0].equalsIgnoreCase("on")) {
						isEnabled = true;
						player.sendMessage("[P]&r &aTurned on the challange!".replace("[P]", prefix).replace("&", "§"));
					} else if (args[0].equalsIgnoreCase("off")) {
						isEnabled = false;
						player.sendMessage("[P]&r &cTurned off the challange!".replace("[P]", prefix).replace("&", "§"));
					} else if (args[0].equalsIgnoreCase("reload")) {
						if (player.hasPermission("dontjump.reload")) {
							this.reloadConfig();
							player.sendMessage("[P]&r [R]".replace("[P]", getConfig().getString("prefix")).replace("[R]", getConfig().getString("reload-message")).replace("&", "§"));
							getLogger().info(player.getName() + " Reloaded the config!");
						} else {
							player.sendMessage("[P]&r &cYou don't have permission to reload the config.".replace("&", "§"));
						}
					} else {
						player.sendMessage("[P]&r &cInvalid Syntax, Please use '/dontjump <on|off|reload>'!".replace("[P]", prefix).replace("&", "§"));
					}
				}
			} else {
				sender.sendMessage("[P]&r &cConsole can't execute this command!".replace("[P]", prefix).replace("&", "§"));
			}
			}
		}
		if(label.equalsIgnoreCase("heal")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.hasPermission("dontjump.heal")) {
					player.setHealth(player.getMaxHealth());
					player.sendMessage("[P]&r &6Your health has been restored!".replace("[P]", prefix).replace("&", "§"));
				} else {
					player.sendMessage("[P]&r &cYou don't have permission to execute this command!".replace("[P]", prefix).replace("&", "§"));
				}
			} else {
				sender.sendMessage("[P]&r &cConsole can't execute this command!".replace("[P]", prefix).replace("&", "§"));
			}
		}
		return false;
	}
	
	
	
}