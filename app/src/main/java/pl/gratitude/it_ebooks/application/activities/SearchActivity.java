package pl.gratitude.it_ebooks.application.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.gratitude.it_ebooks.R;
import pl.gratitude.it_ebooks.application.adapters.SearchedBooksRecyclerAdapter;
import pl.gratitude.it_ebooks.application.controllers.RequestHandlerController;
import pl.gratitude.it_ebooks.application.interfaces.OnClickDelegate;
import pl.gratitude.it_ebooks.application.models.SearchModel;
import pl.gratitude.it_ebooks.application.services.SearchBookService;
import pl.gratitude.it_ebooks.common.helpers.BundleHelper;
import pl.gratitude.it_ebooks.common.helpers.EndlessRecyclerOnScrollListener;
import pl.gratitude.it_ebooks.common.helpers.StaticHelper;
import pl.gratitude.it_ebooks.common.ui.MarginDecoration;

public class SearchActivity extends AppCompatActivity implements OnClickDelegate {

    public static final String TAG = SearchActivity.class.getSimpleName();

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.grid)
    RecyclerView recyclerView;
    @Bind(R.id.coordinator)
    CoordinatorLayout coordinator;
    @Bind(R.id.onboarding)
    FrameLayout onboarding;

    private boolean isOnline;
    private String query;
    private Animation hideAnimation;
    private SearchView searchView;
    private SearchBookService searchBookService;
    private SearchedBooksRecyclerAdapter recyclerAdapter;
    private RequestHandlerController requestHandlerController;
    private EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        ButterKnife.bind(SearchActivity.this);

        setupToolbar();
        initFields();
        getIntentData();
        setUpRecyclerView();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleSearchNewIntent(intent);
        super.onNewIntent(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_app, menu);
        setupSearch(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.action_search:
                hideOnboarding();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_CENTER:
            case KeyEvent.KEYCODE_ENTER:
                break;
        }

        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        endlessRecyclerOnScrollListener.reset(0, true);
    }

    private void setUpRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(this, 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (recyclerAdapter.isFooter(position)) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new MarginDecoration(this));
        recyclerView.setAdapter(recyclerAdapter);
        endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(manager) {
            @Override
            public void onLoadMore(int current_page) {
                loadMoreRequest(query, current_page);
            }
        };
        recyclerView.addOnScrollListener(endlessRecyclerOnScrollListener);
    }

    private void setupSearch(Menu menu) {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    hideOnboarding();
                }
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        startDetailsActivity(position);
    }

    @OnClick(R.id.fab)
    void fab() {
        hideOnboarding();
        makeSearchViewFocusable();
    }

    private void setupToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    private void makeSearchViewFocusable() {
        toolbar.requestFocus();
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.requestFocusFromTouch();
    }

    private void hideOnboarding() {
        if (onboarding.getVisibility() == View.VISIBLE) {
            hideAnimation.start();
            onboarding.setAnimation(hideAnimation);
            onboarding.setVisibility(View.INVISIBLE);
        }
    }

    private void startDetailsActivity(int position) {
        Intent details = new Intent(SearchActivity.this, DetailsActivity.class);
        details.putExtra(StaticHelper.ITEM_POSITION, position);
        details.putExtra(StaticHelper.BOOKS_MODEL, recyclerAdapter.getBooks().get(position));
        startActivity(details);
    }

    private void handleSearchNewIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            this.query = intent.getStringExtra(SearchManager.QUERY);
            searchRequest(query);
        }
    }

    private void getIntentData() {
        Intent intentData = getIntent();
        isOnline = intentData.getBooleanExtra(BundleHelper.KEY_CONNECTIVITY_INFO, false);
    }

    private void initFields() {
        recyclerAdapter = new SearchedBooksRecyclerAdapter(SearchActivity.this, this);
        requestHandlerController = new RequestHandlerController(recyclerAdapter, SearchActivity.this, coordinator);
        searchBookService = new SearchBookService(SearchActivity.this);
        hideAnimation = AnimationUtils.loadAnimation(SearchActivity.this, R.anim.anim_hide);
    }


    private void searchRequest(String query) {
        searchBookService.searchBook(
                new Response.Listener<SearchModel>() {
                    @Override
                    public void onResponse(SearchModel response) {
                        requestHandlerController.onSuccess(response, RequestHandlerController.SINGLE_QUERY_TYPE);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        requestHandlerController.onError(error);
                    }
                },
                query
        );
    }

    private void loadMoreRequest(String searchString, int pageNumber) {
        String query = searchString;
        if (pageNumber > -1) {
            query = searchString + "/page/" + pageNumber;
        }
        searchBookService.searchBook(
                new Response.Listener<SearchModel>() {
                    @Override
                    public void onResponse(SearchModel response) {
                        requestHandlerController.onSuccess(response, RequestHandlerController.LOAD_MORE_QUERY_TYPE);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        requestHandlerController.onError(error);
                    }
                },
                query
        );
    }
}
