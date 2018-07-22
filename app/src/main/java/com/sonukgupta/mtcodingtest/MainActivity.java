package com.sonukgupta.mtcodingtest;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.sonukgupta.mtcodingtest.cuatomWidget.MyProgressbar;
import com.sonukgupta.mtcodingtest.model.BaseModel;
import com.sonukgupta.mtcodingtest.model.PageQSRModel;
import com.sonukgupta.mtcodingtest.model.SearchResultModel;
import com.sonukgupta.mtcodingtest.network.ApiCallBack;
import com.sonukgupta.mtcodingtest.network.ApiProvider;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SearchView svSearch;
    SearchResultAdapter mSearchResultAdapter;
    List<PageQSRModel> mPageQSRModelList = new ArrayList<>();
    AlertDialog myProgressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rvSearchResult);
        svSearch  = (SearchView) findViewById(R.id.svSearch);

        myProgressbar = MyProgressbar.getSimpleProgressDialogue(this, false);

        svSearch.setQuery("",true);
        svSearch.setFocusable(true);
        svSearch.setIconified(false);
        svSearch.requestFocusFromTouch();

//        svSearch.requestFocus();
//        //search view click anywhere
//        svSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                svSearch.setIconified(false);
//            }
//        });

        //on query change listener
        svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextChange(String newText) {

                if (newText.length() == 0) {
                    mPageQSRModelList.clear();
                    mSearchResultAdapter.notifyDataSetChanged();
                }
                getSearchResult(newText);
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                // Do your task here
                getSearchResult(query);
                return false;
            }

        });
    }

    private void getSearchResult(String query) {

        //showHideProgressBar(true);
        new ApiProvider().getSearchResult(new ApiCallBack() {
            @Override
            public void onError(Exception e) {

                //showHideProgressBar(false);
            }

            @Override
            public void onModel(BaseModel baseModel) {

                //showHideProgressBar(false);
                if (baseModel == null || !(baseModel instanceof SearchResultModel)) return;

                SearchResultModel searchResultModel = (SearchResultModel) baseModel;

                if (searchResultModel.isBatchComplete()
                        && searchResultModel.getQuerySearchResultModel() != null
                        && searchResultModel.getQuerySearchResultModel().getPages() != null
                        && searchResultModel.getQuerySearchResultModel().getPages().size() > 0){

                    mPageQSRModelList.clear();
                    mPageQSRModelList = searchResultModel.getQuerySearchResultModel().getPages();
                    setUpRecyclerView();
                }
            }

            @Override
            public void onJson(JsonObject jsonObject) {

                //showHideProgressBar(false);
            }

            @Override
            public void onAPIFail() {
                //showHideProgressBar(false);
            }
        }, query);
    }

    private void setUpRecyclerView() {
        //here set the recycler view and set the adapter
        mSearchResultAdapter = new SearchResultAdapter(mPageQSRModelList, mSearchResultItemClickCallBack);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mSearchResultAdapter);
    }

    SearchResultItemClickCallBack mSearchResultItemClickCallBack = new SearchResultItemClickCallBack() {
        @Override
        public void onItemClick(int position) {
            if (mPageQSRModelList.get(position) == null || mPageQSRModelList.get(position).getPageId() == null) return;

            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            intent.putExtra(Constants.PAGE_ID, mPageQSRModelList.get(position).getPageId());
            intent.putExtra(Constants.REQUEST_NAME, mPageQSRModelList.get(position).getTitle());
            startActivity(intent);
        }
    };

    public void showHideProgressBar(boolean flag) {
        if (flag && !myProgressbar.isShowing()) {
            myProgressbar.show();
        } else if (!flag && myProgressbar.isShowing()){
            myProgressbar.dismiss();
        }
    }

}
