package cc.deepinmc.utils.command.subcommand

import cc.deepinmc.utils.command.SubCommand
import cc.deepinmc.utils.setTab
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 *
 *
 * @author Zoyn
 * @since 2018-01-28
 */
class TabCommand : SubCommand {

    //util tab <玩家> <头部> <尾部> §6设置该玩家的Tab
    override fun execute(sender: CommandSender?, args: Array<out String>?) {
        if (!sender!!.hasPermission("util.tab")) {
            sender.sendMessage("§c权限不足!")
            return
        }
        if (args!!.size != 4) {
            sender.sendMessage("§c指令参数不正确!")
            return
        }
        val player: Player? = Bukkit.getPlayerExact(args[1])
        if (player == null) {
            sender.sendMessage("§c玩家不在线!")
            return
        }
        val head = if (args[2] == "null") null else args[2]
        val foot = if (args[3] == "null") null else args[3]
        setTab(player, head, foot)
    }
}