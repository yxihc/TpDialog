
package com.taopao.tpdialog;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

public class TpBottomSheetListItemModel {
    Drawable image = null;
    int imageRes = 0;
    int imageSkinTintColorAttr = 0;
    int imageSkinSrcAttr = 0;
    int textSkinColorAttr = 0;
    CharSequence text;
    String tag = "";
    boolean hasRedPoint = false;
    boolean isDisabled = false;
    Typeface typeface;

    public TpBottomSheetListItemModel(CharSequence text, String tag) {
        this.text = text;
        this.tag = tag;
    }

    public TpBottomSheetListItemModel image(Drawable image) {
        this.image = image;
        return this;
    }

    public TpBottomSheetListItemModel image(int imageRes) {
        this.imageRes = imageRes;
        return this;
    }

    public TpBottomSheetListItemModel skinTextColorAttr(int attr) {
        this.textSkinColorAttr = attr;
        return this;
    }

    public TpBottomSheetListItemModel skinImageTintColorAttr(int attr) {
        this.imageSkinTintColorAttr = attr;
        return this;
    }

    public TpBottomSheetListItemModel skinImageSrcAttr(int attr) {
        this.imageSkinSrcAttr = attr;
        return this;
    }

    public TpBottomSheetListItemModel redPoint(boolean hasRedPoint) {
        this.hasRedPoint = hasRedPoint;
        return this;
    }

    public TpBottomSheetListItemModel disabled(boolean isDisabled) {
        this.isDisabled = isDisabled;
        return this;
    }

    public TpBottomSheetListItemModel typeface(Typeface typeface) {
        this.typeface = typeface;
        return this;
    }
}
