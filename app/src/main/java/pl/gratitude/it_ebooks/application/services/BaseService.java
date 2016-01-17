package pl.gratitude.it_ebooks.application.services;

import android.content.Context;

import com.android.volley.RequestQueue;

import pl.gratitude.it_ebooks.R;

/**
 * Created 09.11.2015.
 *
 * @author Sławomir
 */
public class BaseService {

    private Context mContext;
    private RequestQueue queue;
    private String apiAddress;

    public BaseService(Context context) {
        this.mContext = context;
        this.apiAddress = context.getResources().getString(R.string.api_address);
        this.queue = VolleyProvider.getInstance(context.getApplicationContext()).getRequestQueue();
    }

    public Context getContext() {
        return mContext;
    }

    public RequestQueue getQueue() {
        return queue;
    }

    public String getApiAddress() {
        return apiAddress;
    }
}
