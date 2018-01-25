package cc.deepinmc.utils

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.wrappers.WrappedChatComponent
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import java.lang.reflect.InvocationTargetException

/**
 * Easy to send an actionbar
 *
 * @author Zoyn
 * @since 2016/?/?
 */
object ActionBarUtils {

    /**
     * 给一名玩家发送actionbar
     * <p>
     * send schudule actionbar to player
     *
     * @param player player object
     * @param msg    message
     */
    @JvmStatic
    fun sendBar(player: Player, msg: String) {
        val translatedMessage = ChatColor.translateAlternateColorCodes('&', msg)

        //get protocol manager instance
        val protocolManager = ProtocolLibrary.getProtocolManager()
        val packet = protocolManager.createPacket(PacketType.Play.Server.CHAT)

        //write data
        packet.chatComponents.write(0, WrappedChatComponent.fromText(translatedMessage))
        packet.bytes.write(0, 2.toByte())

        // send packet
        try {
            protocolManager.sendServerPacket(player, packet, false)
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }
    }

}
