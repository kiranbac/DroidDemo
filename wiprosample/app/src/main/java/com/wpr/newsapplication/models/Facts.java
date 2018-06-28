package com.wpr.newsapplication.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Facts {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("rows")
    @Expose
    private List<Row> rows = null;

    /**
     * No args constructor for use in serialization
     */
    public Facts() {
    }

    /**
     *
     * @param title
     * @param rows
     */
    public Facts(String title, List<Row> rows) {
        super();
        this.title = title;
        this.rows = rows;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }


    public class Row {

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("imageHref")
        @Expose
        private Object imageHref;

        /**
         * No args constructor for use in serialization
         */
        public Row() {
        }

        /**
         *
         * @param title
         * @param description
         * @param imageHref
         */
        public Row(String title, String description, Object imageHref) {
            super();
            this.title = title;
            this.description = description;
            this.imageHref = imageHref;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Object getImageHref() {
            return imageHref;
        }

        public void setImageHref(Object imageHref) {
            this.imageHref = imageHref;
        }

    }
}
