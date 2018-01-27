package cc.deepinmc.utils

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.wrappers.WrappedChatComponent
import org.bukkit.ChatColor
import org.bukkit.entity.Player

/**
 * 给一名玩家发送actionbar
 * <p>
 * send a actionbar to player
 *
 * @param player player object
 * @param msg    message
 */
fun sendBar(player: Player, msg: String) {
    val translatedMessage = ChatColor.translateAlternateColorCodes('&', msg)

    //get protocol manager instance
    val protocolManager = ProtocolLibrary.getProtocolManager()
    val packet = protocolManager.createPacket(PacketType.Play.Server.CHAT)

    //write data
    packet.chatComponents.write(0, WrappedChatComponent.fromText(translatedMessage))
    packet.bytes.write(0, 2.toByte())

    // send packet
    protocolManager.sendServerPacket(player, packet, false)
}
