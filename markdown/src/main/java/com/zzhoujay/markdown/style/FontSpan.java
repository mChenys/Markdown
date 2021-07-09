package com.zzhoujay.markdown.style;

import android.annotation.SuppressLint;
import android.text.ParcelableSpan;
import android.text.TextPaint;
import android.text.style.StyleSpan;

/**
 * Created by zhou on 2016/11/10.
 * FontSpan 字体span,支持颜色/大小/样式设置
 */
@SuppressLint("ParcelCreator")
public class FontSpan extends StyleSpan implements ParcelableSpan {

    private final float size;
    private final int color;

    public FontSpan(float size, int style, int color) {
        super(style);
        this.size = size;
        this.color = color;
    }

    @Override
    public void updateMeasureState(TextPaint p) {
        super.updateMeasureState(p);
        p.setTextSize(p.getTextSize() * size);
    }

    @Override
    public void updateDrawState(TextPaint tp) {
        super.updateDrawState(tp);
        updateMeasureState(tp);
        tp.setColor(color);
    }
}
