package fr.evocraft.hikabrain.listeners;

import fr.evocraft.hikabrain.HikaBrain;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if(HikaBrain.playersconnect.contains(p)){
            e.setQuitMessage("[HikaBrain] "+p.getName()+" à quitté la partie !");
            HikaBrain.playersconnect.remove(p);
        }
        e.setQuitMessage(null);
    }
}
