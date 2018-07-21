package com.sonukgupta.mtcodingtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.sonukgupta.mtcodingtest.model.BaseModel;
import com.sonukgupta.mtcodingtest.model.PageQSRModel;
import com.sonukgupta.mtcodingtest.model.SearchResultModel;
import com.sonukgupta.mtcodingtest.network.ApiCallBack;
import com.sonukgupta.mtcodingtest.network.ApiProvider;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SearchResultAdapter mSearchResultAdapter;
    List<PageQSRModel> mPageQSRModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rvSearchResult);

        new ApiProvider().getSearchResult(new ApiCallBack() {
            @Override
            public void onError(Exception e) {

            }

            @Override
            public void onModel(BaseModel baseModel) {

                if (baseModel == null || !(baseModel instanceof SearchResultModel)) return;

                SearchResultModel searchResultModel = (SearchResultModel) baseModel;

                if (searchResultModel.isBatchComplete()
                        && searchResultModel.getQuerySearchResultModel() != null
                        && searchResultModel.getQuerySearchResultModel().getPages() != null
                        && searchResultModel.getQuerySearchResultModel().getPages().size() > 0){

                    mPageQSRModelList = searchResultModel.getQuerySearchResultModel().getPages();
                    setUpRecyclerView();
                }
            }

            @Override
            public void onJson(JsonObject jsonObject) {

            }

            @Override
            public void onAPIFail() {

            }
        }, "");
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

            //Toast.makeText(MainActivity.this, position + " : clicked", Toast.LENGTH_LONG).show();
        }
    };
}
