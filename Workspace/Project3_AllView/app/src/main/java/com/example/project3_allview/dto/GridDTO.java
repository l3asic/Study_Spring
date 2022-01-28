package com.example.project3_allview.dto;

public class GridDTO {
    private String title , content;
    private int imgresId;
    //alt + insert key

    public GridDTO(String title, String content, int imgresId) {
        this.title = title;
        this.content = content;
        this.imgresId = imgresId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImgresId() {
        return imgresId;
    }

    public void setImgresId(int imgresId) {
        this.imgresId = imgresId;
    }
}
