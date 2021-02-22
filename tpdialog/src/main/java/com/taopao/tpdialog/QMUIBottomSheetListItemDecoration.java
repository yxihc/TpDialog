
package com.taopao.tpdialog;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class QMUIBottomSheetListItemDecoration extends RecyclerView.ItemDecoration {

    private final Paint mSeparatorPaint;

    public QMUIBottomSheetListItemDecoration(Context context) {
        mSeparatorPaint = new Paint();
        mSeparatorPaint.setStrokeWidth(1);
        mSeparatorPaint.setStyle(Paint.Style.STROKE);
        mSeparatorPaint.setColor(Color.parseColor("#DEE0E2"));
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        RecyclerView.Adapter adapter = parent.getAdapter();
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (adapter == null || layoutManager == null) {
            return;
        }
        for (int i = 0; i < parent.getChildCount(); i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            if (view instanceof QMUIBottomSheetListItemView) {
                if (position > 0 &&
                        adapter.getItemViewType(position - 1) != QMUIBottomSheetListAdapter.ITEM_TYPE_NORMAL) {
                    int top = layoutManager.getDecoratedTop(view);
                    c.drawLine(0, top, parent.getWidth(), top, mSeparatorPaint);
                }
                if (position + 1 < adapter.getItemCount() &&
                        adapter.getItemViewType(position + 1) == QMUIBottomSheetListAdapter.ITEM_TYPE_NORMAL) {
                    int bottom = layoutManager.getDecoratedBottom(view);
                    c.drawLine(0, bottom, parent.getWidth(), bottom, mSeparatorPaint);
                }
            }
        }
    }

}
