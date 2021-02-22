package com.taopao.tpdialog;

import android.content.Context;
import android.util.AttributeSet;

/**
 * @Author: TaoPao
 * @Date: 2/22/21 2:39 PM
 * @Description: java类作用描述
 */
public class TpBottomSheetRootLayout extends PriorityLinearLayout {
    private final int mUsePercentMinHeight;
    private final float mHeightPercent;
    private final int mMaxWidth;

    public TpBottomSheetRootLayout(Context context) {
        this(context, null);
    }

    public TpBottomSheetRootLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);

        mUsePercentMinHeight = 650;
        mHeightPercent = 0.65f;
        mMaxWidth = 500;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        if (widthSize > mMaxWidth) {
//            widthMeasureSpec = MeasureSpec.makeMeasureSpec(mMaxWidth, widthMode);
//        }


        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (heightSize >= mUsePercentMinHeight) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(
                    (int) (heightSize * mHeightPercent), MeasureSpec.AT_MOST);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}