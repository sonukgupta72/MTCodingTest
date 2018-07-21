package com.sonukgupta.mtcodingtest.network;

import android.util.Log;

import com.sonukgupta.mtcodingtest.model.SearchResultModel;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sonu on 21/7/18.
 */

public class ApiProvider {

    private static final String REQUEST_URL = "https://en.wikipedia.org/";
    private static String TAG = "ApiProvider";

    protected ApiServiceNetwork mApiServiceNetwork = ApiServiceNetwork.getInstance();

    private boolean mInternetConnected = true;

    public boolean isInternetConnected() {
        return mInternetConnected;
    }

    public void setInternetConnected(boolean internetConnected) {
        this.mInternetConnected = internetConnected;
    }

    public void getSearchResult(final ApiCallBack apiCallBack, final String searchQuery) {
        mApiServiceNetwork.getNetworkService(null, REQUEST_URL)
                .getSearchResult(/*searchQuery*/)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SearchResultModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Exception", e);
                        apiCallBack.onAPIFail();
                    }

                    @Override
                    public void onNext(SearchResultModel model) {
                        apiCallBack.onModel(model);
                    }
                });
    }


}

