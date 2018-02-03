# BukkitUtils [![](https://jitpack.io/v/DeepinMC/BukkitUtils.svg)](https://jitpack.io/#DeepinMC/BukkitUtils)
BukkitUtils是一个对于Bukkit服务器所开发的插件, 所用语言为Kotlin

## 协议
该开源项目遵循MIT协议

## Repository
##### Maven
如果你是Maven那么请添加以下的内容进你的pom.xml
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.DeepinMC</groupId>
    <artifactId>BukkitUtils</artifactId>
    <version>v1.0.0</version>
</dependency>
```

##### Gradle
如果你是Gradle那么请添加以下的内容进你的build.gradle

```
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
	
dependencies {
    compile 'com.github.DeepinMC:BukkitUtils:v1.0.0'
}
```