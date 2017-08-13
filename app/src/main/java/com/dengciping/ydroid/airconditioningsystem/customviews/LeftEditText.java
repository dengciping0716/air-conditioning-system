package com.dengciping.ydroid.airconditioningsystem.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

/**
 * 左边带文字的EditText
 *
 * @author DengCiPing
 * @date 2017/8/12 下午8:34
 */
public class LeftEditText extends AppCompatEditText {
    private String fixedText;
    private View.OnClickListener mListener;
    private int leftPadding;

    public LeftEditText(Context context) {
        super(context);
    }

    public LeftEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LeftEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setFixedText(String text) {
        fixedText = text;
        leftPadding = getPaddingLeft();
        int left = (int) getPaint().measureText(fixedText) + leftPadding;
        setPadding(left, getPaddingTop(), getPaddingBottom(), getPaddingRight());
        invalidate();
    }

    public void setDrawableClick(View.OnClickListener listener) {
        mListener = listener;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!TextUtils.isEmpty(fixedText)) {
            canvas.drawText(fixedText, leftPadding, getBaseline(), getPaint());
//            通过下面的代码，可以查看出文字的基线，以及view的中线
//            Paint p = new Paint();
//            p.setStrokeWidth(1);
//            p.setColor(Color.parseColor("#ff0000"));
//            canvas.drawLine(0, getBaseline(), getMeasuredWidth(), getBaseline(), p);
//            canvas.drawLine(0, getMeasuredHeight() / 2, getMeasuredWidth(), getMeasuredHeight() / 2, p);
        }
    }

}
