package ls.xunxian.handbook;

import io.lumine.xikage.mythicmobs.MythicMobs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class XunBookGui {

    private static XunBookGui instance;

    private static final HashMap<String, Inventory> inv = new HashMap<>();

    public static void setInstance(XunBookGui instance) {
        XunBookGui.instance = instance;
    }

    public HashMap<String, Inventory> getInv() {
        return inv;
    }

    public static XunBookGui getInstance() {
        return instance;
    }

    public static ItemStack getMMItem(String id) {
        ItemStack itemStack = MythicMobs.inst().getItemManager().getItemStack(id);
        if (itemStack != null) {
            return itemStack;
        } else {
            Bukkit.getServer().getConsoleSender().sendMessage("物品ID: " + id + "不存在！请检查调整后重载！");
        }
        return null;
    }

    public static void loadInventory() {
        File[] files = XunConfigManager.getYamlFile();
        if (files != null) {
            for (File file : files) {
                Inventory inventory = Bukkit.createInventory(null, XunConfigManager.getSlotIndex(), file.getName().replaceAll("\\.yml", ""));
                try {
                    FileInputStream inputStream = new FileInputStream(file);
                    Yaml yaml = new Yaml();
                    Object data = yaml.load(inputStream);
                    if (data instanceof Map) {
                        Map<String, Object> dataMap = (Map<String, Object>) data;
                        Set<String> keys = dataMap.keySet();
                        if (keys != null && !keys.isEmpty()) {
                            for (String key : keys) {
                                if (key != null) {
                                    @SuppressWarnings("unchecked")
                                    Map<String, Object> nestedData = (Map<String, Object>) ((Map<String, Object>) data).get(key);
                                    ItemStack itemStack = getMMItem(key);
                                    int slotIndex = (Integer) nestedData.get("slot");
                                    inventory.setItem(slotIndex, itemStack);
                                    inv.put(file.getName().replaceAll("\\.yml", ""), inventory);
                                } else {
                                    Bukkit.getServer().getConsoleSender().sendMessage("配置错误");
                                }
                            }
                        }
                    } else {
                        Bukkit.getServer().getConsoleSender().sendMessage("图鉴配置为空！请创建配置");
                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public static void clearInventory() {
        inv.clear();
    }

    public static void openInventory(String playerName, String handName) {
        Player player = Bukkit.getPlayer(playerName);
        if (!inv.isEmpty() && inv != null) {
            Inventory inventory = inv.get(handName);
            player.openInventory(inventory);
        } else {
            player.sendMessage("§f[§cXunHandBook§f] §f该图鉴没有正确设置物品或物品位置!");
        }
    }
}
