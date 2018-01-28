package cc.deepinmc.utils

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.wrappers.WrappedChatComponent
import org.bukkit.ChatColor
import org.bukkit.entity.Player

/**
 * Easy to set player tab
 *
 * @author Zoyn
 * @since 2018-01-25
 */
fun setTab(player: Player, head: String?, foot: String?) {
    val translatedHead = if (head != null) ChatColor.translateAlternateColorCodes('&', head) else ""
    val translatedFoot = if (foot != null) ChatColor.translateAlternateColorCodes('&', foot) else ""
    // create packet
    val packet = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER)

    packet.chatComponents
            .write(0, WrappedChatComponent.fromText(translatedHead))
            .write(1, WrappedChatComponent.fromText(translatedFoot))

    ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet, false)
}