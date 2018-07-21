package com.sonukgupta.mtcodingtest.network;


import com.sonukgupta.mtcodingtest.model.SearchResultModel;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by sonu on 21/7/18.
 */

public interface ApiServiceInterface {

    @GET("/w/api.php?action=query&format=json&prop=pageimages%7Cpageterms&generator=prefixsearch&redirects=1&formatversion=2&piprop=thumbnail&pithumbsize=180&pilimit=10&wbptterms=description&gpssearch=Sachin+T&gpslimit=10")
    Observable<SearchResultModel> getSearchResult(/*@Path("searchQuery") String searchQuery*/);

//    @POST("v2/customer/mobile/location")
//    Observable<CommonApiResponseModel> updateLocation(@Body UpdateLatLongModel updateLatLongModel);
}
