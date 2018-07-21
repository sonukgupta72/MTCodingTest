package com.sonukgupta.mtcodingtest.network;

import com.google.gson.JsonObject;
import com.sonukgupta.mtcodingtest.model.BaseModel;

/**
 * Created by sonu on 21/7/18.
 */

public interface ApiCallBack {

    void onError(Exception e);

    void onModel(BaseModel baseModel);

    void onJson(JsonObject jsonObject);

    void onAPIFail();
}
