package com.limh.calendar.bean

/**
 * @author limh
 * @function 天描述
 * @date 2019/3/10 22:04
 */
data class DayDesc(
    var check: Boolean,
    var day: Int,
    var month: Int,
    var thisMonth: Boolean,
    var year: Int
)