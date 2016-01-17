package pl.gratitude.it_ebooks.application.services;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created 09.11.2015.
 *
 * @author Sławomir
 */
public class VolleyProvider {

    private static Context mContext;
    private static VolleyProvider mInstance;
    private RequestQueue mRequestQueue;

    private VolleyProvider(Context context) {
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized VolleyProvider getInstance(Context context) {
        if(mInstance == null) {
            mInstance = new VolleyProvider(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if(mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }

}
