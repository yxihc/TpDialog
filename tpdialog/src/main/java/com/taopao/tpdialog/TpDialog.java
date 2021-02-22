package com.taopao.tpdialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;

/**
 * @Author: TaoPao
 * @Date: 2/20/21 2:16 PM
 * @Description: java类作用描述
 */
public class TpDialog {
    private TpDialog() {
    }


    public static class Builder {
        private Context mContext;
        private final DialogInfo mDialogInfo = new DialogInfo();

        public Builder(Context context) {
            this.mContext = context;
        }

        /**
         * 设置按下返回键是否关闭弹窗，默认为true
         *
         * @param isDismissOnBackPressed
         * @return
         */
        public Builder dismissOnBackPressed(Boolean isDismissOnBackPressed) {
            this.mDialogInfo.isDismissOnBackPressed = isDismissOnBackPressed;
            return this;
        }

        /**
         * 设置点击弹窗外面是否关闭弹窗，默认为true
         *
         * @param isDismissOnTouchOutside
         * @return
         */
        public Builder dismissOnTouchOutside(Boolean isDismissOnTouchOutside) {
            this.mDialogInfo.isDismissOnTouchOutside = isDismissOnTouchOutside;
            return this;
        }

        /**
         * 设置当操作完毕后是否自动关闭弹窗，默认为true。比如：点击Confirm弹窗的确认按钮默认是关闭弹窗，如果为false，则不关闭
         *
         * @param autoDismiss
         * @return
         */
        public Builder autoDismiss(Boolean autoDismiss) {
            this.mDialogInfo.autoDismiss = autoDismiss;
            return this;
        }

        /**
         * //是否显示右边选中的勾，默认没有
         *
         * @param needRightMark
         * @return
         */
        public Builder setNeedRightMark(Boolean needRightMark) {
            this.mDialogInfo.needRightMark = needRightMark;
            return this;
        }

        /**
         * 设置要被选中的 Item 的下标。
         * <p>
         * 注意:仅当 {@link # needRightMark} 为 true 时才有效。
         */
        public Builder setCheckedIndex(int checkedIndex) {
            this.mDialogInfo.checkedIndex = checkedIndex;
            return this;
        }

        public Builder addItem(TpBottomSheetListItemModel itemModel) {
            this.mDialogInfo.mItems.add(itemModel);
            return this;
        }


        /**
         * @param textAndTag Item 的文字内容，同时会把内容设置为 tag。
         */
        public Builder addItem(String textAndTag) {
            this.mDialogInfo.mItems.add(new TpBottomSheetListItemModel(textAndTag, textAndTag));
            return this;
        }

        /**
         * @param image      icon Item 的 icon。
         * @param textAndTag Item 的文字内容，同时会把内容设置为 tag。
         */
        public Builder addItem(Drawable image, String textAndTag) {
            this.mDialogInfo.mItems.add(new TpBottomSheetListItemModel(textAndTag, textAndTag).image(image));
            return this;
        }

        /**
         * @param text Item 的文字内容。
         * @param tag  item 的 tag。
         */
        public Builder addItem(String text, String tag) {
            this.mDialogInfo.mItems.add(new TpBottomSheetListItemModel(text, tag));
            return this;
        }

        /**
         * @param imageRes Item 的图标 Resource。
         * @param text     Item 的文字内容。
         * @param tag      Item 的 tag。
         */
        public Builder addItem(int imageRes, String text, String tag) {
            this.mDialogInfo.mItems.add(new TpBottomSheetListItemModel(text, tag).image(imageRes));
            return this;
        }

        /**
         * @param imageRes    Item 的图标 Resource。
         * @param text        Item 的文字内容。
         * @param tag         Item 的 tag。
         * @param hasRedPoint 是否显示红点。
         */
        public Builder addItem(int imageRes, String text, String tag, boolean hasRedPoint) {
            this.mDialogInfo.mItems.add(new TpBottomSheetListItemModel(text, tag).image(imageRes).redPoint(hasRedPoint));
            return this;
        }

        /**
         * @param imageRes    Item 的图标 Resource。
         * @param text        Item 的文字内容。
         * @param tag         Item 的 tag。
         * @param hasRedPoint 是否显示红点。
         * @param disabled    是否显示禁用态。
         */
        public Builder addItem(
                int imageRes, CharSequence text, String tag, boolean hasRedPoint, boolean disabled) {
            this.mDialogInfo.mItems.add(new TpBottomSheetListItemModel(text, tag)
                    .image(imageRes).redPoint(hasRedPoint).disabled(disabled));
            return this;
        }

        public Builder setTitle(String text) {
            this.mDialogInfo.title = text;
            return this;
        }

        @Nullable
        protected View onCreateTitleView(@NonNull Context context, DialogInfo dialogInfo) {
            if (hasTitle(dialogInfo)) {
//                TextView tv = new TextView(context);
//                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                tv.setLayoutParams(params);
//                tv.setEllipsize(TextUtils.TruncateAt.END);
//                tv.setGravity(View.TEXT_ALIGNMENT_CENTER);
//                tv.setMaxLines(1);
//                tv.setText(dialogInfo.title);
//                tv.setTextColor(Color.parseColor("#000000"));
//                tv.setBackgroundColor(Color.parseColor("#FFFFFF"));
                View view =  View.inflate(context, R.layout.tp_bottom_sheet_title, null);
                TextView tv = view.findViewById(R.id.tv_title);
                tv.setText(dialogInfo.title);
                return view;
            }
            return null;
        }

        private boolean hasTitle(DialogInfo dialogInfo) {
            return dialogInfo.title != null && dialogInfo.title.length() != 0;
        }

        public Builder addContentHeaderView(@NonNull View view) {
            if (mDialogInfo.contentHeaderViews == null) {
                mDialogInfo.contentHeaderViews = new ArrayList<>();
            }
            mDialogInfo.contentHeaderViews.add(view);
            return this;
        }

        public TpBottomSheet asBottomList() {
            TpBottomSheet tpBottomSheet = new TpBottomSheet(mContext);
            tpBottomSheet.setDialogInfo(this.mDialogInfo);

            View titleView = onCreateTitleView(mContext, mDialogInfo);

            if (titleView != null) {
                tpBottomSheet.addContentView(titleView);
            }
            tpBottomSheet.addContentView(tpBottomSheet.onCreateContentView(this.mContext, tpBottomSheet, this.mDialogInfo));
            return tpBottomSheet;
        }

    }

}