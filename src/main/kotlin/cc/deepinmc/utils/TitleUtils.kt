package cc.deepinmc.utils

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.events.PacketContainer
import com.comphenix.protocol.wrappers.EnumWrappers
import com.comphenix.protocol.wrappers.WrappedChatComponent
import org.bukkit.ChatColor
import org.bukkit.entity.Player

/**
 * Easy to send title
 * <p>
 * depend on ProtocolLib
 *
 * @author Zoyn
 * @since 2018-01-16
 */
fun sendTitle(player: Player, fadeIn: Int, stay: Int, fadeOut: Int, title: String?, subTitle: String?) {
    val protocolManager = ProtocolLibrary.getProtocolManager()
    var packet: PacketContainer

    if (title != null) {
        var translatedTitle = ChatColor.translateAlternateColorCodes('&', title)
        translatedTitle = translatedTitle.replace("%player%".toRegex(), player.name)
        // create packet
        packet = protocolManager.createPacket(PacketType.Play.Server.TITLE)
        // write data
        packet.titleActions.write(0, EnumWrappers.TitleAction.TITLE) // EnumTitleAction
        packet.chatComponents.write(0, WrappedChatComponent.fromText(translatedTitle)) // title message
        packet.integers
                .write(0, fadeIn)
                .write(1, stay)
                .write(2, fadeOut)

        // send packet
        protocolManager.sendServerPacket(player, packet, false)
    }
    if (subTitle != null) {
        var translatedSubTitle = ChatColor.translateAlternateColorCodes('&', subTitle)
        translatedSubTitle = translatedSubTitle.replace("%player%".toRegex(), player.name)

        packet = protocolManager.createPacket(PacketType.Play.Server.TITLE)
        packet.titleActions.write(0, EnumWrappers.TitleAction.SUBTITLE)
        packet.chatComponents.write(0, WrappedChatComponent.fromText(translatedSubTitle))
        packet.integers.write(0, fadeIn)
        packet.integers.write(1, stay)
        packet.integers.write(2, fadeOut)

        protocolManager.sendServerPacket(player, packet, false)
    }
}
