package com.app.potluck.service;

import com.app.potluck.dto.EventDTO;
import java.util.List;

public interface EventService {

    EventDTO createEvent(EventDTO eventDTO, Long hostId);

    EventDTO getEventById(Long eventId);

    List<EventDTO> getUpcomingEventsForUser(Long userId);

    List<EventDTO> getPastEventsForUser(Long userId);

    EventDTO updateEvent(EventDTO eventDTO, Long eventId, Long hostId); //Added hostId for security

    void deleteEvent(Long eventId, Long hostId); //Added hostId for security

    List<EventDTO> searchEvents(String location, Integer radius, String type);
}