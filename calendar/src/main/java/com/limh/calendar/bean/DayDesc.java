package com.limh.calendar.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author limh
 * @function 日历
 * @date 2019/3/11 11:22
 */
public class DayDesc implements Parcelable {
    private boolean check;
    private int day;
    private int month;
    private boolean thisMonth;
    private int year;

    public DayDesc(boolean check, int day, int month, boolean thisMonth, int year) {
        this.check = check;
        this.day = day;
        this.month = month;
        this.thisMonth = thisMonth;
        this.year = year;
    }


    protected DayDesc(Parcel in) {
        check = in.readByte() != 0;
        day = in.readInt();
        month = in.readInt();
        thisMonth = in.readByte() != 0;
        year = in.readInt();
    }

    public static final Creator<DayDesc> CREATOR = new Creator<DayDesc>() {
        @Override
        public DayDesc createFromParcel(Parcel in) {
            return new DayDesc(in);
        }

        @Override
        public DayDesc[] newArray(int size) {
            return new DayDesc[size];
        }
    };

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public boolean getThisMonth() {
        return thisMonth;
    }

    public void setThisMonth(boolean thisMonth) {
        this.thisMonth = thisMonth;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (check ? 1 : 0));
        dest.writeInt(day);
        dest.writeInt(month);
        dest.writeByte((byte) (thisMonth ? 1 : 0));
        dest.writeInt(year);
    }
}
