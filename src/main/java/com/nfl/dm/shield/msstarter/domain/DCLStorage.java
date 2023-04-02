package com.nfl.dm.shield.msstarter.domain;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

import java.time.LocalDateTime;

public class DCLStorage {

    @CsvBindByPosition(position = 0)
    private String assetType;

    @CsvBindByPosition(position = 1)
    private String assetId;

    @CsvBindByPosition(position = 2)
    private String name;

    @CsvBindByPosition(position = 3)
    private String contentSource;

    @CsvBindByPosition(position = 4)
    private double fileSize;

    @CsvBindByPosition(position = 5)
    private float duration;

    @CsvDate(value = "yyyy-MM-dd'T'HH:mm:ss")
    @CsvBindByPosition(position = 6)
    private LocalDateTime dateCreated;

    @CsvBindByPosition(position = 7)
    private String dCLAssetType;

    @CsvBindByPosition(position = 8)
    private String eventType;

    @CsvBindByPosition(position = 9)
    private String showName;

    @CsvBindByPosition(position = 10)
    private String weekType;

    @CsvBindByPosition(position = 11)
    private String feedType;

    @CsvBindByPosition(position = 12)
    private String contentDescriptionSecondary;

    @CsvBindByPosition(position = 13)
    private String deliveredToMCPInstance;

    @CsvBindByPosition(position = 14)
    private String syndicateTo;

    @CsvBindByPosition(position = 15)
    private String mCPProgramName;

    @CsvBindByPosition(position = 16)
    private String sport;

    @CsvBindByPosition(position = 17)
    private String aWSStorageClass;

    @CsvBindByPosition(position = 18)
    private String aWSStatus;

    @CsvBindByPosition(position = 19)
    private String studioShowElement;

    private String csPlusAt;

    private String csPlusEt;

    private String csPlusAtPlusFt;

    private String csPlusAtPlusSn;

    private String csPlusSse;

    public DCLStorage() {
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentSource() {
        return contentSource;
    }

    public void setContentSource(String contentSource) {
        this.contentSource = contentSource;
    }

    public double getFileSize() {
        return fileSize;
    }

    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getdCLAssetType() {
        return dCLAssetType;
    }

    public void setdCLAssetType(String dCLAssetType) {
        this.dCLAssetType = dCLAssetType;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getWeekType() {
        return weekType;
    }

    public void setWeekType(String weekType) {
        this.weekType = weekType;
    }

    public String getFeedType() {
        return feedType;
    }

    public void setFeedType(String feedType) {
        this.feedType = feedType;
    }

    public String getContentDescriptionSecondary() {
        return contentDescriptionSecondary;
    }

    public void setContentDescriptionSecondary(String contentDescriptionSecondary) {
        this.contentDescriptionSecondary = contentDescriptionSecondary;
    }

    public String getDeliveredToMCPInstance() {
        return deliveredToMCPInstance;
    }

    public void setDeliveredToMCPInstance(String deliveredToMCPInstance) {
        this.deliveredToMCPInstance = deliveredToMCPInstance;
    }

    public String getSyndicateTo() {
        return syndicateTo;
    }

    public void setSyndicateTo(String syndicateTo) {
        this.syndicateTo = syndicateTo;
    }

    public String getmCPProgramName() {
        return mCPProgramName;
    }

    public void setmCPProgramName(String mCPProgramName) {
        this.mCPProgramName = mCPProgramName;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getaWSStorageClass() {
        return aWSStorageClass;
    }

    public void setaWSStorageClass(String aWSStorageClass) {
        this.aWSStorageClass = aWSStorageClass;
    }

    public String getaWSStatus() {
        return aWSStatus;
    }

    public void setaWSStatus(String aWSStatus) {
        this.aWSStatus = aWSStatus;
    }

    public String getStudioShowElement() {
        return studioShowElement;
    }

    public void setStudioShowElement(String studioShowElement) {
        this.studioShowElement = studioShowElement;
    }

    public String getCsPlusAt() {
        return csPlusAt;
    }

    public void setCsPlusAt() {
        this.csPlusAt = this.contentSource + "-" + this.dCLAssetType;
    }

    public String getCsPlusAtPlusFt() {
        return csPlusAtPlusFt;
    }

    public void setCsPlusAtPlusFt() {
        String game = "Game";
        if (this.dCLAssetType.equals(game)) {
            this.csPlusAtPlusFt = this.contentSource + "-" + game + "-" + this.feedType;
        } else {
            this.csPlusAtPlusFt = this.contentSource + "-" + "Exclude" + "-" + this.feedType;
        }
    }

    public String getCsPlusAtPlusSn() {
        return csPlusAtPlusSn;
    }

    public void setCsPlusAtPlusSn() {
        String show = "Show";
        if (this.dCLAssetType.equals(show)) {
            this.csPlusAtPlusSn = this.contentSource + "-" + show + "-" + this.showName;
        } else {
            this.csPlusAtPlusSn = this.contentSource + "-" + "Exclude" + "-" + this.showName;
        }
    }

    public String getCsPlusEt() {
        return csPlusEt;
    }

    public void setCsPlusEt() {
        this.csPlusEt = this.contentSource + "-" + this.eventType;
    }

    public String getCsPlusSse() {
        return csPlusSse;
    }

    public void setCsPlusSse() {
        this.csPlusSse = this.contentSource + "-" + this.studioShowElement;
    }
}
