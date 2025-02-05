package com.app.potluck.dto;

import java.time.LocalDateTime;
import java.util.List;

public class EventDTO {

    private Long eventId;
    private String name;
    private String description;
    private String location;
    private LocalDateTime dateTime;
    private int guestCount;
    private boolean isPrivate;
    private UserDTO host; // Host details as a separate DTO
    private List<MenuItemDTO> menuItems; // List of MenuItemDTOs

    // Instead of guests list, use a separate field for attendee details
    private List<EventAttendeeDTO> attendees;

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

    public void setPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public UserDTO getHost() {
        return host;
    }

    public void setHost(UserDTO host) {
        this.host = host;
    }

    public List<MenuItemDTO> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItemDTO> menuItems) {
        this.menuItems = menuItems;
    }

    public List<EventAttendeeDTO> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<EventAttendeeDTO> attendees) {
        this.attendees = attendees;
    }
}