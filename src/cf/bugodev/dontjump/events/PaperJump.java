package cf.bugodev.dontjump.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;

import cf.bugodev.dontjump.DontJump;

public class PaperJump implements Listener {
	@EventHandler
	public void onJump(PlayerJumpEvent event) {
		if (DontJump.isEnabled == true) {
			Player player = (Player) event.getPlayer();
			player.damage(0.2);
			player.setHealth(1);
		}
	}
}
