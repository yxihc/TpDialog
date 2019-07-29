package com.taopao.tpdialog;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class TpIOSLoadingView extends LinearLayout {
    private ProgressBar progressBar;
    private TextView tv;
    private ImageView iv;
    private Context context;

    public TpIOSLoadingView(Context context) {
        this(context, null);
    }

    public TpIOSLoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TpIOSLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.tp_loading_view, this, true);
        progressBar = view.findViewById(R.id.progressBar);
        tv = findViewById(R.id.tv);
        iv = findViewById(R.id.iv);
    }

    /**
     * loading
     */
    public void showLoading() {
        iv.setVisibility(GONE);
        progressBar.setVisibility(VISIBLE);
    }

    /**
     * 成功
     */
    public void showSuccess() {
        iv.setImageResource(R.mipmap.load_success);
        iv.setVisibility(View.VISIBLE);
        progressBar.setVisibility(GONE);
        tv.setText("加载成功");
    }

    /**
     * 成功
     */
    public void showSuccess(String message) {
        iv.setImageResource(R.mipmap.load_success);
        iv.setVisibility(View.VISIBLE);
        progressBar.setVisibility(GONE);
        tv.setText(message);
    }

    /**
     * 失败
     */
    public void showFail() {
        iv.setImageResource(R.mipmap.load_fail);
        iv.setVisibility(View.VISIBLE);
        progressBar.setVisibility(GONE);
        tv.setText("加载失败");
    }

    /**
     * 失败
     */
    public void showFail(String message) {
        iv.setImageResource(R.mipmap.load_fail);
        iv.setVisibility(View.VISIBLE);
        progressBar.setVisibility(GONE);
        tv.setText(message);
    }

    /**
     * 提示文字
     *
     * @param txt string
     */
    public void setText(String txt) {
        tv.setText(txt);
    }

    /**
     * 提示文字
     */
    public void setText(@StringRes int txtId) {
        tv.setText(txtId);
    }
}

