package cc.deepinmc.utils.command.subcommand

import cc.deepinmc.utils.command.SubCommand
import cc.deepinmc.utils.getOnlinePlayers
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
            // 当title(subTitle)为null时则设置为空字符串
            val title = if (args[2] == "null") "" else args[2]
            val subTitle = if (args[3] == "null") "" else args[3]

            val fadeIn = if (args.size == 7) args[4].toInt() else 2
            val stay = if (args.size == 7) args[5].toInt() else 20
            val fadeOut = if (args.size == 7) args[6].toInt() else 2

            // 当输入玩家那栏为null时则识别为给全体玩家发送
            if (args[1] == "null") {
                getOnlinePlayers().forEach({ player -> sendTitle(player, fadeIn, stay, fadeOut, title, subTitle) })
                return
            } else {
                val player: Player? = Bukkit.getPlayerExact(args[1])
                if (player == null) {
                    sender.sendMessage("§c该玩家不在线!")
                    return
                }
                sendTitle(player, fadeIn, stay, fadeOut, title, subTitle)
            }
        } else {
            sender.sendMessage("§c指令参数不正确!")
        }
    }
}