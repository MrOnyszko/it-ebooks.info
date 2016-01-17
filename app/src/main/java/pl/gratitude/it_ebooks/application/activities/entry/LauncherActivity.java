package pl.gratitude.it_ebooks.application.activities.entry;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.gratitude.it_ebooks.R;
import pl.gratitude.it_ebooks.application.activities.SearchActivity;
import pl.gratitude.it_ebooks.common.activities.BaseActivity;
import pl.gratitude.it_ebooks.common.helpers.BundleHelper;
import pl.gratitude.it_ebooks.common.helpers.ConnectivityHelper;

public class LauncherActivity extends BaseActivity {

    public static final String TAG = LauncherActivity.class.getSimpleName();

    @Bind(R.id.root)
    ViewGroup root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        ButterKnife.bind(this);
        resolveOnlineOrOfflineMode();
        Log.i(TAG, "onCreate");
    }

    private void resolveOnlineOrOfflineMode() {
        Intent appIntent = new Intent(LauncherActivity.this, SearchActivity.class);
        appIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        if (ConnectivityHelper.isOnline(LauncherActivity.this)) {
            appIntent.putExtra(BundleHelper.KEY_CONNECTIVITY_INFO, true);
        } else {
            appIntent.putExtra(BundleHelper.KEY_CONNECTIVITY_INFO, false);
        }
        LauncherActivity.this.startActivity(appIntent);
        finish();
    }

}
