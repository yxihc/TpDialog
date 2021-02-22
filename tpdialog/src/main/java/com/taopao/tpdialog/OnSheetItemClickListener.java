package com.taopao.tpdialog;

import android.view.View;

/**
 * @Author: TaoPao
 * @Date: 2/22/21 9:52 AM
 * @Description: java类作用描述
 */
public interface OnSheetItemClickListener {
    void onClick(TpBottomSheet dialog, View itemView, int position, String tag);
}