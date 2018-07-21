package com.sonukgupta.mtcodingtest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sonu on 21/7/18.
 */

public class QuerySearchResultModel implements BaseModel{
    @SerializedName("redirects")
    @Expose
    private List<RedirectQSRModel> redirects;

    @SerializedName("pages")
    @Expose
    private List<PageQSRModel> pages;

    public List<RedirectQSRModel> getRedirects() {
        return redirects;
    }

    public void setRedirects(List<RedirectQSRModel> redirects) {
        this.redirects = redirects;
    }

    public List<PageQSRModel> getPages() {
        return pages;
    }

    public void setPages(List<PageQSRModel> pages) {
        this.pages = pages;
    }
}
