package cc.deepinmc.utils

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*

/**
 * 获取在线的玩家
 * <p>
 * Get online players
 */
fun getOnlinePlayers(): MutableList<Player> {
    val onlinePlayers = mutableListOf<Player>()
    for (world in Bukkit.getWorlds()) {
        onlinePlayers.addAll(world.players)
    }
    return onlinePlayers
}

/**
 * 获取玩家的UUID (仅限离线服务器!)
 * <p>
 * Get player uuid (only offline server!)
 */
fun getPlayerUUID(playerName: String): String {
    return Bukkit.getPlayerExact(playerName)?.uniqueId?.toString() ?: UUID.nameUUIDFromBytes("OfflinePlayer:$playerName".toByteArray()).toString()
}