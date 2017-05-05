package com.lanjiang.figersland.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.lanjiang.figersland.R;

import java.util.Calendar;

/**
 * Created by Asus on 2016/12/28.
 */

public class DialogUtils {

    public static void selectDate(Context context, DatePickerDialog.OnDateSetListener listener) {
        // 获取日历对象
        Calendar calendar = Calendar.getInstance();
        // 获取当前对应的年、月、日的信息
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(context, listener, year, month, day).show();
    }

    public static void showYesNo(final Context context, String msg) {
        showYesNo(context,msg,new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.dismiss();
            }
        });
    }

    public static void showYesNo(final Context context, String msg,MaterialDialog.SingleButtonCallback callback) {
        MaterialDialog dialog = new MaterialDialog.Builder(context)
                .title(R.string.hint)
                .content(msg)
                .positiveText(R.string.sure)
                .negativeText(R.string.cancel)
                .negativeColorRes(R.color.text_gray)
                .positiveColorRes(R.color.color_main)
                .onPositive(callback)
                .show();
    }
}
