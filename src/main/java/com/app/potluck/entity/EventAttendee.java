package com.app.potluck.entity;
import com.app.potluck.enums.*;
import jakarta.persistence.*;

@Entity
@Table(name = "event_attendees")
public class EventAttendee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventAttendeeId;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private EventAttendeeRole role;

    @Enumerated(EnumType.STRING)
    @Column(name="rsvp_status")
    private RsvpStatus rsvpStatus;

    public RsvpStatus getRsvpStatus() {
        return rsvpStatus;
    }

    public void setRsvpStatus(RsvpStatus rsvpStatus) {
        this.rsvpStatus = rsvpStatus;
    }

    public enum EventAttendeeRole {
        HOST, GUEST
    }
    

}

