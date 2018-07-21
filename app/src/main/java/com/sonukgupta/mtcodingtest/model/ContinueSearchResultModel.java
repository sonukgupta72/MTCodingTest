package com.sonukgupta.mtcodingtest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by sonu on 21/7/18.
 */

public class ContinueSearchResultModel implements BaseModel{

    @SerializedName("gpsoffset")
    @Expose
    private String gpsOffset;

}
