package pl.gratitude.it_ebooks.application.interfaces;

import com.android.volley.VolleyError;

import pl.gratitude.it_ebooks.application.models.SearchModel;

/**
 * Created 11.11.2015.
 *
 * @author Sławomir
 */
public interface ResponseListener {

    void onSuccess(SearchModel searchModel, int type);
    void onError(VolleyError error);

}
