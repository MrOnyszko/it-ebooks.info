package pl.gratitude.it_ebooks.application.services;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import pl.gratitude.it_ebooks.application.models.DetailsModel;
import pl.gratitude.it_ebooks.application.models.SearchModel;

/**
 * Created 09.11.2015.
 *
 * @author Gutek
 */
public class SearchBookService extends BaseService {

    public static final String TAG = SearchBookService.class.getSimpleName();

    private HashMap<String, String> headers = new HashMap<>();
    
    public SearchBookService(Context context) {
        super(context);
        headers.put("Content-Type", "application/json; charset=utf-8");
    }

    public void searchBook(Response.Listener<SearchModel> response, Response.ErrorListener error, String query){
        String actionUrl = "/search/";
        GsonRequest<SearchModel> searchRequest =
                new GsonRequest<>(
                        Request.Method.GET, getApiAddress() + actionUrl + query,
                        SearchModel.class, headers,
                        response,
                        error
                );
        VolleyProvider.getInstance(getContext()).addToRequestQueue(searchRequest);
    }

    public void getDetails(Response.Listener<DetailsModel> response, Response.ErrorListener error, String id) {
        String actionUrl = "/book/";
        GsonRequest<DetailsModel> searchRequest =
                new GsonRequest<>(
                        Request.Method.GET, getApiAddress() + actionUrl + id,
                        DetailsModel.class, headers,
                        response,
                        error
                );
        VolleyProvider.getInstance(getContext()).addToRequestQueue(searchRequest);
    }

}
