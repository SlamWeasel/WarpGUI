package de.SlamWeasel.WarpGUI.main;

import de.SlamWeasel.WarpGUI.broadcast.Broadcast;
import de.SlamWeasel.WarpGUI.command.CmdLock;
import de.SlamWeasel.WarpGUI.listener.ArmorStandListener;
import de.SlamWeasel.WarpGUI.listener.GUIListener;
import javafx.scene.paint.Color;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
    private static Main plugin;

    public void onEnable()
    {
        plugin = this;

        this.getCommand("ignoregui").setExecutor(new CmdLock());

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new GUIListener(), this);
        pm.registerEvents(new ArmorStandListener(), this);
        pm.registerEvents(new CmdLock(), this);

        Broadcast.Broadcasts();

        System.out.println(Color.GREEN + "WarpGUI wurde initialisiert");
    }

    public static  Main getPlugin()
    {
        return plugin;
    }
}
