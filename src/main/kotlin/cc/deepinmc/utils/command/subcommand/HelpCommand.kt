package cc.deepinmc.utils.command.subcommand

import cc.deepinmc.utils.command.SubCommand
import org.bukkit.command.CommandSender

class HelpCommand : SubCommand {

    override fun execute(sender: CommandSender?, args: Array<out String>?) {
        sender!!.sendMessage("§6§m一一一一一一 §6§l[§eBukkitUtils§6§l] §6§m一一一一一一")
        sender.sendMessage("§l• §e/util title <玩家> <标题> <副标题> [淡入时间] [停留时间] [淡出时间]")
        sender.sendMessage("  §7§l┃ §6给该玩家发送一条Title信息")
        sender.sendMessage("§l• §e/util bar <玩家> <信息> ")
        sender.sendMessage("  §7§l┃ §6给该玩家发送一条Actionbar信息")
        sender.sendMessage("§l• §e/util tab <玩家> <头部> <尾部> ")
        sender.sendMessage("  §7§l┃ §6设置该玩家的Tab")
    }

}