package com.limh.baselibs.utils

import android.net.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @function：时间格式化工具
 * @author： limh
 * @date： 2018/10/7
 * Copyright @2018 Corpration Name
 */
object TimeUtils {

    private val DateLocal = ThreadLocal<SimpleDateFormat>()

    fun  topicTime(createAt: Long): String {
        val time = System.currentTimeMillis() - createAt
        val tmp = time / 1000 / 60
        when {
            tmp < 1 -> return String.format("%s秒前", tmp * 60)
            tmp < 60 -> return String.format("%s分钟前", tmp)
            tmp < 3600 -> return String.format("%s小时前", tmp / 60)
            else -> {
                val sdr2 = SimpleDateFormat("yyyy-MM-dd", Locale.CANADA)
                val timedate = Date(createAt)
                val times2 = sdr2.format(timedate)
                return if (isToday(times2)) {
                    val sdr1 = SimpleDateFormat("HH:mm", Locale.CANADA)

                    sdr1.format(timedate)
                } else {
                    times2
                }
            }
        }
    }

    @Throws(ParseException::class)
    private fun isToday(day: String): Boolean {

        try {
            val pre = Calendar.getInstance()
            val predate = Date(System.currentTimeMillis())
            pre.time = predate
            val cal = Calendar.getInstance()
            val date = getDateFormat().parse(day)
            cal.time = date
            if (cal.get(Calendar.YEAR) == pre.get(Calendar.YEAR)) {
                val diffDay = cal.get(Calendar.DAY_OF_YEAR) - pre.get(Calendar.DAY_OF_YEAR)

                if (diffDay == 0) {
                    return true
                }
            }
        } catch (e: Exception) {
            return false
        }
        return false
    }

    private fun getDateFormat(): SimpleDateFormat {
        if (null == DateLocal.get()) {
            DateLocal.set(SimpleDateFormat("yyyy-MM-dd", Locale.CHINA))
        }
        return DateLocal.get()
    }
}