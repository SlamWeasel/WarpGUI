package de.SlamWeasel.WarpGUI.listener;

import de.SlamWeasel.WarpGUI.util.BookUtil;
import de.SlamWeasel.WarpGUI.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class GUIListener implements Listener
{
    private int[] Reihe = {0,9,18,27,36,45,54,63,72,81};

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();
        Inventory I = p.getInventory();
        ItemStack GUI = new ItemStack(Material.COMPASS);
        ItemMeta GUIMeta = GUI.getItemMeta();
        GUIMeta.setDisplayName("§6Warp-GUI");
        GUI.setItemMeta(GUIMeta);
        p.teleport(new Location(Bukkit.getServer().getWorld("comLobby"), Bukkit.getServer().getWorld("comLobby").getSpawnLocation().getX() + 0.5D, Bukkit.getServer().getWorld("comLobby").getSpawnLocation().getY(), Bukkit.getServer().getWorld("comLobby").getSpawnLocation().getZ() + 0.5D));

        if(I.contains(Material.COMPASS))
        {
            for(int i = 0; i != 45; i++)
                I.remove(Material.COMPASS);
            I.setItem(8, GUI);
        }
        else
            I.setItem(8, GUI);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e)
    {
        Player p = e.getPlayer();
        ItemStack GUI = new ItemStack(Material.COMPASS);
        Util.setName(GUI, "§6Warp-GUI");
        p.getInventory().remove(GUI);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e)
    {
        Player p = e.getPlayer();
        if(e.getItem() == null)
            return;
        if(e.getItem().getType() == Material.COMPASS)
        {
            if(e.getItem().getItemMeta().getDisplayName() == null)
                return;
            if(e.getItem().getItemMeta().getDisplayName().equals("§6Warp-GUI"))
            {
                Inventory main = Bukkit.createInventory(null, Reihe[1], "§8Warp-GUI");
                ItemStack left = new ItemStack(Material.ENDER_EYE, 1);
                Util.setName(left, "§2§oShowLobby");
                ItemStack mid = new ItemStack(Material.GRASS, 1);
                Util.setName(mid, "§2§oPlotserver");
                ItemStack right = new ItemStack(Material.LAVA_BUCKET, 1);
                Util.setName(right, "§2§oInfo");
                ItemStack fill = new ItemStack(Material.LEGACY_STAINED_GLASS_PANE, 1, (short) 8);
                Util.setName(fill, " ");

                for(int I = 0; I != Reihe[1]; I++)
                    main.setItem(I, fill);
                main.setItem(1, left);
                main.setItem(4, mid);
                main.setItem(7, right);

                p.openInventory(main);
            }else return;
        }else return;
    }

    @EventHandler
    public void invClick(InventoryClickEvent e)
    {
        Player p = (Player) e.getWhoClicked();
        File file = new File("plugins/WarpGUI/Config.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        if(cfg.getBoolean("Player." + p.getName() + ".on"))
            return;
        if(e.getClickedInventory() == null)
            return;
        if(e.getClickedInventory() != null)
            e.setCancelled(true);
        if(e.getInventory().getHolder() == e.getWhoClicked())
        {
            if(cfg.getBoolean("Player." + p.getName() + ".on"))
                e.setCancelled(false);
            else
                e.setCancelled(true);
        }
        if( /*(e.getClickedInventory().getType().name().equals("§8Warp-GUI")) ||
                (e.getClickedInventory().getType().name().equals("§8Plotserver")) ||
                (e.getClickedInventory().getType().name().equals("§8Info")) ||
                (e.getClickedInventory().getType().name().equals("§8Team")) ||
                (e.getClickedInventory().getType().name().equals("§8Rechte"))*/true)
        {
            if(e.getCurrentItem().getType() == Material.LEGACY_STAINED_GLASS_PANE)
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals(" "))
                    e.setCancelled(true);
            if(e.getCurrentItem().getType() == Material.ENDER_EYE)
            {
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§2§oShowLobby"))
                {
                    e.setCancelled(true);
                    p.teleport(new Location(
                            Bukkit.getServer().getWorld("comShowLobby"),
                            Bukkit.getServer().getWorld("comShowLobby").getSpawnLocation().getBlockX() + 0.5,
                            Bukkit.getServer().getWorld("comShowLobby").getSpawnLocation().getBlockY(),
                            Bukkit.getServer().getWorld("comShowLobby").getSpawnLocation().getBlockZ() + 0.5,
                            -180, 0));
                }else return;
            }
            if(e.getCurrentItem().getType() == Material.WOODEN_PICKAXE)
            {
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§2§oPlotserver01"))
                {
                    e.setCancelled(true);
                    p.teleport(new Location(
                            Bukkit.getServer().getWorld("Plot01"),
                            Bukkit.getServer().getWorld("Plot01").getSpawnLocation().getBlockX() + 0.5,
                            Bukkit.getServer().getWorld("Plot01").getSpawnLocation().getBlockY(),
                            Bukkit.getServer().getWorld("Plot01").getSpawnLocation().getBlockZ() + 0.5,
                            -180, 0));
                }
                else return;
            }
            if(e.getCurrentItem().getType() == Material.IRON_PICKAXE)
            {
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§2§oPlotserver02"))
                {
                    if(p.hasPermission("warpgui.novize"))
                    {
                        e.setCancelled(true);
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
                        p.sendMessage("§cDu hast nicht die Berechtigung dazu, du musst mindestens den Rang §bNovize §chaben");
                    }
                }else return;
            }
            if(e.getCurrentItem().getType() == Material.GOLDEN_PICKAXE)
            {
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§2§oPlotserver03"))
                {
                    if(p.hasPermission("warpgui.adept"))
                    {
                        e.setCancelled(true);
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
                        p.sendMessage("§cDu hast nicht die Berechtigung dazu, du musst mindestens den Rang §3Adept §chaben");
                    }
                }else return;
            }
            if(e.getCurrentItem().getType() == Material.DARK_OAK_DOOR)
            {
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cZurück"))
                {
                    e.setCancelled(true);
                    Inventory main = Bukkit.createInventory(null, Reihe[1], "§8Warp-GUI");
                    ItemStack left = new ItemStack(Material.ENDER_EYE, 1);
                    Util.setName(left, "§2§oShowLobby");
                    ItemStack mid = new ItemStack(Material.GRASS, 1);
                    Util.setName(mid, "§2§oPlotserver");
                    ItemStack right = new ItemStack(Material.LAVA_BUCKET, 1);
                    Util.setName(right, "§2§oInfo");
                    ItemStack fill = new ItemStack(Material.LEGACY_STAINED_GLASS_PANE, 1, (short) 8);
                    Util.setName(fill, " ");

                    for(int I = 0; I != Reihe[1]; I++)
                        main.setItem(I, fill);
                    main.setItem(1, left);
                    main.setItem(4, mid);
                    main.setItem(7, right);

                    p.openInventory(main);
                }else return;
            }
            if( (e.getCurrentItem().getType() == Material.GRASS) && (e.getCurrentItem().getItemMeta().getDisplayName().equals("§2§oPlotserver")) )
            {
                e.setCancelled(true);
                Inventory Plots = Bukkit.createInventory(null, Reihe[3], "§8Plotserver");
                ItemStack fill = new ItemStack(Material.LEGACY_STAINED_GLASS_PANE, 1, (short) 8);
                Util.setName(fill, " ");
                ItemStack plotI = new ItemStack(Material.WOODEN_PICKAXE, 1);
                ItemMeta plotIMeta = plotI.getItemMeta();
                plotIMeta.setDisplayName("§2§oPlotserver01");
                plotIMeta.setLore(Arrays.asList("Wenn man hier seinen Plot erfolgreich abschließt", "wird man zum Novizen befördert und erhält Zugriff", "auf den zweiten Plotserver mit mehr Rechten!"));
                plotI.setItemMeta(plotIMeta);
                ItemStack plotII = new ItemStack(Material.IRON_PICKAXE, 1);
                ItemMeta plotIIMeta = plotII.getItemMeta();
                plotIIMeta.setDisplayName("§2§oPlotserver02");
                plotIIMeta.setLore(Arrays.asList("Wenn man hier seinen Plot erfolgreich abschließt", "wird man zum Novizen befördert und erhält Zugriff", "auf den dritten Plotserver mit mehr Rechten!"));
                plotII.setItemMeta(plotIIMeta);
                ItemStack plotIII = new ItemStack(Material.GOLDEN_PICKAXE, 1);
                ItemMeta plotIIIMeta = plotII.getItemMeta();
                plotIIIMeta.setDisplayName("§2§oPlotserver03");
                plotIIIMeta.setLore(Arrays.asList("Wenn man hier seinen Plot erfolgreich abschließt", "wir man zum Meister befördert und hat jederzeit die", "Möglichkeit das Bauteam Dryadon zu betreten, ", "außerdem hat man Zugriff auf außergewöhnliche", "Befehle!"));
                plotIII.setItemMeta(plotIIIMeta);
                ItemStack back = new ItemStack(Material.DARK_OAK_DOOR, 1);
                Util.setName(back, "§cZurück");

                for(int I = 0; I != Reihe[3]; I++)
                    Plots.setItem(I, fill);
                Plots.setItem(Reihe[3]/2-3,   plotI);
                Plots.setItem(Reihe[3]/2,     plotII);
                Plots.setItem(Reihe[3]/2+3,   plotIII);
                Plots.setItem(Reihe[3]-1,     back);

                p.openInventory(Plots);
            }
            if(e.getCurrentItem().getType() == Material.LAVA_BUCKET && e.getCurrentItem().getItemMeta().getDisplayName().equals("§2§oInfo"))
            {
                File file01 = new File("plugins/WarpGUI/src/plugin.yml");
                YamlConfiguration cfg01 = YamlConfiguration.loadConfiguration(file01);
                ArrayList<String> list = new ArrayList<String>();

                e.setCancelled(true);
                Inventory Info = Bukkit.createInventory(null, Reihe[4], "§8Info");
                ItemStack fill = new ItemStack(Material.LEGACY_STAINED_GLASS_PANE,1, (short) 8);
                Util.setName(fill, " ");
                ItemStack back = new ItemStack(Material.DARK_OAK_DOOR, 1);
                Util.setName(back, "§cZurück");
                ItemStack allgemein = new ItemStack(Material.WRITTEN_BOOK, 1);
                Util.setBook(allgemein, "§e§lAllgemein", "SlamWeasel");
                ItemStack rechte = new ItemStack(Material.WRITTEN_BOOK, 1);
                Util.setBook(rechte, "§e§lRechte", "Server");
                ItemStack faq = new ItemStack(Material.WRITTEN_BOOK, 1);
                Util.setBook(faq, "§e§lFAQ", "SlamWeasel");
                ItemStack team = new ItemStack(Material.WRITTEN_BOOK, 1);
                Util.setBook(team, "§e§lTeam", "Dryadon");

                /* @author Plugin-Version 2.0.3.18 */

                ItemStack plugin = new ItemStack(Material.NAME_TAG, 1);
                ItemMeta pluginMeta = plugin.getItemMeta();
                pluginMeta.setDisplayName("§6§lPluginVersion");
                pluginMeta.setLore(Arrays.asList("§e2.0.3.18"));
                plugin.setItemMeta(pluginMeta);

                for(int I = 0; I != Reihe[4]; I++)
                    Info.setItem(I, fill);
                Info.setItem(12, rechte);
                Info.setItem(19, allgemein);
                Info.setItem(23, faq);
                Info.setItem(16, team);
                Info.setItem(0, plugin);
                Info.setItem(Reihe[4]-1, back);
                p.openInventory(Info);
            }
            if(e.getCurrentItem().getType() == Material.WRITTEN_BOOK && e.getCurrentItem().getItemMeta().getDisplayName().equals("§e§lAllgemein"))
            {
                e.setCancelled(true);
                ItemStack allgemein = new ItemStack(Material.WRITTEN_BOOK, 1);
                BookMeta allgemeinMeta = (BookMeta) allgemein.getItemMeta();
                allgemeinMeta.setDisplayName("§e§lAllgemein");
                allgemeinMeta.setTitle("§e§lAllgemein");
                allgemeinMeta.setAuthor("Slamweasel");

                ArrayList<String> pages = new ArrayList<String>();
                pages.add(0, "§c§lAllgemein\n" +
                        "\n§r" +
                        "Hey und Willkommen auf dem §2§lDerBauserver§r\n§0Building Server.\n" +
                        "Wir sind das Bauteam §2Dryadon§r §0und wollen dir hier eine Plattform für deine Kreativität bieten."
                );
                pages.add(1,"\n" +
                        "Du kannst dir in unseren Showcase-Welten Inspiration für deine Plots suchen.\n" +
                        "\n" +
                        "Es gibt drei verschiedene Plotserver, auf denen du nacheinander einen Plot einsenden kannst. Je weiter du kommst, desto mehr"
                );
                pages.add(2, "\n" +
                        "Rechte und Zugriff auf Plugins erhältst du, außerdem wird dein Rang auf dem Server erhöht und du kannst nach Abschluss der letzten Plotwelt dem Bauteam beitreten"
                );
                allgemeinMeta.setPages(pages);
                allgemein.setItemMeta(allgemeinMeta);

                p.closeInventory();
                BookUtil.openBook(allgemein, p);
            }
            if(e.getCurrentItem().getType() == Material.WRITTEN_BOOK && e.getCurrentItem().getItemMeta().getDisplayName().equals("§l§7Besucher"))
            {
                e.setCancelled(true);
                ItemStack rechte = new ItemStack(Material.WRITTEN_BOOK, 1);
                BookMeta rechteMeta = (BookMeta) rechte.getItemMeta();
                rechteMeta.setDisplayName("§l§7Besucher");
                rechteMeta.setTitle("§l§7Besucher");
                rechteMeta.setAuthor("Rang I");

                ArrayList<String> pages = new ArrayList<String>();
                pages.add(0, "§c§lRechte Besucher\n" +
                        "\n§r" +
                        "Du kannst §2Plotwelt 1§r \nbetreten.\n" +
                        "\n" +
                        "Du kannst die §2Showwelten§r §0betreten.\n" +
                        "\n" +
                        "Du kannst §2/tpa <Spielername>§r \nAnfragen senden.\n" +
                        "\n" +
                        "Du kannst mit §2/spawn§r §0zum Spawn."
                );
                pages.add(1, "\n" +
                        "Du kannst mit §2/msg <Spielername>§r §0private Nachrichten senden.\n" +
                        "\n" +
                        "WorldEdit Brushes mit Radius <= §25§r §0Blöcken\n" +
                        "\n" +
                        "WorldEdit Selektionen mit <= §21000§r §0Blöcken\n" +
                        "\n" +
                        "Worldedit Aktionsintervall = §21§r" + "\n" + "§0Aktion alle §23§r §0Sekunden"
                );
                pages.add(2, "\n" +
                        "§4Blockverbote:§r\n" +
                        "\n" +
                        "Nasser Schwamm, Lava, Portale, Minecarts, Boote, Lebewesen, TNT, Redstone, alle anderen nicht platzierbaren Items"
                );
                rechteMeta.setPages(pages);
                rechte.setItemMeta(rechteMeta);

                p.closeInventory();
                BookUtil.openBook(rechte, p);
            }
            if(e.getCurrentItem().getType() == Material.WRITTEN_BOOK && e.getCurrentItem().getItemMeta().getDisplayName().equals("§l§bNovize"))
            {
                e.setCancelled(true);
                ItemStack rechte = new ItemStack(Material.WRITTEN_BOOK, 1);
                BookMeta rechteMeta = (BookMeta) rechte.getItemMeta();
                rechteMeta.setDisplayName("§l§bNovize");
                rechteMeta.setTitle("§l§bNovize");
                rechteMeta.setAuthor("Rang II");

                ArrayList<String> pages = new ArrayList<String>();
                pages.add(0, "§c§lRechte Novize\n" +
                        "\n§r" +
                        "§9Alle Rechte der vorherigen Ränge +\n" +
                        "\n§r" +
                        "Du kannst §2Plotwelt 2§r \nbetreten. \n" +
                        "\n" +
                        "Du kannst Voxelsniper verwenden!\n" +
                        "\n" +
                        "Du kannst §2/plot tp <Spielername>§r §0benutzten"
                );
                pages.add(1, "\n" +
                        "WorldEdit Brushes mit Radius <= §27§r §oBlöcken\n" +
                        "\n" +
                        "WorldEdit Selektionen mit <= §25000§r §0Blöcken\n" +
                        "\n" +
                        "Worldedit Aktionsintervall = §21§r\n§0Aktion §2jede§r §0Sekunden"
                );
                pages.add(2, "\n" +
                        "\n" +
                        "Voxelsniper Brushes mit Radius <= §24§r §0Blöcken\n" +
                        "\n" +
                        "Voxelsniper Aktionsintervall = §21§r\n§0Aktion §2jede§r §0Sekunde"
                );
                pages.add(3, "\n" +
                        "§4Blockverbote:§r\n" +
                        "\n" +
                        "Nasser Schwamm, Portale, Minecarts, Lebewesen, TNT, Redstone, alle anderen nicht platzierbaren Items"
                );
                rechteMeta.setPages(pages);
                rechte.setItemMeta(rechteMeta);

                p.closeInventory();
                BookUtil.openBook(rechte, p);
            }
            if(e.getCurrentItem().getType() == Material.WRITTEN_BOOK && e.getCurrentItem().getItemMeta().getDisplayName().equals("§l§3Adept"))
            {
                e.setCancelled(true);
                ItemStack rechte = new ItemStack(Material.WRITTEN_BOOK, 1);
                BookMeta rechteMeta = (BookMeta) rechte.getItemMeta();
                rechteMeta.setDisplayName("§l§3Adept");
                rechteMeta.setTitle("§l§3Adept");
                rechteMeta.setAuthor("Rang III");

                ArrayList<String> pages = new ArrayList<String>();
                pages.add(0, "§c§lRechte Adept\n" +
                        "\n§r" +
                        "§9Alle Rechte der vorherigen Ränge +\n" +
                        "\n§r" +
                        "Du kannst §2Plotwelt 3§r \nbetreten. \n"
                );
                pages.add(1, "\n" +
                        "WorldEdit Brushes mit Radius <= §210§r §oBlöcken\n" +
                        "\n" +
                        "WorldEdit Selektionen mit <= §250000§r §0Blöcken\n" +
                        "\n" +
                        "Worldedit Aktionsintervall = §21§r\n§0Aktion §2jede§r §0Sekunden"
                );
                pages.add(2, "\n" +
                        "\n" +
                        "Voxelsniper Brushes mit Radius <= §26§r §0Blöcken\n" +
                        "\n" +
                        "Voxelsniper Aktionsintervall = §21§r\n§0Aktion §2jede§r §0Sekunde"
                );
                pages.add(3, "\n" +
                        "§4Blockverbote:§r\n" +
                        "\n" +
                        "Nasser Schwamm, Minecarts, Lebewesen, TNT, Redstone, alle anderen nicht platzierbaren Items"
                );
                rechteMeta.setPages(pages);
                rechte.setItemMeta(rechteMeta);

                p.closeInventory();
                BookUtil.openBook(rechte, p);
            }
            if(e.getCurrentItem().getType() == Material.WRITTEN_BOOK && e.getCurrentItem().getItemMeta().getDisplayName().equals("§l§9Meister"))
            {
                e.setCancelled(true);
                ItemStack rechte = new ItemStack(Material.WRITTEN_BOOK, 1);
                BookMeta rechteMeta = (BookMeta) rechte.getItemMeta();
                rechteMeta.setDisplayName("§l§9Meister");
                rechteMeta.setTitle("§l§9Meister");
                rechteMeta.setAuthor("Rang IV");

                ArrayList<String> pages = new ArrayList<String>();
                pages.add(0, "§c§lRechte Meister\n" +
                        "\n§r" +
                        "§9Alle Rechte der vorherigen Ränge +\n" +
                        "\n§r" +
                        "Du kannst §2/tp <Spielername>§r §0benutzen.\n" +
                        "\n" +
                        "Hast du den Meisterrang brauchst du keine Plots mehr zu bauen und kannst"
                );
                pages.add(1, "\n" +
                        "jederzeit dem Bauteam §2Dryadon§r §0beitreten."
                        );
                rechteMeta.setPages(pages);
                rechte.setItemMeta(rechteMeta);

                p.closeInventory();
                BookUtil.openBook(rechte, p);
            }
            if(e.getCurrentItem().getType() == Material.WRITTEN_BOOK && e.getCurrentItem().getItemMeta().getDisplayName().equals("§e§lFAQ"))
            {
                e.setCancelled(true);
                ItemStack faq = new ItemStack(Material.WRITTEN_BOOK, 1);
                BookMeta faqMeta = (BookMeta) faq.getItemMeta();
                faqMeta.setDisplayName("§e§lFAQ");
                faqMeta.setTitle("§e§lFAQ");
                faqMeta.setAuthor("SlamWeasel");

                ArrayList<String> pages = new ArrayList<String>();
                pages.add(0, "\n" +
                        "     §9§l§nFAQ:\n" +
                        "\n§r" +
                        "§0Hier findet ihr Antworten auf Fragen, die sich die Spieler stellen können"
                );
                pages.add(1, "\n" +
                        "§4§lWie kann ich einen Plot bekommen?\n" +
                        "\n§r" +
                        "Entweder man teleportiert sich mit dem Kompass in die 1. Plotwelt und claimt sich einen leeren Plot, also mit grauem Rahmen, mit §2/plot claim§r§0, oder man gibt einfach §2/plot auto§r §0ein."
                );
                pages.add(2, "\n" +
                        "§4§lKann ich auch mit einem Freund zusammen bauen?\n" +
                        "\n§r" +
                        "Nein, dieser Server ist als Vorbau- und Showserver gedacht für einzelne Architekten oder jene, die welche werden wollen."
                );
                pages.add(3, "\n" +
                        "§4§lWie/Wann wird mein Plot bewertet?\n" +
                        "\n§r" +
                        "Die Plots werden in einem Bewertungs-\nlivestream auf §2https://www.twitch.tv/sirzenner§r§0, der planmäßig montagabends um 19:00Uhr beginnt, von Bauteammitgliedern bewertet."
                );
                pages.add(4, "\n" +
                        "§4§lGibt es ein Thema das ich bauen muss?\n" +
                        "\n§r" +
                        "Nein, die Builder, die die Bewertung vollziehen sind erfahren und können die Qualitäten von Plots unabhängig vom Thema bewerten."
                );
                pages.add(5, "\n" +
                        "§4§lKann ich mich auch als Supporter bewerben?\n" +
                        "\n§r" +
                        "Nein, Supporter sind im Moment nicht benötigt"
                );
                pages.add(6, "\n" +
                        "§4§lGehört dieser Server zu DustMC?\n" +
                        "\n§r" +
                        "Nein, wir arbeiten mit DustMC zusammen, und auch unsere Server liegen im selben Netzwerk, weswegen auch DustMC-Nachrichten gestreamt werden, wir sind jedoch nicht von DustMC abhängig."
                );
                faqMeta.setPages(pages);
                faq.setItemMeta(faqMeta);

                p.closeInventory();
                BookUtil.openBook(faq, p);
            }
            if(e.getCurrentItem().getType() == Material.WRITTEN_BOOK && e.getCurrentItem().getItemMeta().getDisplayName().equals("§e§lTeam"))
            {
                e.setCancelled(true);
                Inventory Team = Bukkit.createInventory(null, Reihe[3], "§8Team");
                ItemStack fill = new ItemStack(Material.LEGACY_STAINED_GLASS_PANE, 1, (short) 8);
                Util.setName(fill, " ");
                ItemStack back = new ItemStack(Material.DARK_OAK_DOOR, 1);
                Util.setName(back, "§cZurück");

                for(int I = 0; I != Reihe[3]; I++)
                    Team.setItem(I, fill);
                Team.setItem(4, Util.skull("iron_muffin10", "§n§l§2Dryadon"));
                Team.setItem(9, Util.skull("SlamWeasel", "§6SlamWeasel"));
                Team.setItem(10, Util.skull("SirZenner", "§6SirZenner"));
                Team.setItem(11, Util.skull("Cornflakesminer", "§6Cornflakesminer"));
                Team.setItem(12, Util.skull("leonard1504", "§6leonard1504"));
                Team.setItem(13, Util.skull("Ghasti", "§6Ghasti"));
                Team.setItem(14, Util.skull("EinHonigtee", "§6EinHonigtee"));
                Team.setItem(15, Util.skull("ZyRientic", "§6ZyRientic"));
                Team.setItem(16, Util.skull("zYitzu", "§6zYitzu"));
                Team.setItem(17, Util.skull("7sf", "§67sf"));
                Team.setItem(Reihe[3]-5, back);
                p.openInventory(Team);
            }
            if(e.getCurrentItem().getType() == Material.WRITTEN_BOOK && e.getCurrentItem().getItemMeta().getDisplayName().equals("§e§lRechte"))
            {
                Inventory Rechte = Bukkit.createInventory(null, Reihe[4], "§8Rechte");
                ItemStack fill = new ItemStack(Material.LEGACY_STAINED_GLASS_PANE, 1, (short) 8);
                Util.setName(fill, " ");
                ItemStack back = new ItemStack(Material.DARK_OAK_DOOR, 1);
                Util.setName(back, "§cZurück");
                ItemStack besucher = new ItemStack(Material.WRITTEN_BOOK, 1);
                Util.setBook(besucher, "§l§7Besucher", "Rang I");
                ItemStack novize = new ItemStack(Material.WRITTEN_BOOK, 1);
                Util.setBook(novize, "§l§bNovize", "Rang II");
                ItemStack adept = new ItemStack(Material.WRITTEN_BOOK, 1);
                Util.setBook(adept, "§l§3Adept", "Rang III");
                ItemStack meister = new ItemStack(Material.WRITTEN_BOOK, 1);
                Util.setBook(meister, "§l§9Meister", "Rang IV");

                for(int I = 0; I != Reihe[4]; I++)
                    Rechte.setItem(I, fill);
                Rechte.setItem(19, besucher);
                Rechte.setItem(12, novize);
                Rechte.setItem(23, adept);
                Rechte.setItem(16, meister);
                Rechte.setItem(Reihe[4]-1, back);

                p.openInventory(Rechte);
            }
            if(e.getCurrentItem().getType() == Material.PLAYER_HEAD)
                e.setCancelled(true);
        }
        if( (p.getLocation().getWorld().getName().equals("Plot01")) ||
                (p.getLocation().getWorld().getName().equals("Plot02")))
            if(e.getInventory().getHolder() == e.getWhoClicked())
                e.setCancelled(false);
        if(p.getLocation().getWorld().getName().equals("Plot03"))
            return;
    }

    @EventHandler
    public void atDrop(PlayerDropItemEvent e)
    {
        Player p = e.getPlayer();

        if(p.hasPermission("warpgui.ignore.droplock"))
            e.setCancelled(false);
        else
            e.setCancelled(true);
    }
}
