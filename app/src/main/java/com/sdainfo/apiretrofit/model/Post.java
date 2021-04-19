package com.sdainfo.apiretrofit.model;

public class Post {
    private String id;
    private String idUser;
    private String title;
    private String bady;

    public Post() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBady() {
        return bady;
    }

    public void setBady(String bady) {
        this.bady = bady;
    }
}
