package cc.deepinmc.utils.command

import cc.deepinmc.utils.command.subcommand.*
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

/**
 * 一个用于管理BukkitUtils的管理器
 * <p>
 * A manager to manage the BukkitUtils commnad
 *
 * @author Zoyn
 * @since 2018-01-28
 */
class CommandHandler : CommandExecutor {

    private val map = mutableMapOf<String, SubCommand>()

    init {
        map.put("help", HelpCommand())
        map.put("title", TitleCommand())
        map.put("bar", BarCommand())
        map.put("tab", TabCommand())
        map.put("item", ItemCommand())
    }

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) {
            map["help"]?.execute(sender, args)
            return true
        }

        // 子命令 map[args[0]]
        if (map[args[0]] == null) {
            sender.sendMessage("§c未知命令!")
        } else {
            map[args[0]]?.execute(sender, args)
        }
        return true
    }
}

