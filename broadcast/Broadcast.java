package de.SlamWeasel.WarpGUI.broadcast;

import de.SlamWeasel.WarpGUI.main.Main;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public class Broadcast {
    static int BN = 0;

    public static void Broadcasts() {
        Server server = Bukkit.getServer();
        server.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable(){
                public void run()
            {
                Player player;
                Iterator var2;
                switch(Broadcast.BN)
                {
                    case 0:
                        var2 = Bukkit.getOnlinePlayers().iterator();

                        while(var2.hasNext())
                        {
                            player = (Player)var2.next();
                            player.sendMessage(" ");
                            player.sendMessage(" ");
                            player.sendMessage("   §6§lDein Plot ist fertig?");
                            player.sendMessage("   §6§lGib §d/submit §6§lein und dein Plot kann");
                            player.sendMessage("   §6§lvon uns bewertet werden");
                            player.sendMessage(" ");
                            player.sendMessage(" ");
                        }

                        ++Broadcast.BN;
                        break;
                    case 1:
                        var2 = Bukkit.getOnlinePlayers().iterator();

                        while(var2.hasNext())
                        {
                            player = (Player)var2.next();
                            player.sendMessage(" ");
                            player.sendMessage(" ");
                            player.sendMessage("   §6§lDu weißt nicht was du bauen sollst?");
                            player.sendMessage("   §6§lIn unseren §dShowwelten §6§lkannst du dich");
                            player.sendMessage("   §6§linspirieren lassen");
                            player.sendMessage(" ");
                            player.sendMessage(" ");
                        }

                        ++Broadcast.BN;
                        break;
                    case 2:
                        var2 = Bukkit.getOnlinePlayers().iterator();

                        while(var2.hasNext())
                        {
                            player = (Player)var2.next();
                            player.sendMessage(" ");
                            player.sendMessage(" ");
                            player.sendMessage("   §6§lWir haben auch TeamSpeak!");
                            player.sendMessage("   §6§lDu kannst uns einfach über §dDryadon.net");
                            player.sendMessage("   §6§ljoinen");
                            player.sendMessage(" ");
                            player.sendMessage(" ");
                        }

                        ++Broadcast.BN;
                        break;
                    case 3:
                        var2 = Bukkit.getOnlinePlayers().iterator();

                        while(var2.hasNext())
                        {
                            player = (Player)var2.next();
                            player.sendMessage(" ");
                            player.sendMessage(" ");
                            player.sendMessage("   §6§lBenimm dich!");
                            player.sendMessage("   §6§lSpieler, die auf ihren Plots spammen oder obszöne");
                            player.sendMessage("   §6§lDinge bauen, im Chat spammen oder beleidigen und");
                            player.sendMessage("   §6§lWerbung machen werden bestraft!");
                            player.sendMessage(" ");
                            player.sendMessage(" ");
                        }

                        ++Broadcast.BN;
                        break;
                    case 4:
                        var2 = Bukkit.getOnlinePlayers().iterator();

                        while(var2.hasNext())
                        {
                            player = (Player)var2.next();
                            player.sendMessage(" ");
                            player.sendMessage(" ");
                            player.sendMessage("   §6§lBitte keine Pixelarts einreichen,");
                            player.sendMessage("   §6§lda diese bei der Bewertung nicht");
                            player.sendMessage("   §6§lberücksichtigt werden!");
                            player.sendMessage(" ");
                            player.sendMessage(" ");
                        }

                        ++Broadcast.BN;
                        break;
                    case 5:
                        var2 = Bukkit.getOnlinePlayers().iterator();

                        while(var2.hasNext())
                        {
                            player = (Player)var2.next();
                            player.sendMessage(" ");
                            player.sendMessage(" ");
                            player.sendMessage("   §6§lBitte keine leeren oder unfertigen");
                            player.sendMessage("   §6§lPlots einreichen, da diese bei der");
                            player.sendMessage("   §6§lBewertung nicht berücksichtigt und");
                            player.sendMessage("   §6§lgegebenenfalls gelöscht werden");
                            player.sendMessage(" ");
                            player.sendMessage(" ");
                        }

                        ++Broadcast.BN;
                        break;
                    case 6:
                        var2 = Bukkit.getOnlinePlayers().iterator();

                        while(var2.hasNext()) {
                            player = (Player)var2.next();
                            player.sendMessage(" ");
                            player.sendMessage(" ");
                            player.sendMessage("   §6§lHast du Fragen oder möchtest etwas");
                            player.sendMessage("   §6§lüber das Team oder den Server wissen?");
                            player.sendMessage("   §6§lSchau mal in den §dFAQ §6§lim Kompass nach!");
                            player.sendMessage(" ");
                            player.sendMessage(" ");
                        }

                        Broadcast.BN++;
                        break;
                    case 7:
                        var2 = Bukkit.getOnlinePlayers().iterator();

                        while(var2.hasNext()) {
                            player = (Player)var2.next();
                            player.sendMessage(" ");
                            player.sendMessage(" ");
                            player.sendMessage("   §4§l!!!!!");
                            player.sendMessage("   §6§lDer Bewertungsstream findet nun immer");
                            player.sendMessage("   §dDienstags §6§lum 19:00Uhr statt");
                            player.sendMessage("   §4§l!!!!!");
                            player.sendMessage(" ");
                            player.sendMessage(" ");
                        }

                        Broadcast.BN = 0;
                        break;
                }

            }
        }, 0L, 1400L);
    }
}
