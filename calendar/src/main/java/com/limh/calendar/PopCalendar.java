package com.limh.calendar;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import com.limh.calendar.adapter.CommAdapter;
import com.limh.calendar.bean.DayDesc;
import com.limh.calendar.popwindow.CommPopwindow;

import java.util.ArrayList;

/**
 * @author limh
 * @function
 * @date 2019/3/10 21:17
 */
public class PopCalendar {
    private CommPopwindow commPopwindow;
    private int selectPosition = -1;

    public PopCalendar(final Context context) {
        final ArrayList<DayDesc> days = Utils.INSTANCE.getMonths();

        commPopwindow = new CommPopwindow.Builder(context)
                .setView(R.layout.layout_pop_calendar)
                .setBackGroundLevel(0.5f)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setViewOnclickListener(new CommPopwindow.ViewInterface() {
                    @Override
                    public void getChildView(View view, int layoutResId) {
                        TextView yearMonth = view.findViewById(R.id.txt_pop_yearmonth);
                        yearMonth.setText(Utils.INSTANCE.getDisplay(-1,-1));
                        GridView gridView = view.findViewById(R.id.grid_view);
                        final CommAdapter<DayDesc> adapter = new CommAdapter<DayDesc>(days, R.layout.layout_item_calendar) {
                            @Override
                            public void bindView(ViewHolder holder, DayDesc obj, int position) {
                                TextView dayView = holder.getView(R.id.txt_item_day);
                                ViewGroup.LayoutParams params = dayView.getLayoutParams();
                                params.width = Utils.INSTANCE.getScreenWidth(context) / 7;
                                params.height = Utils.INSTANCE.getScreenWidth(context) / 7;
                                dayView.setLayoutParams(params);
                                if (obj.getThisMonth()) {
                                    if (obj.getCheck()) {
                                        holder.setTextColor(R.id.txt_item_day, R.color.colorBlue);
                                    } else {
                                        holder.setTextColor(R.id.txt_item_day, R.color.colorTxt1);
                                    }
                                } else {
                                    holder.setTextColor(R.id.txt_item_day, R.color.colorLine);
                                }
                                holder.setText(R.id.txt_item_day, "" + obj.getDay());

                            }
                        };
                        gridView.setAdapter(adapter);
                        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Toast.makeText(context, Utils.INSTANCE.getDisplay(days.get(position).getYear(), days.get(position).getMonth()) + "" + days.get(position).getDay(), Toast.LENGTH_SHORT).show();
                                if (days.get(position).getThisMonth()) {
                                    if (selectPosition != -1) {
                                        days.get(selectPosition).setCheck(false);
                                    }
                                    days.get(position).setCheck(true);
                                    adapter.notifyDataSetChanged();
                                    selectPosition = position;
                                }
                            }
                        });
                    }

                    @Override
                    public void onDismiss() {

                    }
                })
                .create();
    }

    public void show(View view) {
        if (null != commPopwindow && !commPopwindow.isShowing()) {
            commPopwindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        }
    }

    public void dismiss() {
        if (null != commPopwindow && commPopwindow.isShowing()) {
            commPopwindow.dismiss();
        }
    }

}
