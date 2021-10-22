package de.SlamWeasel.WarpGUI.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class ArmorStandListener implements Listener
{
    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent e)
    {
        Player p = e.getPlayer();
        Entity en = e.getRightClicked();

        if(en.getType() == EntityType.ARMOR_STAND)
        {
            if(en.getCustomName().equals("ShowLobby betreten") || en.getCustomName().equals("Zurück zur ShowLobby"))
            {
                if( p.getEquipment().getItemInMainHand().getType() == Material.NETHER_STAR ||
                        p.getEquipment().getItemInMainHand().getType() == Material.MAGMA_CREAM ||
                        p.getEquipment().getItemInMainHand().getType() == Material.SHEARS)
                    return;
                else
                {
                    String world = p.getLocation().getWorld().getName();
                    World lob = Bukkit.getServer().getWorld("comShowLobby");

                    switch(world)
                    {
                        case "showJump":        p.teleport(new Location(
                                lob,
                                -11.5,
                                83,
                                -184.5,
                                -90, 0));break;
                        case "showDryadenCity": p.teleport(new Location(
                                lob,
                                -11.5,
                                83,
                                -147.5,
                                -90, 0));break;
                        case "showVaceLobby":   p.teleport(new Location(
                                lob,
                                -11.5,
                                83,
                                -184.5,
                                90, 0));break;
                        case "showTheater":     p.teleport(new Location(
                                lob,
                                -11.5,
                                83,
                                -184.5,
                                90, 0));break;
                        case "showDMCLobby":    p.teleport(new Location(
                                lob,
                                -11.5,
                                83,
                                -184.5,
                                -90, 0));break;
                        case "showTimeline":    p.teleport(new Location(
                                lob,
                                -11.5,
                                83,
                                -184.5,
                                90, 0));break;
                        case "showAsgard":      p.teleport(new Location(
                                lob,
                                -11.5,
                                83,
                                -184.5,
                                -90, 0));break;
                        case "comLobby":        p.teleport(new Location(
                                lob,
                                lob.getSpawnLocation().getBlockX() + 0.5,
                                lob.getSpawnLocation().getBlockY(),
                                lob.getSpawnLocation().getBlockZ() + 0.5,
                                -180, 0));break;
                        default:                p.teleport(new Location(
                                lob,
                                lob.getSpawnLocation().getBlockX() + 0.5,
                                lob.getSpawnLocation().getBlockY(),
                                lob.getSpawnLocation().getBlockZ() + 0.5,
                                -180, 0));break;
                    }
                }
            }
            if(en.getCustomName().equals("1. Plotwelt betreten"))
            {
                if( p.getItemInHand().getType() == Material.NETHER_STAR ||
                        p.getItemInHand().getType() == Material.MAGMA_CREAM ||
                        p.getItemInHand().getType() == Material.SHEARS)
                    return;
                else
                    p.teleport(new Location(
                            Bukkit.getServer().getWorld("Plot01"),
                            Bukkit.getServer().getWorld("Plot01").getSpawnLocation().getBlockX() + 0.5,
                            Bukkit.getServer().getWorld("Plot01").getSpawnLocation().getBlockY(),
                            Bukkit.getServer().getWorld("Plot01").getSpawnLocation().getBlockZ() + 0.5,
                            -180, 0));
            }

            if(en.getCustomName().equals("2. Plotwelt betreten"))
            {
                if(p.hasPermission("warpgui.novize"))
                {
                    if( p.getItemInHand().getType() == Material.NETHER_STAR ||
                            p.getItemInHand().getType() == Material.MAGMA_CREAM ||
                            p.getItemInHand().getType() == Material.SHEARS)
                        return;
                    else
                        p.teleport(new Location(
                                Bukkit.getServer().getWorld("Plot02"),
                                Bukkit.getServer().getWorld("Plot02").getSpawnLocation().getBlockX() + 0.5,
                                Bukkit.getServer().getWorld("Plot02").getSpawnLocation().getBlockY(),
                                Bukkit.getServer().getWorld("Plot02").getSpawnLocation().getBlockZ() + 0.5,
                                -180, 0));
                }
                else
                {
                    e.setCancelled(true);
                    p.sendMessage("§cDu hast nicht die Berechtigung dazu, du musst mindestens den Rang §bNovize §chaben!");
                }
            }

            if(en.getCustomName().equals("3. Plotwelt betreten")) {
                if (p.hasPermission("warpgui.adept")) {
                    if (p.getItemInHand().getType() == Material.NETHER_STAR ||
                            p.getItemInHand().getType() == Material.MAGMA_CREAM ||
                            p.getItemInHand().getType() == Material.SHEARS)
                        return;
                    else
                        p.teleport(new Location(
                                Bukkit.getServer().getWorld("Plot03"),
                                Bukkit.getServer().getWorld("Plot03").getSpawnLocation().getBlockX() + 0.5,
                                Bukkit.getServer().getWorld("Plot03").getSpawnLocation().getBlockY(),
                                Bukkit.getServer().getWorld("Plot03").getSpawnLocation().getBlockZ() + 0.5,
                                -180, 0));
                }
                else
                {
                    e.setCancelled(true);
                    p.sendMessage("§cDu hast nicht die Berechtigung dazu, du musst mindestens den Rang §3Adept §chaben!");
                }
            }

            if(en.getCustomName().equals("Zurück zum Spawn"))
            {
                if (p.getItemInHand().getType() == Material.NETHER_STAR ||
                        p.getItemInHand().getType() == Material.MAGMA_CREAM ||
                        p.getItemInHand().getType() == Material.SHEARS)
                    return;
                else
                    p.teleport(new Location(
                            Bukkit.getServer().getWorld("comLobby"),
                            Bukkit.getServer().getWorld("comLobby").getSpawnLocation().getBlockX() + 0.5,
                            Bukkit.getServer().getWorld("comLobby").getSpawnLocation().getBlockY(),
                            Bukkit.getServer().getWorld("comLobby").getSpawnLocation().getBlockZ() + 0.5,
                            90, 0));
            }else return;
        }else return;
    }
}
