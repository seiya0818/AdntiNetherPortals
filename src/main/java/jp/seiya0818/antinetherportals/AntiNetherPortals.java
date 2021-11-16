package jp.seiya0818.antinetherportals;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiNetherPortals extends JavaPlugin
{
	public static String prefix = ChatColor.WHITE + "[" + ChatColor.RED + "" + ChatColor.BOLD + "AntiNetherPortals" + ChatColor.WHITE + "]";

    @Override
    public void onEnable()
    {
    	//Prefixの設定
    	saveDefaultConfig();
    	String loadedPrefix = getColoredStr("prefix");
    	if(loadedPrefix != null)
    		prefix = loadedPrefix;

		new EventListener(this, this);

		Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.GREEN  + "プラグインが読み込まれました。");
        Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.YELLOW + "使用中のバージョン: " + this.getDescription().getVersion().toString());
    }

    public String getColoredStr(String key)
    {
    	return getConfig().getString(key).replaceAll("&", "§");
    }
}
