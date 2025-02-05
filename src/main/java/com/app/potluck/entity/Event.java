package com.app.potluck.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime; // Use LocalDateTime for date and time
import java.util.List;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", nullable = false)
    private Long eventId;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT") // Use TEXT for longer descriptions
    private String description;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    private int guestCount;

    @Column(nullable = false)
    private boolean isPrivate;

    @ManyToOne // Many events can have one host
    @JoinColumn(name = "host_id", nullable = false)
    private User host;

    @OneToMany(mappedBy = "event") // One event can have many menu items
    private List<MenuItem> menuItems;

    // Removed guests list
    // Replaced with

    @OneToMany(mappedBy = "event") // One event can have many attendees (through EventAttendee)
    private List<EventAttendee> attendees;

    @Column(name = "created_at") // Explicitly map to database column
    private LocalDateTime createdAt;

    @Column(name = "updated_at") // Explicitly map to database column
    private LocalDateTime updatedAt;

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getGuestCount() {
        return guestCount;
    }

    public void setGuestCount(int guestCount) {
        this.guestCount = guestCount;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public User getHost() {
        return host;
    }

    public void setHost(User host) {
        this.host = host;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public List<EventAttendee> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<EventAttendee> attendees) {
        this.attendees = attendees;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}