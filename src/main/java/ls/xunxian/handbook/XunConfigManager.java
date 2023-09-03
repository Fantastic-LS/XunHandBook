package ls.xunxian.handbook;

import java.io.File;

public class XunConfigManager {

    private static final File handBookFolder = new File(XunHandBook.getPlugin().getDataFolder(),"handbook");

    public static int getSlotIndex() {
        return XunHandBook.getPlugin().getConfig().getInt("slotIndex");
    }

    public static void saveHandBook() {
        if (handBookFolder == null) {
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
        /*List<String> name = null;
        if (yamlFiles != null) {
            for (File yamlFile : yamlFiles) {
                name.add(yamlFile.getName());
            }
        }
        return name;*/
    }
}
