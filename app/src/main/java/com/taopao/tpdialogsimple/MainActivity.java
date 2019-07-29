package com.taopao.tpdialogsimple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.taopao.tpdialog.TpIOSLoadingDialog;

public class MainActivity extends AppCompatActivity {

    private TpIOSLoadingDialog mTpIOSLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showLoading(View view) {
        mTpIOSLoadingDialog = DialogUtils.showLoading(this);
    }

    public void showLoading2(View view) {
        mTpIOSLoadingDialog.setMessage("sss");
        mTpIOSLoadingDialog.show();
    }
}
