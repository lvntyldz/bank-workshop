package com.ba;


import com.rometools.rome.feed.synd.SyndEntry;

import java.util.List;

public abstract class BaseModel {

    private String title;
    private String description;

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

    public abstract List<SyndEntry> getEntries();
}
