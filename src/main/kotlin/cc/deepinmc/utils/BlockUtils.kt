package cc.deepinmc.utils

import org.bukkit.CropState
import org.bukkit.Material
import org.bukkit.NetherWartsState
import org.bukkit.block.Block
import org.bukkit.material.Crops
import org.bukkit.material.NetherWarts

/**
 * 方块工具类
 *
 * @author Zoyn
 * @since 2018-02-07
 */

fun Block.equalsBlock(block: Block?): Boolean =
        this.world.name == block!!.world.name && this.x == block.x && this.y == block.y && this.z == block.z

/**
 * 判断一个方块是否是庄稼
 * <p>
 * Judge whether a square is a crop or not
 * @param block the block
 * @return true when the block is crop
 */
fun isCrop(block: Block): Boolean {
    return when (block.type) {
        Material.CROPS, Material.POTATO, Material.CARROT, Material.NETHER_WARTS -> true
        else -> false
    }
}

/**
 * 判断一个庄稼是否已成熟
 * <p>
 * Determine if a crop is mature
 *
 * @param crops the crop
 * @return true when the crop is mature
 */
fun isMature(crops: Crops): Boolean {
    return crops.state == CropState.RIPE
}

/**
 * 判断一个方块(自动判断是否为庄稼)是否已经成熟
 * <p>
 * Judge whether a square (automatically determine whether the crop) is mature
 * @param block the block
 * @return true when the crop is mature
 */
fun isMature(block: Block): Boolean {
    return if (!isCrop(block)) {
        false
    } else {
        when (block.type) {
            Material.NETHER_WARTS -> (block.state.data as NetherWarts).state == NetherWartsState.RIPE
            else -> (block.state.data as Crops).state == CropState.RIPE
        }
    }
}