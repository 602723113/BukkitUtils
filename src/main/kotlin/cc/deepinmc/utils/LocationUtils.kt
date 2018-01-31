package cc.deepinmc.utils

import org.bukkit.Location
import org.bukkit.util.Vector

/**
 * 取两坐标点的距离
 * 使用空间两点距离公式计算: √(X2 - X1)^2 + (Y2 - Y1)^2 + (Z2 - Z1)^2
 * <p>
 * get two location's distance
 *
 * @param locA 坐标1
 * @param locB 坐标2
 * @return 坐标间的距离
 */
fun getDistance(locA: Location, locB: Location): Double {
    return Math.sqrt(Math.pow(locA.x - locB.x, 2.0) + Math.pow(locA.y - locB.y, 2.0) + Math.pow(locA.z - locB.z, 2.0))
}

/**
 * 取两点间的向量
 * <p>
 * get two location's vector
 *
 * @param locA 坐标1
 * @param locB 坐标2
 * @return 坐标1至坐标2的向量
 */
fun getVector(locA: Location, locB: Location): Vector {
    return locB.toVector().subtract(locA.toVector())
}