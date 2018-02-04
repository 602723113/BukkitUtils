package cc.deepinmc.utils.reflect

import java.lang.reflect.Constructor
import java.lang.reflect.Field
import java.lang.reflect.Method

/**
 * @author Zoyn
 * @since 2018-02-04
 */
@FunctionalInterface
interface ConstructorFilter {
    fun accept(constructor: Constructor<*>): Boolean
}

@FunctionalInterface
interface FieldFilter {
    fun accept(field: Field): Boolean
}

@FunctionalInterface
interface MethodFilter {
    fun accept(method: Method): Boolean
}