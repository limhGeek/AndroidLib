package com.limh.calendar;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import com.limh.calendar.adapter.CommAdapter;
import com.limh.calendar.bean.DayDesc;
import com.limh.calendar.popwindow.CommPopwindow;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * @author limh
 * @function
 * @date 2019/3/10 21:17
 */
public class PopCalendar {

    //结果回调
    private OnResultListener onResultListener;
    //选中颜色
    private int selectColor = -1;
    //点击确定自动关闭
    private boolean autoClose = true;

    private CommPopwindow commPopwindow;
    private int selectPosition = -1;
    private TextView txtDisplay;
    //选中的年月日
    private int year, month, day;
    //当前日历的年月日
    private int currentYear, currentMonth;
    private ArrayList<DayDesc> days = new ArrayList<>();
    private CommAdapter<DayDesc> adapter;

    public PopCalendar setOnResultListener(OnResultListener onResultListener) {
        this.onResultListener = onResultListener;
        return this;
    }

    public PopCalendar setSelectColor(int selectColor) {
        this.selectColor = selectColor;
        return this;
    }

    public PopCalendar setAutoClose(boolean autoClose) {
        this.autoClose = autoClose;
        return this;
    }

    public PopCalendar(final Context context) {
        Calendar c = Calendar.getInstance();
        currentYear = c.get(Calendar.YEAR);
        currentMonth = c.get(Calendar.MONTH) + 1;
        days.clear();
        days.addAll(Utils.getMonths(currentYear, currentMonth));
        commPopwindow = new CommPopwindow.Builder(context)
                .setView(R.layout.layout_pop_calendar)
                .setAnimationStyle(R.style.pop_anim)
                .setBackGroundLevel(0.5f)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setViewOnclickListener(new CommPopwindow.ViewInterface() {
                    @Override
                    public void getChildView(View view, int layoutResId) {
                        txtDisplay = view.findViewById(R.id.txt_pop_yearmonth);
                        txtDisplay.setText(Utils.getDisplay(currentYear, currentMonth));
                        GridView gridView = view.findViewById(R.id.grid_view);
                        adapter = new CommAdapter<DayDesc>(days, R.layout.layout_item_calendar) {
                            @Override
                            public void bindView(ViewHolder holder, DayDesc obj, int position) {
                                TextView txtDay = holder.getView(R.id.txt_item_day);
                                if (obj.getThisMonth()) {
                                    if (obj.isCheck()) {
                                        if (-1 != selectColor) {
                                            txtDay.setTextColor(ContextCompat.getColor(context, selectColor));
                                        } else {
                                            txtDay.setTextColor(ContextCompat.getColor(context, R.color.colorBlue));
                                        }
                                    } else {
                                        txtDay.setTextColor(ContextCompat.getColor(context, R.color.colorTxt1));
                                    }
                                } else {
                                    txtDay.setTextColor(ContextCompat.getColor(context, R.color.colorLine));
                                }
                                txtDay.setText(String.format(Locale.CHINESE, "%d", obj.getDay()));
                            }
                        };
                        gridView.setAdapter(adapter);
                        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                if (days.get(position).getThisMonth()) {
                                    if (selectPosition != -1) {
                                        days.get(selectPosition).setCheck(false);
                                    }
                                    days.get(position).setCheck(true);
                                    adapter.notifyDataSetChanged();
                                    selectPosition = position;
                                    year = days.get(position).getYear();
                                    month = days.get(position).getMonth();
                                    day = days.get(position).getDay();
                                }
                            }
                        });
                        //取消按钮
                        view.findViewById(R.id.btn_pop_cancel).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                selectPosition = -1;
                                dismiss();
                            }
                        });
                        //确定按钮
                        view.findViewById(R.id.btn_pop_yes).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (null != onResultListener && selectPosition != -1) {
                                    onResultListener.onResult(year, month, day);
                                }
                                if (autoClose) {
                                    dismiss();
                                }
                            }
                        });
                        //上个月
                        view.findViewById(R.id.img_pop_month_last).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                currentMonth -= 1;
                                if (currentMonth == 0) {
                                    currentMonth = 12;
                                    currentYear -= 1;
                                }
                                updateCalendar();
                            }
                        });
                        //上一年
                        view.findViewById(R.id.img_pop_year_last).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                currentYear -= 1;
                                updateCalendar();
                            }
                        });
                        //下个月
                        view.findViewById(R.id.img_pop_month_next).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                currentMonth += 1;
                                if (currentMonth == 13) {
                                    currentMonth = 1;
                                    currentYear += 1;
                                }
                                updateCalendar();
                            }
                        });
                        //下一年
                        view.findViewById(R.id.img_pop_year_next).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                currentYear += 1;
                                updateCalendar();
                            }
                        });

                    }
                })
                .create();
    }

    private void updateCalendar() {
        if (null == adapter) return;
        if (null == days) return;

        days.clear();
        days.addAll(Utils.getMonths(currentYear, currentMonth));
        for (DayDesc item : days) {
            if (year == item.getYear() && month == item.getMonth() && day == item.getDay()) {
                item.setCheck(true);
            }
        }
        adapter.notifyDataSetChanged();
        if (null != txtDisplay) {
            txtDisplay.setText(Utils.getDisplay(currentYear, currentMonth));
        }
    }

    public interface OnResultListener {
        void onResult(int year, int month, int day);
    }

    public void show(View view) {
        if (null != commPopwindow && !commPopwindow.isShowing()) {
            commPopwindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        }
    }

    private void dismiss() {
        if (null != commPopwindow && commPopwindow.isShowing()) {
            commPopwindow.dismiss();
        }
    }

}
