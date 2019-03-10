package com.limh.calendar

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import com.limh.calendar.bean.DayDesc
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * @author limh
 * @function 日历工具类
 * @date 2019/3/10 18:09
 */
object Utils {
    val FORMAT_YMD = SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE)
    val FORMAT_YMD1 = SimpleDateFormat("yyyy年MM月", Locale.CHINESE)

    fun getDisplay(year: Int, month: Int): String {
        val c = Calendar.getInstance()
        if (year != -1) {
            c.set(Calendar.YEAR, year)
        }
        if (month != -1) {
            c.set(Calendar.MONTH, month - 1)
        }
        return FORMAT_YMD1.format(c.time)
    }

    /**
     * 判断某年末月天数
     */
    fun getTotalDay(year: Int, month: Int): Int {
        var day = 0
        when (month) {
            1, 3, 5, 7, 8, 10, 12 -> day = 31
            4, 6, 9, 11 -> day = 30
            2 -> {
                day = if (isLeap(year)) 29 else 28
            }
        }
        return day
    }

    /**
     * 判断某年某月第一天是周几
     */
    fun getFristDay(year: Int, month: Int): Int {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month - 1)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        println(FORMAT_YMD.format(calendar.time) + " 是星期：" + (calendar.get(Calendar.DAY_OF_WEEK) - 1))
        return calendar.get(Calendar.DAY_OF_WEEK) - 1
    }

    /**
     * 判断当年是否是闰年
     */
    private fun isLeap(year: Int): Boolean {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0
    }

    /**
     * 获取某年某月数据，空数据用*填充
     */
    fun getMonths(year: Int, month: Int): ArrayList<DayDesc> {
        val list = arrayListOf<DayDesc>()
        //如果每周第一天是周日则firstDay不减1
        val firstDay = getFristDay(year, month) - 1
        val lastMonthDays = getTotalDay(year, month - 1)
        for (i in firstDay downTo 1) {
            list.add(DayDesc(false, lastMonthDays - i + 1, month - 1, false, year))
        }
        val totalDay = getTotalDay(year, month)
        for (i in 1..totalDay) {
            list.add(DayDesc(false, i, month, true, year))
        }
        if (list.size < 42) {
            for (i in 1..(42 - list.size))
                list.add(DayDesc(false, i, month + 1, false, year))
        }
        return list
    }

    fun getMonths(): ArrayList<DayDesc> {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH) + 1
        val list = arrayListOf<DayDesc>()
        //如果每周第一天是周日则firstDay不减1
        val firstDay = getFristDay(year, month) - 1
        val lastMonthDays = getTotalDay(year, month - 1)
        for (i in firstDay downTo 1) {
            list.add(DayDesc(false, lastMonthDays - i + 1, month - 1, false, year))
        }
        val totalDay = getTotalDay(year, month)
        for (i in 1..totalDay) {
            list.add(DayDesc(false, i, month, true, year))
        }
        if (list.size < 42) {
            for (i in 1..(42 - list.size))
                list.add(DayDesc(false, i, month + 1, false, year))
        }
        for (i in list.indices) {
            if (i % 7 == 0) {
                println()
            }
            print(" " + list[i].day)
        }
        return list
    }

    fun getScreenWidth(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        wm.defaultDisplay.getMetrics(dm)
        return dm.widthPixels
    }

    @JvmStatic
    fun main(args: Array<String>) {
//        print(getTotalDay())
//        getMonths()
//        getMonths(2019, 4)
//        println("=====================")
//        getMonths(2018, 12)
//        println("=====================")
//        getMonths(2018, 6)
//        println("=====================")
//        getMonths(2018, 2)
    }
}