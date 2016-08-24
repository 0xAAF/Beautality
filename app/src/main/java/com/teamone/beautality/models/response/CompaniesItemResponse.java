package com.teamone.beautality.models.response;

import java.util.List;

/**
 * Created by oshhepkov on 20.08.16.
 */
public class CompaniesItemResponse {
    private String id, title, image;
    private List<String> photos;
    private CompaniesOwnerResponse owner;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public CompaniesOwnerResponse getOwner() {
        return owner;
    }

    public void setOwner(CompaniesOwnerResponse owner) {
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CompaniesItemResponse generate() {
        setTitle("Title");
        owner = new CompaniesOwnerResponse();
        owner.setFirstName("FirstName");
        owner.setLastName("LastName");
        return this;
    }
}
