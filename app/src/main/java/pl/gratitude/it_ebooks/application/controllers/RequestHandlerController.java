package pl.gratitude.it_ebooks.application.controllers;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;

import com.android.volley.VolleyError;

import pl.gratitude.it_ebooks.R;
import pl.gratitude.it_ebooks.application.adapters.SearchedBooksRecyclerAdapter;
import pl.gratitude.it_ebooks.application.interfaces.ResponseListener;
import pl.gratitude.it_ebooks.application.models.SearchModel;
import pl.gratitude.it_ebooks.common.helpers.SnackBarBuilder;

/**
 * Created 11.11.2015.
 *
 * @author Sławomir
 */
public class RequestHandlerController implements ResponseListener {

    private static final String TAG = RequestHandlerController.class.getSimpleName();

    public static final int SINGLE_QUERY_TYPE = 0;
    public static final int LOAD_MORE_QUERY_TYPE = 1;

    private Context context;
    private ViewGroup rootView;
    private SnackBarBuilder snackBarBuilder;
    private SearchedBooksRecyclerAdapter adapter;

    public RequestHandlerController(SearchedBooksRecyclerAdapter adapter, Context context, ViewGroup rootView) {
        this.adapter = adapter;
        this.context = context;
        this.rootView = rootView;
        initFields();
    }

    private void initFields() {
        snackBarBuilder = new SnackBarBuilder(rootView);
    }

    @Override
    public void onSuccess(SearchModel searchModel, int type) {
        if (searchModel != null) {

            if(type == SINGLE_QUERY_TYPE) {
                adapter.setData(searchModel.getBooks());
                adapter.setTotal(searchModel.getTotal());
            } else if (type == LOAD_MORE_QUERY_TYPE){
                adapter.updateData(searchModel.getBooks());
                adapter.notifyItemInserted(adapter.getItemCount());
            }

            if (searchModel.getBooks() == null) {
                if (searchModel.getTotal() != null) {
                    if (searchModel.getTotal().equals("0")) {
                        snackBarBuilder.build(context.getResources().getString(R.string.error)).show();
                    }
                }
            }

            Log.i(TAG, "Response: \n" + searchModel.toString());
        } else {
            snackBarBuilder.build(context.getResources().getString(R.string.error)).show();
        }
        
        Log.d(TAG, "onSuccess() called with: " + "searchModel = [" + searchModel + "]");
    }

    @Override
    public void onError(VolleyError error) {
        if(error != null) {
            snackBarBuilder.build(context.getResources().getString(R.string.error)).show();
        }
        Log.d(TAG, "onError() called with: " + "error = [" + error + "]");
    }

}
