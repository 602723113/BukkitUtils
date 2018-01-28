package cc.deepinmc.utils.command.subcommand

import cc.deepinmc.utils.command.SubCommand
import cc.deepinmc.utils.sendTitle
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TitleCommand : SubCommand {

    // util title 玩家 标题 副标题 淡入 停留 淡出
    override fun execute(sender: CommandSender?, args: Array<out String>?) {
        if (!sender!!.hasPermission("util.title")) {
            sender.sendMessage("§c权限不足!")
            return
        }
        if (args!!.size == 4 || args.size == 7) {
            val player: Player? = Bukkit.getPlayerExact(args[1])
            if (player == null) {
                sender.sendMessage("§c玩家不在线!")
                return
            }

            // 当title(subTitle)为null时则设置为空字符串
            val title = if (args[2] == "null") "" else args[2]
            val subTitle = if (args[3] == "null") "" else args[3]
            var fadeIn = 2
            var stay = 20
            var fadeOut = 2
            when (args.size) {
                4 -> sendTitle(player, fadeIn, stay, fadeOut, title, subTitle)
                7 -> {
                    fadeIn = args[4].toInt()
                    stay = args[5].toInt()
                    fadeOut = args[6].toInt()
                    sendTitle(player, fadeIn, stay, fadeOut, title, subTitle)
                }
            }
        } else {
            sender.sendMessage("§c指令参数不正确!")
        }
    }
}