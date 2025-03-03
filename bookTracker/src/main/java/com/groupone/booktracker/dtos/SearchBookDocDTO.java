package com.groupone.booktracker.dtos;

import java.util.List;

public class SearchBookDocDTO {
    private Long coverI;
    private Long hasFulltext;
    private Long editionCount;
    private String title;
    private List<String> authorName;
    private Long firstPublishYear;
    private String key;
    private List<String> ia;
    private List<String> authorKey;
    private boolean publicScanB;

    public SearchBookDocDTO() {}

    public SearchBookDocDTO(Long coverI, Long hasFulltext, Long editionCount, String title, List<String> authorName, Long firstPublishYear, String key, List<String> ia, List<String> authorKey, boolean publicScanB) {
        this.coverI = coverI;
        this.hasFulltext = hasFulltext;
        this.editionCount = editionCount;
        this.title = title;
        this.authorName = authorName;
        this.firstPublishYear = firstPublishYear;
        this.key = key;
        this.ia = ia;
        this.authorKey = authorKey;
        this.publicScanB = publicScanB;
    }

    public Long getCoverI() {
        return coverI;
    }

    public void setCoverI(Long coverI) {
        this.coverI = coverI;
    }

    public Long getHasFulltext() {
        return hasFulltext;
    }

    public void setHasFulltext(Long hasFulltext) {
        this.hasFulltext = hasFulltext;
    }

    public Long getEditionCount() {
        return editionCount;
    }

    public void setEditionCount(Long editionCount) {
        this.editionCount = editionCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthorName() {
        return authorName;
    }

    public void setAuthorName(List<String> authorName) {
        this.authorName = authorName;
    }

    public Long getFirstPublishYear() {
        return firstPublishYear;
    }

    public void setFirstPublishYear(Long firstPublishYear) {
        this.firstPublishYear = firstPublishYear;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getIa() {
        return ia;
    }

    public void setIa(List<String> ia) {
        this.ia = ia;
    }

    public List<String> getAuthorKey() {
        return authorKey;
    }

    public void setAuthorKey(List<String> authorKey) {
        this.authorKey = authorKey;
    }

    public boolean isPublicScanB() {
        return publicScanB;
    }

    public void setPublicScanB(boolean publicScanB) {
        this.publicScanB = publicScanB;
    }
}
