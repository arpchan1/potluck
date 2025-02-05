package com.app.potluck.dto;

import com.app.potluck.entity.EventAttendee;
import com.app.potluck.enums.RsvpStatus;

public class EventAttendeeDTO {

    private Long eventAttendeeId;
    private Long userId; // ID of the attending user
    private String userName; // Name of the attending user
    private EventAttendee role; // Role of the user in the event (host/guest)
    private RsvpStatus rsvpStatus; // RSVP status (pending, accepted, declined)

    // Getters and setters
    public Long getEventAttendeeId() {
        return eventAttendeeId;
    }

    public void setEventAttendeeId(Long eventAttendeeId) {
        this.eventAttendeeId = eventAttendeeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public EventAttendee getRole() {
        return role;
    }

    public void setRole(EventAttendee role) {
        this.role = role;
    }

    public RsvpStatus getRsvpStatus() {
        return rsvpStatus;
    }

    public void setRsvpStatus(RsvpStatus rsvpStatus) {
        this.rsvpStatus = rsvpStatus;
    }

    @Override
    public String toString() {
        return "EventAttendeeDTO{" +
                "eventAttendeeId=" + eventAttendeeId +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", role=" + role +
                ", rsvpStatus=" + rsvpStatus +
                '}';
    }
}