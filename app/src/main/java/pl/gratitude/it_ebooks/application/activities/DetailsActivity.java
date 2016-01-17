package pl.gratitude.it_ebooks.application.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.gratitude.it_ebooks.R;
import pl.gratitude.it_ebooks.application.models.Books;
import pl.gratitude.it_ebooks.application.models.DetailsModel;
import pl.gratitude.it_ebooks.application.services.SearchBookService;
import pl.gratitude.it_ebooks.common.helpers.BundleHelper;
import pl.gratitude.it_ebooks.common.helpers.StaticHelper;

public class DetailsActivity extends AppCompatActivity {

    public static final String TAG = DetailsActivity.class.getSimpleName();

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.details_grid)
    GridLayout bookDetails;
    @Bind(R.id.description)
    TextView description;
    @Bind(R.id.publisher)
    TextView publisher;
    @Bind(R.id.by)
    TextView by;
    @Bind(R.id.isbn)
    TextView isbn;
    @Bind(R.id.year)
    TextView year;
    @Bind(R.id.pages)
    TextView pages;
    @Bind(R.id.cover_image)
    ImageView cover;
    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;

    private Books books;
    private String url;
    private int position;
    private SearchBookService searchBookService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(DetailsActivity.this);
        setSupportActionBar(toolbar);

        initFields();
        getIntentData();
        getDetailsData();

    }

    private void initFields() {
        searchBookService = new SearchBookService(DetailsActivity.this);
    }

    private void getIntentData() {
        Intent data = getIntent();
        position = data.getIntExtra(StaticHelper.ITEM_POSITION, 0);
        books = (Books) data.getSerializableExtra(StaticHelper.BOOKS_MODEL);
    }

    private void getDetailsData() {
        searchBookService.getDetails(new Response.Listener<DetailsModel>() {
            @Override
            public void onResponse(DetailsModel response) {
                if (response != null) {
                    setCoverImage(response);
                    fillData(response);
                    setToolbarTitle(response.getTitle());
                    url = response.getDownload();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Msg: " + error.getMessage() + "cause: " + error.getCause());
            }
        }, books.getID());
    }

    private void fillData(DetailsModel model) {
        description.setText(model.getDescription());
        publisher.setText(model.getPublisher());
        by.setText(model.getAuthor());
        isbn.setText(model.getISBN());
        year.setText(model.getYear());
        pages.setText(model.getPage());
    }

    private void setToolbarTitle(String title) {
        toolbarLayout.setTitle(title);
    }

    private void setCoverImage(DetailsModel model) {
        Glide.with(DetailsActivity.this).load(model.getImage()).crossFade().into(cover);
    }

    private void downloadFile(String url) {
        Intent intent = new Intent();
        intent.setClass(DetailsActivity.this, WebViewActivity.class);
        intent.putExtra(BundleHelper.KEY_URL, url);
        startActivity(intent);
    }

    @OnClick(R.id.fab)
    void onFabClick() {
        downloadFile(url);
    }

}
