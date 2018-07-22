package com.sonukgupta.mtcodingtest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {

    WebView wvDetails;
    String mPageId = null;
    String mRequestName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        wvDetails = (WebView) findViewById(R.id.wvDetails);

        if (getIntent() != null && getIntent().getExtras() != null) {
            mPageId = getIntent().getExtras().getString(Constants.PAGE_ID);
            mRequestName = getIntent().getExtras().getString(Constants.REQUEST_NAME);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (mRequestName != null) {
            getSupportActionBar().setTitle(mRequestName);
        }


        if (mPageId != null) {
            wvDetails.getSettings().setJavaScriptEnabled(false);

            wvDetails.setWebViewClient(new WebViewClient() {
                @SuppressWarnings("deprecation")
                @Override
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    Toast.makeText(DetailsActivity.this, description, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                    // Redirect to deprecated method, so you can use it in all SDK versions
                    //onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
                }
            });

            if (isNetworkConnected()) {
                wvDetails.loadUrl(Constants.WIKI_URL + mRequestName.trim().replace(" ", "_"));
            } else {
                Toast.makeText(this, getString(R.string.error_network_connection), Toast.LENGTH_LONG).show();
            }
        }
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


    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}
