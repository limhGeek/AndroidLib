package com.limh.calendar;


import android.content.Context;
import com.limh.calendar.bean.DayDesc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * @author limh
 * @function
 * @date 2019/3/11 11:25
 */
public class Utils {
    private static final SimpleDateFormat FORMAT_YMD = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE);
    private static final SimpleDateFormat FORMAT_YMD1 = new SimpleDateFormat("yyyy年MM月", Locale.CHINESE);

    public static String getDisplay(int year, int month) {
        Calendar c = Calendar.getInstance();
        if (year != -1) {
            c.set(Calendar.YEAR, year);
        }
        if (month != -1) {
            c.set(Calendar.MONTH, month - 1);
        }
        return FORMAT_YMD1.format(c.getTime());
    }

    private static int getTotalDay(int year, int month) {
        int day = 0;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                day = 30;
                break;
            case 2:
                if (isLeap(year)) {
                    day = 29;
                } else {
                    day = 28;
                }
                break;
        }
        return day;
    }

    /**
     * 某年某月第一天是周几
     *
     * @param year  年
     * @param month 月
     * @return 周几  第一天是周日
     */
    private static int getFristDay(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    private static boolean isLeap(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    public static ArrayList<DayDesc> getMonths(int year, int month) {
        ArrayList<DayDesc> list = new ArrayList<>();

        int firstDay = getFristDay(year, month);
        int lastMonthDays = getTotalDay(year, month - 1);
        for (int i = firstDay - 1; i >= 1; i--) {
            list.add(new DayDesc(false, lastMonthDays - i + 1, month - 1, false, year));
        }
        int totalDay = getTotalDay(year, month);
        for (int i = 1; i <= totalDay; i++) {
            list.add(new DayDesc(false, i, month, true, year));
        }
        if (list.size() < 42) {
            int count = 42-list.size();
            for (int i = 0; i < count; i++) {
                list.add(new DayDesc(false, (i + 1), month + 1, false, year));
            }
        }
        return list;
    }

    public static ArrayList<DayDesc> getMonths() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        ArrayList<DayDesc> list = new ArrayList<>();
        int firstDay = getFristDay(year, month);
        int lastMonthDays = getTotalDay(year, month - 1);
        for (int i = firstDay - 1; i >= 1; i--) {
            list.add(new DayDesc(false, lastMonthDays - i + 1, month - 1, false, year));
        }
        int totalDay = getTotalDay(year, month);
        for (int i = 1; i <= totalDay; i++) {
            list.add(new DayDesc(false, i, month, true, year));
        }
        if (list.size() < 42) {
            int count = 42-list.size();
            for (int i = 0; i < count; i++) {
                list.add(new DayDesc(false, (i + 1), month + 1, false, year));
            }
        }
        return list;
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
