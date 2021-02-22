package com.taopao.tpdialog;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatDialog;
import androidx.core.view.ViewCompat;

/**
 * @Author: TaoPao
 * @Date: 2/19/21 11:05 AM
 * @Description:
 */
public class BaseTpDialog extends AppCompatDialog {

    boolean cancelable = true;
    private boolean canceledOnTouchOutside = true;
    private boolean canceledOnTouchOutsideSet;

    public BaseTpDialog(Context context) {
        super(context);
    }

    public BaseTpDialog(Context context, int theme) {
        super(context, theme);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    protected BaseTpDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        if (window != null) {

            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getWindow().getDecorView().setPadding(0, 0, 0, 0);
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                    WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);


            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


            //设置全屏
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            getWindow().getDecorView().setSystemUiVisibility(option);


            //remove status bar shadow
            if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
                setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, true);
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, false);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); //尝试兼容部分手机上的状态栏空白问题
            }
            if (Build.VERSION.SDK_INT == 19) { //解决4.4上状态栏闪烁的问题
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }
    }

    public void setWindowFlag(final int bits, boolean on) {
        WindowManager.LayoutParams winParams = getWindow().getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        getWindow().setAttributes(winParams);
    }

    @Override
    public void setCancelable(boolean cancelable) {
        super.setCancelable(cancelable);
        if (this.cancelable != cancelable) {
            this.cancelable = cancelable;
            onSetCancelable(cancelable);
        }
    }

    protected void onSetCancelable(boolean cancelable) {

    }

    @Override
    public void setCanceledOnTouchOutside(boolean cancel) {
        super.setCanceledOnTouchOutside(cancel);
        if (cancel && !cancelable) {
            cancelable = true;
        }
        canceledOnTouchOutside = cancel;
        canceledOnTouchOutsideSet = true;
    }

    protected boolean shouldWindowCloseOnTouchOutside() {
        if (!canceledOnTouchOutsideSet) {
            TypedArray a =
                    getContext()
                            .obtainStyledAttributes(new int[]{android.R.attr.windowCloseOnTouchOutside});
            canceledOnTouchOutside = a.getBoolean(0, true);
            a.recycle();
            canceledOnTouchOutsideSet = true;
        }
        return canceledOnTouchOutside;
    }

    @Override
    public void dismiss() {
        Context context = getContext();
        if (context instanceof ContextWrapper) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (activity.isDestroyed() || activity.isFinishing()) {
                return;
            }
            super.dismiss();
        } else {
            try {
                super.dismiss();
            } catch (Throwable ignore) {
            }
        }
    }


    ///////////////////////tools

    public void hideNavigationBar() {
        final ViewGroup decorView = (ViewGroup) getWindow().getDecorView();
        final int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_IMMERSIVE |
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                View.SYSTEM_UI_FLAG_VISIBLE;
        decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | uiOptions);
    }
}
