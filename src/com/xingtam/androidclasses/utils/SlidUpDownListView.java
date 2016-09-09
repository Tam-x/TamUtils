package com.xingtam.androidclasses.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class SlidUpDownListView extends ListView{
	
	public SlidUpDownListView(Context context) {
		super(context);
	}
	public SlidUpDownListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
        MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
