package com.xingtam.testmain;

import com.xingtam.androidclasses.utils.SlidUpDownListView;
import com.xingtam.tamutils.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class ListviewSlideActivity extends Activity{
	private SlidUpDownListView mListView;
	private ScrollView mScrollView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activty_listview_udslide);
		mListView = (SlidUpDownListView) findViewById(R.id.ud_slide_listview);
		mScrollView = (ScrollView) findViewById(R.id.ud_slide_scrollview);
		mListView.setAdapter(new ListSlideAdatper(ListviewSlideActivity.this));
		mScrollView.smoothScrollTo(0, 0);
	}
	public class ListSlideAdatper extends BaseAdapter{
		private Context context;
		public ListSlideAdatper(Context context){
			this.context = context;
		}
		@Override
		public int getCount() {
			return 50;
		}

		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(int arg0, View view, ViewGroup group) {
			ViewHolder vHolder = null;
			if (view == null) { 			
				view = LayoutInflater.from(context).inflate(R.layout.layout_listview_udslide, null);   
		        vHolder = new ViewHolder();  
		        vHolder.img= (ImageView) view.findViewById(R.id.test001);  
		        vHolder.tv= (TextView) view.findViewById(R.id.test002);         	
	        	view.setTag(vHolder);  
	    	} else {  	       
	        	vHolder = (ViewHolder) view.getTag();  
    		}  
			return view;
		}	
	}
	public class ViewHolder{
		ImageView img;
		TextView tv;
	}
}
