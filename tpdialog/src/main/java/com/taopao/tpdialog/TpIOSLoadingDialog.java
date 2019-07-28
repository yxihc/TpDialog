package com.taopao.tpdialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class TpIOSLoadingDialog extends Dialog {

    private View mRootView;

    private Context mContext;
    private static TpIOSLoadingView mLoadingview;

    public TpIOSLoadingDialog(Context context) {
        super(context, R.style.TpDialogTra);
        init(context);
    }

    public TpIOSLoadingDialog(Context context, boolean isBlack) {
        super(context, R.style.TpDialog);
        init(context);

    }
    private void init(Context context) {
//        setContentView(R.layout.diaolg_loading);
        mContext=context;
        mRootView = View.inflate(context, R.layout.diaolg_loading, null);
        setContentView(mRootView);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
        initView(context,mRootView);
    }

    private void initView(Context context, View rootView) {
        mLoadingview = rootView.findViewById(R.id.loadingview);

    }
    public TpIOSLoadingDialog(Context context, int themeResId) {
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
    public void setMessage(String message){
        mLoadingview.setText(message);
    }


}
