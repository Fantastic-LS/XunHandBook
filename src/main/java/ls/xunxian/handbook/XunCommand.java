package ls.xunxian.handbook;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class XunCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (command.getName().equalsIgnoreCase("xhd")) {
            if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
                sender.sendMessage("§e======§b寻仙图鉴§e======");
                sender.sendMessage("§a/xhd help §f打开帮助菜单");
                sender.sendMessage("§a/xhd list §f查看已有图鉴");
                sender.sendMessage("§a/xhd open [图鉴名称] §f打开该图鉴");
                sender.sendMessage("§a/xhd reload §f重载插件");
                return true;
            }
            switch (args[0]) {
                case "list"://列出已有图鉴
                    sender.sendMessage("§e======§b寻仙图鉴列表§e======");
                    if (XunConfigManager.getYamlFile() != null) {
                        for (File file : XunConfigManager.getYamlFile()) {
                            sender.sendMessage(file.getName().replaceAll("\\.yml",""));
                        }
                    } else {
                        sender.sendMessage("§f[§cXunHandBook§f] §c暂无图鉴，请回到配置文件中创建!");
                    }
                    return true;
                case "open":
                    if (!(sender instanceof Player)) {
                        sender.sendMessage("§f[§cXunHandBook§f] §c该指令只可以玩家使用");
                        return true;
                    }
                    if (args.length == 1 || args[1] == null) {
                        sender.sendMessage("§f[§cXunHandBook§f] §c指令缺少参数!");
                        sender.sendMessage("§f[§cXunHandBook§f] §c正确用法: /xhd open [图鉴名称]");
                    } else {
                        String handBook = args[1];
                        int arr = 0;
                        if (XunConfigManager.getYamlFile() != null) {
                            for (File file : XunConfigManager.getYamlFile()) {
                                arr++;
                                if (file.getName().replaceAll("\\.yml","").equalsIgnoreCase(handBook)) {
                                    XunBookGui.openInventory(sender.getName(),file.getName().replaceAll("\\.yml",""));
                                    break;
                                } else if (XunConfigManager.getYamlFile().length == arr) {
                                    sender.sendMessage("§f[§cXunHandBook§f] §c没有该图鉴!");
                                }
                            }
                        } else {
                            sender.sendMessage("§f[§cXunHandBook§f] §c图鉴为空，请回到配置文件中创建!");
                        }
                    }
                    return true;
                case "reload":
                    //重新加载handbook逻辑
                    //防止重复加载
                    XunHandBook.getPlugin().reloadConfig();
                    XunBookGui.clearInventory();
                    XunBookGui.loadInventory();
                    sender.sendMessage("§f[§cXunHandBook§f] §f插件已重载!");
                    return true;
                default:
                    sender.sendMessage("§e======§b寻仙图鉴§e======");
                    sender.sendMessage("§a/xhd help §f打开帮助菜单");
                    sender.sendMessage("§a/xhd list §f查看已有图鉴");
                    sender.sendMessage("§a/xhd open [图鉴名称] §f打开该图鉴");
                    sender.sendMessage("§a/xhd reload §f重载插件");
                    return true;
            }
        }
        return true;
    }
}
