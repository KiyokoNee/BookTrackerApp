package com.groupone.booktracker.dtos;

import java.util.List;

public class SearchBookResponseDTO {
    private Long start;
    private Long numFound;
    private List<SearchBookDocDTO> docs;

    public SearchBookResponseDTO() {}

    public SearchBookResponseDTO(Long start, Long numFound, List<SearchBookDocDTO> docs) {
        this.start = start;
        this.numFound = numFound;
        this.docs = docs;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getNumFound() {
        return numFound;
    }

    public void setNumFound(Long numFound) {
        this.numFound = numFound;
    }

    public List<SearchBookDocDTO> getDocs() {
        return docs;
    }

    public void setDocs(List<SearchBookDocDTO> docs) {
        this.docs = docs;
    }
}
