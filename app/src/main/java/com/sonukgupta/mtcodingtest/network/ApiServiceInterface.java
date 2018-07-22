package com.sonukgupta.mtcodingtest.network;


import com.google.gson.JsonObject;
import com.sonukgupta.mtcodingtest.model.SearchResultModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by sonu on 21/7/18.
 */

public interface ApiServiceInterface {

    @GET("/w/api.php?")
    Observable<SearchResultModel> getSearchResult(@Query("action") String action, @Query("format") String format, @Query("gpssearch") String gpssearch, @Query("gpslimit") int gpslimit, @Query("prop") String prop, @Query("piprop") String piprop, @Query("wbptterms") String wbptterms, @Query("pithumbsize") int pithumbsize, @Query("pilimit") int pilimit, @Query("formatversion") int formatversion, @Query("redirects") int redirects, @Query("generator") String generator);
}
