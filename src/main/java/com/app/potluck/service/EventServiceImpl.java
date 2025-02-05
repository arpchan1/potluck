package com.app.potluck.service;

import com.app.potluck.dto.EventDTO;
import com.app.potluck.entity.Event;
import com.app.potluck.entity.User;
import com.app.potluck.repository.EventRepository;
import com.app.potluck.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public EventServiceImpl(EventRepository eventRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EventDTO createEvent(EventDTO eventDTO, Long hostId) {
        Optional<User> host = userRepository.findById(hostId);
        if (host.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Host not found");
        }
        Event event = modelMapper.map(eventDTO, Event.class);
        event.setHost(host.get());
        event.setCreatedAt(LocalDateTime.now());
        event.setUpdatedAt(LocalDateTime.now());

        Event savedEvent = eventRepository.save(event);
        return modelMapper.map(savedEvent, EventDTO.class);
    }

    @Override
    public EventDTO getEventById(Long eventId) {
        Optional<Event> event = eventRepository.findById(eventId);
        if (event.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
        }
        return modelMapper.map(event.get(), EventDTO.class);
    }

    @Override
    public List<EventDTO> getUpcomingEventsForUser(Long userId) {
        List<Event> upcomingEvents = eventRepository.findByHostUserIdAndDateTimeAfter(userId, LocalDateTime.now());
        return upcomingEvents.stream().map(event -> modelMapper.map(event, EventDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<EventDTO> getPastEventsForUser(Long userId) {
        List<Event> pastEvents = eventRepository.findByHostUserIdAndDateTimeBefore(userId, LocalDateTime.now());
        return pastEvents.stream().map(event -> modelMapper.map(event, EventDTO.class)).collect(Collectors.toList());
    }


    @Override
    public EventDTO updateEvent(EventDTO eventDTO, Long eventId, Long hostId) {
        Optional<Event> existingEvent = eventRepository.findById(eventId);
        if (existingEvent.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
        }

        Event event = existingEvent.get();
        if(!event.getHost().getUserId().equals(hostId)){
             throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorized to update this event");
        }

        modelMapper.map(eventDTO, event); // Update existing event with DTO data
        event.setUpdatedAt(LocalDateTime.now());
        Event updatedEvent = eventRepository.save(event);
        return modelMapper.map(updatedEvent, EventDTO.class);
    }

    @Override
    public void deleteEvent(Long eventId, Long hostId) {
        Optional<Event> existingEvent = eventRepository.findById(eventId);
        if (existingEvent.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
        }
        Event event = existingEvent.get();
         if(!event.getHost().getUserId().equals(hostId)){
             throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorized to delete this event");
        }
        eventRepository.deleteById(eventId);
    }

    @Override
    public List<EventDTO> searchEvents(String location, Integer radius, String type) {
        List<Event> events = eventRepository.searchEvents(location, type);
        return events.stream()
                .map(event -> modelMapper.map(event, EventDTO.class))
                .collect(Collectors.toList());
    }

    
}