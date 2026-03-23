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
     * 全量事件数据
     */
    var DATA: String = ""

    /**
     * 数据匹配的正则表达式
     */
    const val REGEX: String = "(@[0-9A-Za-z_]{8})(%s)"

    /**
     * 删除事件
     *
     * @param name 名称
     */
    fun remove(name: String) {
        DATA = DATA.replace(REGEX.replace("%s", name).toRegex(), "")
    }

    private fun saveOrUpdate(name: String, data: String) {
        val reg = REGEX.replace("%s", name).toRegex()
        DATA = if (reg.containsMatchIn(DATA)) {
            DATA.replace(reg, data)
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
        saveOrUpdate(name, event.getData() + (event.getName().ifEmpty { name }))
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
