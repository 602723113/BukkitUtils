package cc.deepinmc.utils.command.subcommand

import cc.deepinmc.utils.ItemStackBuilder
import cc.deepinmc.utils.command.SubCommand
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag

class ItemCommand : SubCommand {

    override fun execute(sender: CommandSender?, args: Array<out String>?) {
        val item = ItemStackBuilder(Material.DIAMOND_SWORD)
                .durability(100)
                .displayName("1")
                .lore(mutableListOf("&a1", "&b2333"), translate = true)
                .itemFlag(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS)
                .enchant(Enchantment.DAMAGE_ALL, 100, ignoreSecurity = true)
                .unbreakable(true)
                .amount(1)
                .build()
        val player = sender as Player
        player.inventory.addItem(item)
    }
}