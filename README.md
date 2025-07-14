# Tyme [![License](https://img.shields.io/badge/license-MIT-4EB1BA.svg?style=flat-square)](https://github.com/6tail/tyme4kt/blob/master/LICENSE)

Tyme是一个非常强大的日历工具库，可以看作 [Lunar](https://6tail.cn/calendar/api.html "https://6tail.cn/calendar/api.html") 的升级版，拥有更优的设计和扩展性，支持公历、农历、藏历、星座、干支、生肖、节气、法定假日等。

### Gradle

```
// build.gradle.kts
dependencies {
    implementation("cn.6tail:tyme4kt:1.3.5")
}

// build.gradle
dependencies {
    implementation 'cn.6tail:tyme4kt:1.3.5'
}
```

## 示例

    import com.tyme.solar.SolarDay
     
    fun main() {
        // 公历
        val solarDay = SolarDay.fromYmd(1986, 5, 29)
     
        // 1986年5月29日
        println(solarDay)
     
        // 农历丙寅年四月廿一
        println(solarDay.getLunarDay())
     
        // 第十七饶迥火虎年四月廿一
        println(solarDay.getRabByungDay())
    }
     

## 文档

请移步至 [https://6tail.cn/tyme.html](https://6tail.cn/tyme.html "https://6tail.cn/tyme.html")

## Star History

[![Star History Chart](https://api.star-history.com/svg?repos=6tail/tyme4kt&type=Date)](https://star-history.com/#6tail/tyme4kt&Date)

## 致谢
1. 感谢许剑伟老师分享的寿星天文历，本项目节气算法引自 [https://github.com/sxwnl/sxwnl](https://github.com/sxwnl/sxwnl "https://github.com/sxwnl/sxwnl")
2. 感谢[stonelf](https://github.com/stonelf "https://github.com/stonelf")，本项目藏历数据引自 [https://github.com/stonelf/zangli](https://github.com/stonelf/zangli "https://github.com/stonelf/zangli")
