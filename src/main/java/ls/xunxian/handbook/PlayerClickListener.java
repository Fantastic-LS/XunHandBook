package ls.xunxian.handbook;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.io.File;

public class PlayerClickListener implements Listener {


    @EventHandler
    public void onPlayerClick(InventoryClickEvent event) {
        File[] files = XunConfigManager.getYamlFile();
        Inventory inventory = event.getClickedInventory();
        Player player = (Player) event.getWhoClicked();
        if (inventory == player.getInventory())
            return;
        if (inventory.getTitle() != null) {
            String invName = inventory.getTitle();
            for (File file : files) {
                if (file.getName().replaceAll("\\.yml","").equalsIgnoreCase(invName)) {
                    event.setCancelled(true);
                }
            }
        }
    }

}
