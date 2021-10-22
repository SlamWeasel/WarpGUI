package de.SlamWeasel.WarpGUI.util;

import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Util
{
    public static void save(YamlConfiguration cfg, File file)
    {
        try
        {
            cfg.save(file);
        }
        catch(IOException e1)
        {
            e1.printStackTrace();
        }
    }

    public static void setName(ItemStack itemstack, String n)
    {
        ItemMeta Meta = itemstack.getItemMeta();
        Meta.setDisplayName(n);
        itemstack.setItemMeta(Meta);
    }
    public static void setBook(ItemStack itemstack, String name, String author)
    {
        BookMeta Meta = (BookMeta) itemstack.getItemMeta();
        Meta.setDisplayName(name);
        Meta.setTitle(name);
        Meta.setAuthor(author);
        itemstack.setItemMeta(Meta);
    }
    public static ArrayList<String> lore(String name)
    {
        String Admin = "§cAdmin";
        String Architekt = "§aArchitekt";
        String Developer = "§5Developer";
        name = name.toLowerCase();
        ArrayList<String> Lore = new ArrayList<>();
        switch(name)
        {
            case "slamweasel":      Lore.add(0, Admin);
                Lore.add(1, Architekt);
                Lore.add(2, Developer);
                break;
            case "sirzenner":       Lore.add(0, Admin);
                Lore.add(1, Architekt);
                break;
            case "cornflakesminer": Lore.add(0, Architekt);
                break;
            case "leonard1504":     Lore.add(0, Architekt);
                break;
            case "ghasti":          Lore.add(0, Architekt);
                break;
            case "einhonigtee":     Lore.add(0, Architekt);
                break;
            case "zyitzu":          Lore.add(0, Architekt);
                break;
            case "zyrientic":       Lore.add(0, Architekt);
                break;
            case "iron_muffin10":   Lore.add(0, "§8=====");
                break;
            case "7sf":             Lore.add(0, Developer);
                break;
            default:                Lore.add(0, "Es ist ein Fehler augetreten");
                break;
        }return Lore;
    }
    public static ItemStack skull(String owner, String displayname)
    {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta headMeta = (SkullMeta) skull.getItemMeta();
        headMeta.setOwner(owner);
        headMeta.setDisplayName(displayname);
        headMeta.setLore(lore(owner));
        skull.setItemMeta(headMeta);

        return skull;
    }
}
