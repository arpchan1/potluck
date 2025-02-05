package com.app.potluck.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "menu_items") // Map to the correct table name
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne // Many menu items can belong to one event
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne // Many menu items can be contributed by one user
    @JoinColumn(name = "contributor_id")
    private User contributor;

    // Getters and setters
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getContributor() {
        return contributor;
    }

    public void setContributor(User contributor) {
        this.contributor = contributor;
    }
}