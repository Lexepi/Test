package fr.evocraft.hikabrain;

import fr.evocraft.hikabrain.listeners.PlayerJoin;
import fr.evocraft.hikabrain.listeners.PlayerQuit;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class HikaBrain extends JavaPlugin{

    private static HikaBrainState current;

    public static List<Player> playersconnect = new ArrayList<>();

    @Override
    public void onEnable() {

        this.setState(HikaBrainState.WAITING);

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerJoin(this), this);
        pm.registerEvents(new PlayerQuit(), this);

    }

    public static void setState(HikaBrainState state){current = state;}

    public static boolean isState(HikaBrainState state){
        return current == state;
    }
}
