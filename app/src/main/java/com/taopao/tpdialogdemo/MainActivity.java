package com.taopao.tpdialogdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.taopao.tpdialog.TpBottomSheet;
import com.taopao.tpdialog.TpDialog;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btn1(View view) {
//        TpBottomSheet tpBottomSheet = new TpBottomSheet(this);
//        tpBottomSheet.addContentView(R.layout.xx);
//        tpBottomSheet.getBehavior().setAllowDrag(true);
//
//        tpBottomSheet.show();

        new TpDialog.Builder(this)
                .addItem("sasa")
                .addItem("sasa")
                .addItem("sasa")
                .addItem("sasa")
                .addItem("sasa")
                .addItem("sasa")
                .addItem("sasa")
                .addItem("sasa")
                .addItem("sasa")
                .addItem("sasa")
                .addItem("sasa")
                .addItem("sasa")
                .addItem("sasa")
                .addItem("sasa")
                .addItem("sasa")
                .addItem("sasa")
                .addItem("sasa")
                .addItem("sasa")
                .addItem("sasa")
                .addItem("sasa")
                .addItem("sasa")
                .addItem("sasa")
                .setTitle("标题")
//                .setCheckedIndex(1)
//                .setNeedRightMark(true)
                .asBottomList()
                .show();

    }
}