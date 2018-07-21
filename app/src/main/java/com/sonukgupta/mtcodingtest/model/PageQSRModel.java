package com.sonukgupta.mtcodingtest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sonu on 21/7/18.
 */

public class PageQSRModel implements BaseModel{

    @SerializedName("pageid")
    @Expose
    private String pageId;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("index")
    @Expose
    private String index;

    @SerializedName("thumbnail")
    @Expose
    private Thumbnail thumbnail;

    @SerializedName("terms")
    @Expose
    private Terms terms;

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Terms getTerms() {
        return terms;
    }

    public void setTerms(Terms terms) {
        this.terms = terms;
    }

    public class Thumbnail implements BaseModel {

        @SerializedName("source")
        @Expose
        private String source;

        @SerializedName("width")
        @Expose
        private Integer width;

        @SerializedName("height")
        @Expose
        private Integer height;

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }
    }

    public class Terms implements BaseModel {

        @SerializedName("description")
        @Expose
        private List<String> description;

        public List<String> getDescription() {
            return description;
        }

        public void setDescription(List<String> description) {
            this.description = description;
        }
    }
}
