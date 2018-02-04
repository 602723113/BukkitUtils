package cc.deepinmc.utils.reflect

import java.lang.reflect.Constructor
import java.lang.reflect.Field
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

/**
 * 反射工具
 *
 * @author Zoyn
 * @since 2018-02-04
 */

/**
 * get field objects by using field names
 *
 * @param clazz     class's object
 * @param fieldName field's name
 * @return [Field]
 * @throws NoSuchFieldException If the field with the specified name cannot be found
 * @see .hasField
 */
@Throws(NoSuchFieldException::class)
fun getFieldByFieldName(clazz: Class<*>, fieldName: String): Field? {
    var field: Field? = null
    if (hasField(clazz, fieldName)) {
        field = clazz.getField(fieldName)
    }
    return field
}

/**
 * @param classPath class's path
 * @param fieldName field's name
 * @return [Field]
 * @throws ClassNotFoundException If the class cannot be found
 * @throws NoSuchFieldException   If the field with the specified name cannot be found
 * @see .getFieldByFieldName
 */
@Throws(ClassNotFoundException::class, NoSuchFieldException::class)
fun getFieldByFieldName(classPath: String, fieldName: String): Field? {
    return getFieldByFieldName(Class.forName(classPath), fieldName)
}


/**
 * get a Value field
 *
 * @param obj       object
 * @param fieldName field's name
 * @return [Object]
 * @throws IllegalAccessException If the field is accessible
 * @see .getFieldByFieldName
 */
@Throws(IllegalAccessException::class, NoSuchFieldException::class)
fun getValueByFieldName(obj: Any, fieldName: String): Any? {
    val field = getFieldByFieldName(obj.javaClass, fieldName)
    var value: Any? = null

    if (field != null) {
        if (field.isAccessible) {
            value = field.get(obj)
        } else {
            field.isAccessible = true
            value = field.get(obj)
            field.isAccessible = false
        }
    }
    return value
}

/**
 * set a object's Value field
 *
 * @param obj       object
 * @param fieldName field's name
 * @param value     the value to be set
 * @throws NoSuchFieldException If the field is missing
 */
@Throws(NoSuchFieldException::class, IllegalAccessException::class)
fun setValueByFieldName(obj: Any, fieldName: String, value: Any) {
    val field = obj.javaClass.getDeclaredField(fieldName)
    if (field.isAccessible) {
        field.set(obj, value)
    } else {
        field.isAccessible = true
        field.set(obj, value)
        field.isAccessible = false
    }
}

/**
 * get a class's constructor
 *
 * @param clazz          class's object
 * @param parameterTypes parameters
 * @return [Constructor]
 * @throws NoSuchMethodException If the constructor with the specified parameter types cannot be found
 * @see .hasConstructor
 */
@Throws(NoSuchMethodException::class)
fun getConstructor(clazz: Class<*>, vararg parameterTypes: Class<*>): Constructor<*>? {
    var constructor: Constructor<*>? = null
    if (hasConstructor(clazz, *parameterTypes)) {
        constructor = clazz.getConstructor(*parameterTypes)
    }
    return constructor
}

/**
 * get a class's constructor
 *
 * @param classPath      class's path
 * @param parameterTypes parameters
 * @return [Constructor]
 * @throws ClassNotFoundException If the class cannot be found
 * @throws NoSuchMethodException  If the constructor with the specified parameter types cannot be found
 * @see .getConstructor
 */
@Throws(ClassNotFoundException::class, NoSuchMethodException::class)
fun getConstructor(classPath: String, vararg parameterTypes: Class<*>): Constructor<*>? {
    return getConstructor(Class.forName(classPath), *parameterTypes)
}


/**
 * Constructing an object with a constructor
 *
 * @param constructor the Constructor
 * @param arguments   the constructor's arguments
 * @return [Object]
 * @throws IllegalAccessException    If the desired constructor cannot be accessed due to certain circumstances
 * @throws InvocationTargetException If the desired constructor cannot be invoked
 * @throws InstantiationException    If you cannot create an instance of the target class due to certain circumstances
 */
@Throws(IllegalAccessException::class, InvocationTargetException::class, InstantiationException::class)
fun instantiateObject(constructor: Constructor<*>, vararg arguments: Any): Any {
    return constructor.newInstance(*arguments)
}

/**
 * get a method in a class
 *
 * @param clazz          class's object
 * @param methodName     method's name
 * @param parameterTypes the method's arguments
 * @return [Method]
 * @throws NoSuchMethodException If the method with the specified parameter types cannot be found
 */
@Throws(NoSuchMethodException::class)
fun getMethod(clazz: Class<*>, methodName: String, vararg parameterTypes: Class<*>): Method? {
    return if (hasMethod(clazz, methodName, *parameterTypes)) {
        clazz.getMethod(methodName, *parameterTypes)
    } else null
}

/**
 * get a method in a class
 *
 * @param classPath      class's path
 * @param methodName     method's name
 * @param parameterTypes the method's arguments
 * @return [Method]
 * @throws ClassNotFoundException If the class cannot be found
 * @throws NoSuchMethodException  If the method with the specified parameter types cannot be found
 * @see .getMethod
 */
@Throws(ClassNotFoundException::class, NoSuchMethodException::class)
fun getMethod(classPath: String, methodName: String, vararg parameterTypes: Class<*>): Method? {
    return getMethod(Class.forName(classPath), methodName, *parameterTypes)
}

/**
 * method of invocation of object
 *
 * @param method    method's object
 * @param object    objects that need invoke
 * @param arguments the method's arguments
 * @return [Object]
 * @throws InvocationTargetException If the desired method cannot be invoked
 * @throws IllegalAccessException    If the desired method cannot be accessed due to certain circumstances
 */
@Throws(InvocationTargetException::class, IllegalAccessException::class)
fun invokeMethod(method: Method, `object`: Any, vararg arguments: Any): Any {
    return method.invoke(`object`, *arguments)
}

/**
 * check a class has a specified field
 *
 * @param clazz     class's object
 * @param fieldName field's name
 * @return true -> yes, false -> no
 */
fun hasField(clazz: Class<*>, fieldName: String): Boolean {
    return try {
        clazz.getField(fieldName)
        true
    } catch (e: NoSuchFieldException) {
        false
    }
}

/**
 * check a class has a specified field
 *
 * @param clazz  class's object
 * @param filter filter obj
 * @return true -> yes, false -> no
 */
fun hasField(clazz: Class<*>, filter: FieldFilter): Boolean {
    return clazz.fields.any { filter.accept(it) }
}

/**
 * check a class has a specified field
 *
 * @param classPath class's path
 * @param fieldName field's name
 * @return true -> yes, false -> no
 * @see .hasField
 */
@Throws(ClassNotFoundException::class)
fun hasField(classPath: String, fieldName: String): Boolean {
    return hasField(Class.forName(classPath), fieldName)
}

/**
 * check a class has a specified constructor
 *
 * @param clazz          class's object
 * @param parameterTypes the constructor with the specified parameter types
 * @return true -> yes, false -> no
 */
fun hasConstructor(clazz: Class<*>, vararg parameterTypes: Class<*>): Boolean {
    return try {
        clazz.getConstructor(*parameterTypes)
        true
    } catch (e: NoSuchMethodException) {
        false
    }
}

/**
 * check a class has a specified constructor
 *
 * @param clazz  class's object
 * @param filter filter obj
 * @return true -> yes, false -> no
 */
fun hasConstructor(clazz: Class<*>, filter: ConstructorFilter): Boolean {
    return clazz.constructors.any { filter.accept(it) }
}

/**
 * check a class has a specified constructor
 *
 * @param classPath      class's path
 * @param parameterTypes the constructor with the specified parameter types
 * @return true -> yes, false -> no
 * @see .hasField
 */
@Throws(ClassNotFoundException::class)
fun hasConstructor(classPath: String, vararg parameterTypes: Class<*>): Boolean {
    return hasConstructor(Class.forName(classPath), *parameterTypes)
}


/**
 * check a class has a specified method
 *
 * @param clazz          class's object
 * @param methodName     method's name
 * @param parameterTypes the method with the specified parameter types
 * @return true -> yes, false -> no
 */
fun hasMethod(clazz: Class<*>, methodName: String, vararg parameterTypes: Class<*>): Boolean {
    return try {
        clazz.getMethod(methodName, *parameterTypes)
        true
    } catch (e: NoSuchMethodException) {
        false
    }
}

/**
 * check a class has a specified method
 *
 * @param clazz  class's object
 * @param filter filter obj
 * @return true -> yes, false -> no
 */
fun hasMethod(clazz: Class<*>, filter: MethodFilter): Boolean {
    return clazz.methods.any { filter.accept(it) }
}

/**
 * check a class has a specified method
 *
 * @param classPath      class's path
 * @param methodName     method's name
 * @param parameterTypes the method with the specified parameter types
 * @return true -> yes, false -> no
 * @see .hasMethod
 */
@Throws(ClassNotFoundException::class)
fun hasMethod(classPath: String, methodName: String, vararg parameterTypes: Class<*>): Boolean {
    return hasMethod(Class.forName(classPath), methodName, *parameterTypes)
}