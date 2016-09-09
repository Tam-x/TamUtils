package com.xingtam.testmain;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.xingtam.tamutils.R;

/*
 * http://blog.csdn.net/guolin_blog/article/details/17482095
 * */
public class VolleyActivity extends Activity implements OnClickListener{
	private ImageView mImage;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		RequestQueue mQueue = Volley.newRequestQueue(this); 
		String url = "";
		StringRequest stringRequest = new StringRequest("http://www.baidu.com",  
                new Response.Listener<String>() {  
                    @Override  
                    public void onResponse(String response) {  
                        Log.d("TAG", response);  
                    }  
                }, new Response.ErrorListener() {  
                    @Override  
                    public void onErrorResponse(VolleyError error) {  
                        Log.e("TAG", error.getMessage(), error);  
                    }  
                });  
		mQueue.add(stringRequest);
		StringRequest postRequest = new StringRequest(Method.POST, url,  new Response.Listener<String>() {  
            @Override  
            public void onResponse(String response) {  
                Log.d("TAG", response);  
            }  
        }, new Response.ErrorListener() {  
            @Override  
            public void onErrorResponse(VolleyError error) {  
                Log.e("TAG", error.getMessage(), error);  
            }  
        }) {  
		    @Override  
		    protected Map<String, String> getParams() throws AuthFailureError {  
		        Map<String, String> map = new HashMap<String, String>();  
		        map.put("params1", "value1");  
		        map.put("params2", "value2");  
		        return map;  
		    }  
		};  
		mQueue.add(postRequest);
		
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://m.weather.com.cn/data/101010100.html", null,  
		        new Response.Listener<JSONObject>() {  
		            @Override  
		            public void onResponse(JSONObject response) {  
		                Log.d("TAG", response.toString());  
		            }  
		        }, new Response.ErrorListener() {  
		            @Override  
		            public void onErrorResponse(VolleyError error) {  
		                Log.e("TAG", error.getMessage(), error);  
		            }  
		        });  
		mQueue.add(jsonObjectRequest);
		
		ImageRequest imageRequest = new ImageRequest(  
		        "http://developer.android.com/images/home/aw_dac.png",  
		        new Response.Listener<Bitmap>() {  
		            @Override  
		            public void onResponse(Bitmap response) {  
		            	mImage.setImageBitmap(response);  
		            }  
		        }, 0, 0, Config.RGB_565, new Response.ErrorListener() {  
		            @Override  
		            public void onErrorResponse(VolleyError error) {  
		            	mImage.setImageResource(R.drawable.ic_launcher);  
		            }  
		        });  
		mQueue.add(imageRequest);
		
		ImageLoader imageLoader = new ImageLoader(mQueue, new ImageCache() {  
		    @Override  
		    public void putBitmap(String url, Bitmap bitmap) {  
		    	
		    }  
		    
		    @Override  
		    public Bitmap getBitmap(String url) {  
		        return null;  
		    }  
		});  
		mQueue.start();
		ImageListener listener = ImageLoader.getImageListener(mImage,  
		        R.drawable.default_image, R.drawable.failed_image); 
		imageLoader.get("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg", listener); 
		imageLoader.get("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg",  
                listener, 200, 200);   
	}
	
	public class BitmapCache implements ImageCache {  		  
	    private LruCache<String, Bitmap> mCache;  	  
	    public BitmapCache() {  
	        int maxSize = 10 * 1024 * 1024;  
	        mCache = new LruCache<String, Bitmap>(maxSize) {  
	            @Override  
	            protected int sizeOf(String key, Bitmap bitmap) {  
	                return bitmap.getRowBytes() * bitmap.getHeight();  
	            }  
	        };  
	    }  
	  
	    @Override  
	    public Bitmap getBitmap(String url) {  
	        return mCache.get(url);  
	    }  
	  
	    @Override  
	    public void putBitmap(String url, Bitmap bitmap) {  
	        mCache.put(url, bitmap);  
	    }  
	  
	}  
	private void getNetworkImageView(){
		RequestQueue mQueue = Volley.newRequestQueue(this);
		NetworkImageView networkImageView = (NetworkImageView) findViewById(R.id.network_image_view); 
		networkImageView.setDefaultImageResId(R.drawable.default_image);  
		networkImageView.setErrorImageResId(R.drawable.failed_image);  
		ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache()); 
		networkImageView.setImageUrl("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg",  
		                imageLoader);  
	}
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case 1:
			getNetworkImageView();
			break;

		default:
			break;
		}
	}
}
