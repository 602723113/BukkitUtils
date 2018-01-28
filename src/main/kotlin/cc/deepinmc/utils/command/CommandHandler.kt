package cc.deepinmc.utils.command

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

/**
 * A manager to manage the BukkitUtils commnad
 *
 * @author Zoyn
 * @since 2018-01-28
 */
class CommandHandler : CommandExecutor {

    private val map = mutableMapOf<String, SubCommand>()

    init {
        map.put("help", HelpCommand())
    }

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) {
            map["help"]?.execute(sender, args)
            return true
        }

        // 子命令 map[args[0]]
        if (map[args[0]] == null) {
            sender.sendMessage("&c未知命令!")
        } else {
            map[args[0]]?.execute(sender, args)
        }
        return true
    }
}

