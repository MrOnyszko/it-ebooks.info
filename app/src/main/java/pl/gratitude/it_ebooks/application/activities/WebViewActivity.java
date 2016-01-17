package pl.gratitude.it_ebooks.application.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import pl.gratitude.it_ebooks.R;
import pl.gratitude.it_ebooks.common.helpers.BundleHelper;

@SuppressWarnings("deprecation")
public class WebViewActivity extends AppCompatActivity {

    private WebView webView;
    private WebSettings webSettings;
    private Activity activity;
    private Context mContext;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        supportRequestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_web_view);

        getIntentData();

        mContext = activity = this;

        webView = (WebView) findViewById(R.id.webview);
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.clearCache(true);
        webView.setWebChromeClient(new OneWebChromeClient());
        webView.setWebViewClient(new OneWebViewClient());
        webView.loadUrl(url);

    }

    private void getIntentData() {
        Intent intentData = getIntent();
        url = intentData.getStringExtra(BundleHelper.KEY_URL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //  getMenuInflater().inflate(R.menu.menu_payu_payment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class OneWebChromeClient extends WebChromeClient {

        public OneWebChromeClient() {
            super();
        }

        public void onProgressChanged(WebView view, int progress) {
            activity.setProgress(progress * 1000);
            if (progress == 100) {
                activity.setProgressBarIndeterminateVisibility(false);
                activity.setProgressBarVisibility(false);
            }
        }
    }

    public class OneWebViewClient extends WebViewClient {

        public OneWebViewClient() {
            super();
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            switch (errorCode) {
                case ERROR_AUTHENTICATION:
                    Log.i("onReceivedError", "ErrorCode: " + errorCode + " Description: " + description + " failing url: " + failingUrl);
                    break;
                case ERROR_BAD_URL:
                    Log.i("onReceivedError", "ErrorCode: " + errorCode + " Description: " + description + " failing url: " + failingUrl);

                    break;
                case ERROR_CONNECT:
                    Log.i("onReceivedError", "ErrorCode: " + errorCode + " Description: " + description + " failing url: " + failingUrl);

                    break;
                case ERROR_FAILED_SSL_HANDSHAKE:
                    Log.i("onReceivedError", "ErrorCode: " + errorCode + " Description: " + description + " failing url: " + failingUrl);

                    break;
                case ERROR_FILE:
                    Log.i("onReceivedError", "ErrorCode: " + errorCode + " Description: " + description + " failing url: " + failingUrl);

                    break;
                case ERROR_FILE_NOT_FOUND:
                    Log.i("onReceivedError", "ErrorCode: " + errorCode + " Description: " + description + " failing url: " + failingUrl);

                    break;
                case ERROR_HOST_LOOKUP:
                    Log.i("onReceivedError", "ErrorCode: " + errorCode + " Description: " + description + " failing url: " + failingUrl);

                    view.loadData("<html><head></head><body><h1>1SMILE</h1></br><p>Sprawdź połączenie z internetem i spróbuj ponownie.<p></body></html>", "text/html", "utf-8");
                    break;
                case ERROR_IO:
                    Log.i("onReceivedError", "ErrorCode: " + errorCode + " Description: " + description + " failing url: " + failingUrl);

                    break;
                case ERROR_PROXY_AUTHENTICATION:
                    Log.i("onReceivedError", "ErrorCode: " + errorCode + " Description: " + description + " failing url: " + failingUrl);

                    break;
                case ERROR_REDIRECT_LOOP:
                    Log.i("onReceivedError", "ErrorCode: " + errorCode + " Description: " + description + " failing url: " + failingUrl);

                    break;
                case ERROR_TIMEOUT:
                    Log.i("onReceivedError", "ErrorCode: " + errorCode + " Description: " + description + " failing url: " + failingUrl);

                    break;
                case ERROR_TOO_MANY_REQUESTS:
                    Log.i("onReceivedError", "ErrorCode: " + errorCode + " Description: " + description + " failing url: " + failingUrl);

                    break;
                case ERROR_UNKNOWN:
                    Log.i("onReceivedError", "ErrorCode: " + errorCode + " Description: " + description + " failing url: " + failingUrl);

                    break;
                case ERROR_UNSUPPORTED_AUTH_SCHEME:
                    Log.i("onReceivedError", "ErrorCode: " + errorCode + " Description: " + description + " failing url: " + failingUrl);

                    break;
                case ERROR_UNSUPPORTED_SCHEME:
                    Log.i("onReceivedError", "ErrorCode: " + errorCode + " Description: " + description + " failing url: " + failingUrl);

                    break;

                default:
                    break;
            }

            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            Log.i("Path: ", "" + Uri.parse(url).getPath());

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }

    }

}
