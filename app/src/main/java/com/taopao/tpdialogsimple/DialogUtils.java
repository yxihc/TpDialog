package com.taopao.tpdialogsimple;

import android.content.Context;

import com.taopao.tpdialog.TpIOSLoadingDialog;

/**
 * @Author：淘跑
 * @Date: 2018/8/18 17:29
 * @Use：
 */
public class DialogUtils {
    private DialogUtils() {
        throw new IllegalStateException("you can't instantiate me!");
    }


//////////////////////////////////////登录框/加载框////////////////////////////////////////////////////////

    //加载框
    private static TpIOSLoadingDialog mLoadingDialog;

    /**
     * 显示加载框
     *
     * @param context
     * @param msg
     * @return
     */
    public static TpIOSLoadingDialog showLoading(Context context, String msg) {
        mLoadingDialog = new TpIOSLoadingDialog(context);
        mLoadingDialog.setCancelable(true);
        mLoadingDialog.setMessage(msg);
        mLoadingDialog.show();

        return mLoadingDialog;
    }

    /**
     * 显示加载框
     *
     * @return
     */
    public static TpIOSLoadingDialog showLoading(Context context) {
        mLoadingDialog = new TpIOSLoadingDialog(context);
        mLoadingDialog.setCancelable(true);
        mLoadingDialog.setMessage("加载是中...");
        mLoadingDialog.show();

        return mLoadingDialog;
    }

    /**
     * 关闭加载框
     */
    public static void hideLoading() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

}
