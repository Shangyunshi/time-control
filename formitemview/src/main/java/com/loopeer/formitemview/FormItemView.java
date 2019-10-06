package com.loopeer.formitemview;

import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.Px;
import androidx.annotation.StringRes;

public interface FormItemView {

    //description text
    void setDescText(CharSequence descText);

    void setDecText(@StringRes int StringResId);

    Editable getDescText();

    void setDescTextColor(@ColorInt int color);

    void setDescTextDrawableLeft(Drawable drawable);

    void setDescTextDrawableLeft(@DrawableRes int resDrawable);

    //content text
    void setContentHint(CharSequence hint);

    void setContentHintTextColor(@ColorInt int color);

    void setContentTextColor(@ColorInt int color);

    void setContentTextSize(float size);

    void setContentText(CharSequence contentText);

    String getContentText();

    void setContentTextDrawableRight(Drawable drawable);

    void setContentTextDrawableRight(@DrawableRes int resDrawable);

    void setEnabled(boolean enabled);

    void removeContentTextChangedListener(TextWatcher watcher);

    void addContentTextChangedListener(TextWatcher watcher);

    //enable
    void setFormItemEnabled(boolean enable);

    //restrictions
    void setContentMaxLines(int max);

    void setContentMaxLength(int max);

    void setDescMinWidth(@Px int minWidth);

    void setDescTextMinEms(int minEms);

    //input type
    void setInputType(int type);

    void setDescTextSize(float size);
}
