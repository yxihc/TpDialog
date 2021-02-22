package com.taopao.tpdialog;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.taopao.tpdialog.R;


public class QMUIBottomSheetListItemView extends ConstraintLayout {
    private final View mRootView;

    private final ImageView mIvicon;
    private final TextView mTvtext;
    private final CheckView mCheckview;

    public QMUIBottomSheetListItemView(Context context, boolean markStyle, boolean gravityCenter) {
        super(context);
        mRootView = View.inflate(context, R.layout.tp_adapter_text_match, this);
        mIvicon = mRootView.findViewById(R.id.iv_image);
        mTvtext = mRootView.findViewById(R.id.tv_text);
        mCheckview = mRootView.findViewById(R.id.check_view);
        mCheckview.setColor(Color.parseColor("#121212"));
    }

    public void render(@NonNull TpBottomSheetListItemModel itemModel, boolean isChecked) {
        //标题
        mTvtext.setText(itemModel.text);
        //左侧图标
        Drawable drawable = itemModel.image;
        if (drawable == null && itemModel.imageRes != 0) {
            drawable = ContextCompat.getDrawable(getContext(), itemModel.imageRes);
        }

        if (drawable != null) {
            drawable.mutate();
            mIvicon.setImageDrawable(drawable);
            mIvicon.setVisibility(View.VISIBLE);
        } else {
            mIvicon.setVisibility(View.GONE);
        }

        if (mCheckview != null) {
            mCheckview.setVisibility(isChecked ? View.VISIBLE : View.GONE);
        }

    }
}
