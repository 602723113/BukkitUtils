package cc.deepinmc.utils

import cc.deepinmc.utils.plugin.UtilPlugin
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import java.io.File

/**
 * Easy to manage plugins
 *
 * @author Zoyn
 * @since 2018-01-27
 */
private val pluginManager = Bukkit.getPluginManager()
private val pluginsFile = UtilPlugin.instance.dataFolder.parentFile
private val pluginLoader = UtilPlugin.instance.pluginLoader
private val updateFile = File(pluginsFile.absolutePath + File.separator + "update")

fun enablePlugin(plugin: Plugin) = pluginManager.enablePlugin(plugin)

fun disablePlugin(plugin: Plugin) = pluginManager.disablePlugin(plugin)

/**
 * 利用插件名来获取插件的Jar文件
 * <p>
 * Use the plugin name to get the Jar file of the plugin
 *
 * @param pluginName the plugin's name
 */
fun getPluginFile(pluginName: String): File? {
    return pluginsFile.listFiles().find { file -> pluginLoader.getPluginDescription(file).name == pluginName }
}

/**
 * 利用插件主类实例来获取插件的Jar文件
 * <p>
 * Use the plugin main class instance to get the plugin Jar file
 *
 * @param plugin the plugin main class instance
 */
fun getPluginFile(plugin: Plugin): File? {
    return getPluginFile(plugin.name)
}

/**
 * 获取插件文件夹
 * <p>
 * Get the plugin folder
 */
fun getPluginsFolder(): File = pluginsFile

/**
 * 获取插件目录中的所有jar文件
 * <p>
 * Get all the jar files in the plugins directory
 */
fun getJarFiles(): List<File> {
    return pluginsFile.listFiles({ pathname: File? -> pathname!!.name.endsWith("jar") }).toList()
}

/**
 * 获取更新文件夹
 * <p>
 * Get the update folder
 *
 * @return the update folder
 */
fun getUpdateFile(): File = updateFile