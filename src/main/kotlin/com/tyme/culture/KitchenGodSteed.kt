package com.tyme.culture

import com.tyme.AbstractCulture
import com.tyme.lunar.LunarDay
import com.tyme.sixtycycle.SixtyCycle

/**
 * 灶马头(灶神的坐骑)
 *
 * @author 6tail
 */
class KitchenGodSteed(lunarYear: Int) : AbstractCulture() {
    /**
     * 正月初一的干支
     */
    protected var firstDaySixtyCycle: SixtyCycle = LunarDay(lunarYear, 1, 1).getSixtyCycle()

    protected fun byHeavenStem(n: Int): String {
        return NUMBERS[firstDaySixtyCycle.getHeavenStem().stepsTo(n)]
    }

    protected fun byEarthBranch(n: Int): String {
        return NUMBERS[firstDaySixtyCycle.getEarthBranch().stepsTo(n)]
    }

    /**
     * 几鼠偷粮
     *
     * @return 几鼠偷粮
     */
    fun getMouse(): String {
        return "${byEarthBranch(0)}鼠偷粮"
    }

    /**
     * 草子几分
     *
     * @return 草子几分
     */
    fun getGrass(): String {
        return "草子${byEarthBranch(0)}分"
    }

    /**
     * 几牛耕田（正月第一个丑日是初几，就是几牛耕田）
     *
     * @return 几牛耕田
     */
    fun getCattle(): String{
        return "${byEarthBranch(1)}牛耕田"
    }

    /**
     * 花收几分
     *
     * @return 花收几分
     */
    fun getFlower(): String{
        return "花收${byEarthBranch(3)}分"
    }

    /**
     * 几龙治水（正月第一个辰日是初几，就是几龙治水）
     *
     * @return 几龙治水
     */
    fun getDragon(): String{
        return "${byEarthBranch(4)}龙治水"
    }

    /**
     * 几马驮谷
     *
     * @return 几马驮谷
     */
    fun getHorse(): String{
        return "${byEarthBranch(6)}马驮谷"
    }

    /**
     * 几鸡抢米
     *
     * @return 几鸡抢米
     */
    fun getChicken(): String{
        return "${byEarthBranch(9)}鸡抢米"
    }

    /**
     * 几姑看蚕
     *
     * @return 几姑看蚕
     */
    fun getSilkworm(): String{
        return "${byEarthBranch(9)}姑看蚕"
    }

    /**
     * 几屠共猪
     *
     * @return 几屠共猪
     */
    fun getPig(): String{
        return "${byEarthBranch(11)}屠共猪"
    }

    /**
     * 甲田几分
     *
     * @return 甲田几分
     */
    fun getField(): String{
        return "甲田${byHeavenStem(0)}分"
    }

    /**
     * 几人分饼（正月第一个丙日是初几，就是几人分饼）
     *
     * @return 几人分饼
     */
    fun getCake(): String{
        return "${byHeavenStem(2)}人分饼"
    }

    /**
     * 几日得金（正月第一个辛日是初几，就是几日得金）
     *
     * @return 几日得金
     */
    fun getGold(): String{
        return "${byHeavenStem(7)}日得金"
    }

    /**
     * 几人几丙
     *
     * @return 几人几丙
     */
    fun getPeopleCakes(): String{
        return "${byEarthBranch(2)}人${byHeavenStem(2)}丙"
    }

    /**
     * 几人几锄
     *
     * @return 几人几锄
     */
    fun getPeopleHoes(): String{
        return "${byEarthBranch(2)}人${byHeavenStem(3)}锄"
    }

    override fun getName(): String {
        return "灶马头"
    }

    companion object {
        val NUMBERS: Array<String> = arrayOf("一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二")

        @JvmStatic
        fun fromLunarYear(year: Int): KitchenGodSteed {
            return KitchenGodSteed(year)
        }
    }
}