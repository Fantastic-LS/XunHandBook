package ls.xunxian.handbook;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class XunConfigManager {

    private static final File handBookFolder = new File(XunHandBook.getPlugin().getDataFolder(),"handbook");

    public static int getSlotIndex() {
        return XunHandBook.getPlugin().getConfig().getInt("slotIndex");
    }

    public static void saveHandBook() {
        if (!handBookFolder.exists()) {
            XunHandBook.getPlugin().saveResource("handbook\\示例.yml",false);
        }
    }

    //获取所有handbook文件夹中的yml文件
    public static File[] getYamlFile() {
        File[] yamlFiles = null;
        if (handBookFolder.exists() && handBookFolder.isDirectory()) {
            yamlFiles = handBookFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".yml"));
        }
        return yamlFiles;
    }
}
