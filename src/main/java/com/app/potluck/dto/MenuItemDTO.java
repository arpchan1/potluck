package com.app.potluck.dto;

public class MenuItemDTO {

    private Long id; // Unique identifier for the menu item
    private String name;
    private String description;
    private String contributorName; // Name of the contributor (instead of User object)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getContributorName() {
        return contributorName;
    }
    public void setContributorName(String contributorName) {
        this.contributorName = contributorName;
    }

    // Getters and setters (and any other methods you need)
}