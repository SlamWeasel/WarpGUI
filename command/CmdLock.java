package de.SlamWeasel.WarpGUI.command;

import de.SlamWeasel.WarpGUI.util.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;

public class CmdLock implements Listener, CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(cmd.getName().equalsIgnoreCase("ignoreGUI"))
        {
            if(sender instanceof Player)
            {
                Player p = (Player)sender;
                if(p.hasPermission("warpgui.unlock"))
                {
                    File file = new File("plugins/WarpGUI/Config.yml");
                    YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
                    if(args.length == 0)
                    {
                        if(cfg.getBoolean("Player." + p.getName() + ".on"))
                        {
                            cfg.set("Player." + p.getName() + ".on", false);
                            Util.save(cfg, file);
                            p.sendMessage("§aDu kannst dich nun §l§4nicht mehr §r§aim Inventar bewegen");
                        }
                        else if(!(cfg.getBoolean("Player." + p.getName() + ".on")))
                        {
                            cfg.set("Player." + p.getName() + ".on", true);
                            Util.save(cfg, file);
                            p.sendMessage("§aDu kannst dich nun im Inventar bewegen");
                        }
                    }
                    else if (args[0].equalsIgnoreCase("on"))
                    {
                        cfg.set("Player." + p.getName() + ".on", true);
                        Util.save(cfg, file);
                        p.sendMessage("§aDu kannst dich nun im Inventar bewegen");
                    }
                    else if(args[0].equalsIgnoreCase("off"))
                    {
                        cfg.set("Player." + p.getName() + ".on", false);
                        Util.save(cfg, file);
                        p.sendMessage("§aDu kannst dich nun §l§4nicht mehr §r§aim Inventar bewegen");
                    }
                    else p.sendMessage("§cBenutze den Command folgendermaßen: §d/ignoregui [on/off]");
                }
                else p.sendMessage("§cDu hast nicht die Rechte, das zu tun!");
            }
        }return false;
    }

    @EventHandler
    public void onFirstJoin(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();
        String beenhere = "Player." + p.getName() + ".beenhere";
        File file = new File("plugins/WarpGUI/Config.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        if(!cfg.getBoolean(beenhere) || cfg.getString(beenhere) == null)
        {
            cfg.set(beenhere, true);
            cfg.set("Player." + p.getName() + ".on", false);
        }
        Util.save(cfg, file);
    }
}
