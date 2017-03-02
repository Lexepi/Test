package fr.evocraft.hikabrain.listeners;

import fr.evocraft.hikabrain.HikaBrain;
import fr.evocraft.hikabrain.HikaBrainState;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    int task;

    private HikaBrain main;

    public PlayerJoin(HikaBrain pl){
        this.main = pl;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(!HikaBrain.isState(HikaBrainState.WAITING)){
            p.sendMessage("[HikaBrain] La partie a déjà commencé");
            p.setGameMode(GameMode.SPECTATOR);
        }
        if(HikaBrain.playersconnect.size() <= 4){
            HikaBrain.playersconnect.add(p);
            e.setJoinMessage("[HikaBrain] "+p.getName()+" à rejoint la partie ! ("+HikaBrain.playersconnect.size()+"/4)");
            if(HikaBrain.playersconnect.size() == 1){
                this.task = Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
                     int timer = 16;

                    @Override
                    public void run() {
                        if(HikaBrain.playersconnect.size() == 1) {
                            timer--;
                            if(this.timer == 15 || this.timer == 10 || this.timer == 5 || this.timer == 4 || this.timer == 3 || this.timer == 2){
                                Bukkit.broadcastMessage("[HikaBrain] La partie commence dans "+timer+" secondes");
                            }
                            if(this.timer == 1){
                                Bukkit.broadcastMessage("[HikaBrain] La partie commence dans 1 seconde");
                            }
                            if(this.timer == 0){
                                Bukkit.getScheduler().cancelTask(task);
                                this.timer = 16;
                                Bukkit.broadcastMessage("[HikaBrain] La partie commence... Maintenant");



                            }
                        }else{
                            Bukkit.broadcastMessage("[HikaBrain] Il manque un/des joueurs, la partie se relancera lorsque le nombre de joueurs se atteint !");
                            Bukkit.getScheduler().cancelTask(task);
                            this.timer = 16;
                        }
                    }
                },20, 20);
            }
        }

    }
}
