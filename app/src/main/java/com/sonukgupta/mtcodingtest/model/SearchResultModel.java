package com.sonukgupta.mtcodingtest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by sonu on 21/7/18.
 */

public class SearchResultModel implements BaseModel {
    @SerializedName("batchcomplete")
    @Expose
    private boolean batchComplete;

    @SerializedName("continue")
    @Expose
    private ContinueSearchResultModel continueSearchResultModel;

    @SerializedName("query")
    @Expose
    private QuerySearchResultModel querySearchResultModel;

    public boolean isBatchComplete() {
        return batchComplete;
    }

    public void setBatchComplete(boolean batchComplete) {
        this.batchComplete = batchComplete;
    }

    public ContinueSearchResultModel getContinueSearchResultModel() {
        return continueSearchResultModel;
    }

    public void setContinueSearchResultModel(ContinueSearchResultModel continueSearchResultModel) {
        this.continueSearchResultModel = continueSearchResultModel;
    }

    public QuerySearchResultModel getQuerySearchResultModel() {
        return querySearchResultModel;
    }

    public void setQuerySearchResultModel(QuerySearchResultModel querySearchResultModel) {
        this.querySearchResultModel = querySearchResultModel;
    }

}
