package cf.bugodev.dontjump.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import cf.bugodev.dontjump.DontJump;

public class Jump implements Listener {
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
    	if (DontJump.isEnabled == true) {
    		Player player = (Player) event.getPlayer();
    		Vector velocity = player.getVelocity();
    		if (velocity.getY() > 0) {
    			double jumpVelocity = (double) 0.42F;
    			PotionEffect jumpPotion = player.getPotionEffect(PotionEffectType.JUMP);
    			if (jumpPotion != null) {
    				jumpVelocity += (double) ((float) jumpPotion.getAmplifier() + 1) * 0.1F;
    			}

    			if (player.getLocation().getBlock().getType() != Material.LADDER && Double.compare(velocity.getY(), jumpVelocity) == 0) {
	    			player.damage(0.2);
	    			player.setHealth(1);
	    		}
	        }
	    }
	}
}
