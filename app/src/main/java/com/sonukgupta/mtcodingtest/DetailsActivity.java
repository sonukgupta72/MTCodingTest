package com.sonukgupta.mtcodingtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
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

        if (getIntent()!=null && getIntent().getExtras() != null) {
            mPageId = getIntent().getExtras().getString(Constants.PAGE_ID);
            mRequestName = getIntent().getExtras().getString(Constants.REQUEST_NAME);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (mRequestName != null) {
            getSupportActionBar().setTitle(mRequestName);
        }


        if (mPageId  != null) {
            wvDetails.loadUrl(Constants.WIKI_URL + mPageId);
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
}
