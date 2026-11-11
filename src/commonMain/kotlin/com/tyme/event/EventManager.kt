package com.tyme.event

/**
 * 事件管理器
 *
 * @author 6tail
 */
object EventManager {
    /**
     * 有效字符
     */
    const val CHARS: String = "0123456789ABCDEFGHIJKLMNOPQRSTU_VWXYZabcdefghijklmnopqrstuvwxyz"

    /**
     * 全量事件数据，@[1] 事件类型[1] 内容[3] 偏移天数(-31到31)[1] 起始年[3] 名称[n]
     * <h3>内容</h3>
     * <ul>
     *   <li>0.SOLAR_DAY： 月(1到12，大于12时往后推到明年1月)[1] 日(1到31)[1] 顺延天数(-31到31)[1]</li>
     *   <li>1.SOLAR_WEEK：月(1到12，大于12时往后推到明年1月)[1] 第几个(-6到-1，1到6)[1] 星期几(0到6)[1]</li>
     *   <li>2.LUNAR_DAY： 月(-12到-1，1到12，大于12时往后推到明年1月)[1] 日(1到30)[1] 顺延天数(-31到31)[1]</li>
     *   <li>3.TERM_DAY：节气索引(0-23)[1] 保留[1] 偏移天数(-31到31)[1]</li>
     *   <li>4.TERM_HS：节气索引(0-23)[1] 天干索引(0-9)[1] 偏移天数(-31到31)[1]</li>
     *   <li>5.TERM_EB：节气索引(0-23)[1] 地支索引(0-11)[1] 偏移天数(-31到31)[1]</li>
     * </ul>
     */
    var DATA: String = ""
        private set

    /**
     * 数据匹配的正则表达式
     *
     * <p>名称后必须紧跟下一个事件的数据头（@）或字符串结尾，避免「全」匹配到「全国中小学生安全教育日」。</p>
     */
    const val REGEX: String = "(@[0-9A-Za-z_]{8})(%s)(?=@|$)"

    /**
     * 删除事件
     *
     * @param name 名称
     */
    fun remove(name: String) {
        DATA = DATA.replace(REGEX.replace("%s", Regex.escape(name)).toRegex(), "")
    }

    private fun saveOrUpdate(name: String, data: String) {
        val reg = REGEX.replace("%s", Regex.escape(name)).toRegex()
        DATA = if (reg.containsMatchIn(DATA)) {
            DATA.replace(reg, Regex.escapeReplacement(data))
        } else {
            DATA + data
        }
    }

    /**
     * 新增或更新事件
     *
     * @param name  名称
     * @param event 事件
     */
    fun update(name: String, event: Event) {
        saveOrUpdate(name, event.data + (event.getName().ifEmpty { name }))
    }

    /**
     * 新增或更新事件
     *
     * @param name 名称
     * @param data 事件数据
     */
    fun updateData(name: String, data: String) {
        Event.validate(data)
        saveOrUpdate(name, data)
    }
}
