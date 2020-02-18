package com.med.finder.cliente.utilidades;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.med.finder.cliente.R;


public class CustomFontTextView extends androidx.appcompat.widget.AppCompatTextView {

    public static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    public CustomFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context, attrs);
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context, attrs);
    }

    private void applyCustomFont(Context context, AttributeSet attrs) {
        TypedArray attributeArray = context.obtainStyledAttributes(
                attrs,
                R.styleable.CustomFontTextView);

        String fontName = attributeArray.getString(R.styleable.CustomFontTextView_fontAttr);
        int textStyle = attrs.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", Typeface.NORMAL);

        Typeface customFont = selectTypeface(context, fontName, textStyle);
        setTypeface(customFont);

        attributeArray.recycle();
    }

    private Typeface selectTypeface(Context context, String fontName, int textStyle) {
        if (fontName.contentEquals(context.getString(R.string.font_myraid_bold))) {
            return FontCache.getTypeface("fonts/myriad_bold.ttf", context);

        }if (fontName.contentEquals(context.getString(R.string.font_myraid_italic))) {
            return FontCache.getTypeface("fonts/myriad_italic.ttf", context);

        }if (fontName.contentEquals(context.getString(R.string.font_greatvibes_regular))) {
            return FontCache.getTypeface("fonts/greatvibes_regular.ttf", context);

        }if (fontName.contentEquals(context.getString(R.string.font_led))) {
            return FontCache.getTypeface("fonts/led.ttf", context);

        }  else {
            return FontCache.getTypeface("fonts/myriad_roman.ttf", context);
        }
    }
}