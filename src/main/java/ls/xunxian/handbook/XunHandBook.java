package ls.xunxian.handbook;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class XunHandBook extends JavaPlugin {

    private static XunHandBook plugin;

    public static XunHandBook getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        XunBookGui.clearInventory();
        XunBookGui.loadInventory();
        XunConfigManager.saveHandBook();
        Bukkit.getPluginCommand("xhd").setExecutor(new XunCommand());
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerClickListener(),this);
    }
}
