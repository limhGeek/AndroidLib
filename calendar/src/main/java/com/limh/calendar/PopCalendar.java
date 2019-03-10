package com.limh.calendar;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
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

    public PopCalendar(final Context context) {
        final ArrayList<DayDesc> days = Utils.INSTANCE.getMonths(2019,4);

        commPopwindow = new CommPopwindow.Builder(context)
                .setView(R.layout.layout_pop_calendar)
                .setBackGroundLevel(0.5f)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setViewOnclickListener(new CommPopwindow.ViewInterface() {
                    @Override
                    public void getChildView(View view, int layoutResId) {
                        TextView yearMonth = view.findViewById(R.id.txt_pop_yearmonth);
                        yearMonth.setText(Utils.INSTANCE.getDisplay(2018,2));
                        GridView gridView = view.findViewById(R.id.grid_view);
                        gridView.setAdapter(new CommAdapter<DayDesc>(days, R.layout.layout_item_calendar) {
                            @Override
                            public void bindView(ViewHolder holder, DayDesc obj) {
                                TextView dayView = holder.getView(R.id.txt_item_day);
                                ViewGroup.LayoutParams params = dayView.getLayoutParams();
                                params.width = Utils.INSTANCE.getScreenWidth(context) / 7;
                                params.height = Utils.INSTANCE.getScreenWidth(context) / 7;
                                dayView.setLayoutParams(params);
                                if (obj.getThisMonth()){
                                    holder.setTextColor(R.id.txt_item_day,R.color.colorTxt1);
                                }else {
                                    holder.setTextColor(R.id.txt_item_day,R.color.colorLine);
                                }
                                holder.setText(R.id.txt_item_day, ""+obj.getDay());

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
