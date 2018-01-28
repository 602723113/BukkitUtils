package cc.deepinmc.utils.command.subcommand

import cc.deepinmc.utils.command.SubCommand
import cc.deepinmc.utils.sendBar
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class BarCommand : SubCommand {

    // /util bar <玩家> <信息> §6给该玩家发送一条Actionbar信息
    override fun execute(sender: CommandSender?, args: Array<out String>?) {
        if (!sender!!.hasPermission("util.bar")) {
            sender.sendMessage("§c权限不足!")
            return
        }

        if (args!!.size != 3) {
            sender.sendMessage("§c指令参数不正确!")
            return
        }
        val player: Player? = Bukkit.getPlayerExact(args[1])
        if (player == null) {
            sender.sendMessage("§c玩家不在线!")
            return
        }
        sendBar(player, args[2])
    }
}