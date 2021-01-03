package org.ninjaware.notebook.model.core;

import java.time.LocalDateTime;
import org.ninjaware.notebook.model.enums.PageStyle;
import org.ninjaware.notebook.model.enums.SortOrder;
import org.ninjaware.notebook.model.enums.SortStyle;
import org.ninjaware.notebook.model.system.EntityStatus;

public class Page {

    private String uid;
    private String externalId;
    private String title;
    private String description;
    private PageStyle pageStyle;
    private SortStyle sortStyle;
    private SortOrder sortOrder;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private EntityStatus state;


    public Page() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
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

    public PageStyle getPageStyle() {
        return pageStyle;
    }

    public void setPageStyle(PageStyle pageStyle) {
        this.pageStyle = pageStyle;
    }

    public SortStyle getSortStyle() {
        return sortStyle;
    }

    public void setSortStyle(SortStyle sortStyle) {
        this.sortStyle = sortStyle;
    }

    public SortOrder getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public EntityStatus getState() {
        return state;
    }

    public void setState(EntityStatus state) {
        this.state = state;
    }
}
