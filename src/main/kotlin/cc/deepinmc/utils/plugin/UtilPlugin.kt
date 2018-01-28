package cc.deepinmc.utils.plugin

import cc.deepinmc.utils.command.CommandHandler
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

/**
 * Main Class
 *
 * @author Zoyn
 * @since 2018-01-16
 */
class UtilPlugin : JavaPlugin() {

    companion object {
        @JvmStatic
        lateinit var instance: UtilPlugin
            private set
    }

    override fun onEnable() {
        instance = this

        Bukkit.getPluginCommand("util").executor = CommandHandler()
    }
}