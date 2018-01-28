package cc.deepinmc.utils.command

import org.bukkit.command.CommandSender

/**
 * Represent a sub command
 *
 * @author Zoyn
 * @since 2018-01-28
 */
interface SubCommand {
    fun execute(sender: CommandSender, args: Array<out String>)
}
