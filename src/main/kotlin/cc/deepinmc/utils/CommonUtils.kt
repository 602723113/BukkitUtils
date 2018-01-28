package cc.deepinmc.utils

import org.bukkit.Bukkit
import org.bukkit.entity.Player

/**
 * Get online players
 */
fun getOnlinePlayers(): MutableList<Player> {
    val onlinePlayers = mutableListOf<Player>()
    for (world in Bukkit.getWorlds()) {
        onlinePlayers.addAll(world.players)
    }
    return onlinePlayers
}