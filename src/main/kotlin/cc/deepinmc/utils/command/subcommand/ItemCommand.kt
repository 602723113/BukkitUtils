package cc.deepinmc.utils.command.subcommand

import cc.deepinmc.utils.ItemStackBuilder
import cc.deepinmc.utils.command.SubCommand
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.inventory.ItemFlag

class ItemCommand : SubCommand {

    override fun execute(sender: CommandSender?, args: Array<out String>?) {
        val item = ItemStackBuilder(Material.APPLE)
                .displayName("1")
                .lore("1", "&a2")
                .itemFlag(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS)
                .amount(2)
                .build()
    }
}