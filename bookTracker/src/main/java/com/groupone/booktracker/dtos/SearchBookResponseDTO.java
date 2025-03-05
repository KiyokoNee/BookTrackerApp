package com.groupone.booktracker.dtos;

import java.util.List;

public class SearchBookResponseDTO {
    private List<SearchBookDocDTO> docs;

    public SearchBookResponseDTO() {}

    public List<SearchBookDocDTO> getDocs() {
        return docs;
    }

    public void setDocs(List<SearchBookDocDTO> docs) {
        this.docs = docs;
    }
}
