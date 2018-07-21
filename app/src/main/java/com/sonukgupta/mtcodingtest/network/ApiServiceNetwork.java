package com.sonukgupta.mtcodingtest.network;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sonu on 21/7/18.
 */

public class ApiServiceNetwork implements Interceptor {

    private static final ApiServiceNetwork apiService = new ApiServiceNetwork();
    private static final Retrofit.Builder retrofit = new Retrofit.Builder();
    private static final String TAG = ApiServiceNetwork.class.getCanonicalName();
    private String token, authKey;

    private ApiRequestData apiRequestData;

    private boolean showLog = true;

    private ApiServiceNetwork() {
        //hide the public constructor
    }

    public static ApiServiceNetwork getInstance() {
        return apiService;
    }

    //call this api when using other service like auth0,google etc.
    public ApiServiceInterface getNetworkService(@NonNull final String authKey, @NonNull final String tokenValue, @NonNull final String url) {
        this.token = tokenValue;
        this.authKey = authKey;
        return createRetrofitInstance(url).create(ApiServiceInterface.class);
    }

    public ApiServiceInterface getNetworkService(final ApiRequestData apiExtraData, @NonNull final String url) {
        this.showLog = true;
        this.apiRequestData = apiExtraData;
        return createRetrofitInstance(url).create(ApiServiceInterface.class);
    }

    public ApiServiceInterface getNetworkService(final boolean showLog, final ApiRequestData apiExtraData, @NonNull final String url) {
        this.showLog = showLog;
        this.apiRequestData = apiExtraData;
        return createRetrofitInstance(url).create(ApiServiceInterface.class);
    }

    private Retrofit createRetrofitInstance(String url) {
        OkHttpClient.Builder okHttpClient = new OkHttpClient().newBuilder();
        okHttpClient.addInterceptor(this);
//        if (!BuildConfig.RELEASE && showLog) {
//            okHttpClient.addInterceptor(getLogger());
//        }
        retrofit.baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient.connectTimeout(90,
                        TimeUnit.SECONDS).writeTimeout(90,
                        TimeUnit.SECONDS).readTimeout(90,
                        TimeUnit.SECONDS).build());
        return retrofit.build();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder;

        builder = request.newBuilder();

//        boolean authAdded = false;
//        if (apiRequestData != null && apiRequestData.getHeaders() != null && apiRequestData.getHeaders().size() > 0) {
//            for (Map.Entry<String, String> entry : apiRequestData.getHeaders().entrySet()) {
//                authAdded = entry.getKey().trim().equals(WebConstants.AUTH_HEADER_KEY);
//                builder.addHeader(entry.getKey(), entry.getValue());
//            }
//        }
//
//        String customerAPIKey = PrefUtil.getCustomerKey();
//        if (Validate.isEmptyString(customerAPIKey)) {
//            builder.addHeader("api-key", "" + WebConstants.KEY);
//        } else {
//            builder.addHeader("mobile-token", customerAPIKey);
//        }

        Request request1 = builder.build();
        return chain.proceed(request1);
    }

//    private okhttp3.logging.HttpLoggingInterceptor getLogger() {
//        final okhttp3.logging.HttpLoggingInterceptor logging = new okhttp3.logging.HttpLoggingInterceptor();
//        logging.setLevel(okhttp3.logging.HttpLoggingInterceptor.Level.HEADERS);
//        logging.setLevel(okhttp3.logging.HttpLoggingInterceptor.Level.BASIC);
//        logging.setLevel(okhttp3.logging.HttpLoggingInterceptor.Level.BODY);
//        return logging;
//    }
}
