package com.taopao.tpdialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class IOSLoadingDialog extends Dialog {
    public IOSLoadingDialog(Context context) {
        super(context, R.style.TpDialogTra);
        init(context);
    }

    public IOSLoadingDialog(Context context,boolean isBlack) {
        super(context, R.style.TpDialog);
        init(context);

    }
    private void init(Context context) {
        setContentView(R.layout.diaolg_loading);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
    }

    /**
     * 宽高由该方法的参数设置
     */
    public IOSLoadingDialog(Context context, int width, int height, View layout,
                               int style) {
        super(context, style);
        // 设置内容
        setContentView(layout);
        // 设置窗口属性
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        // 设置宽度、高度、密度、对齐方式
        float density = getDensity(context);
        params.width = (int) (width * density);
        params.height = (int) (height * density);
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
    }

    public IOSLoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
        setContentView(R.layout.diaolg_loading);
        init(context);
    }

    /**
     * 获取显示密度
     *
     * @param context
     * @return
     */
    public float getDensity(Context context) {
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        return dm.density;
    }



}
