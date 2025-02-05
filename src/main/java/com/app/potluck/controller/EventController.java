package com.app.potluck.controller;

import com.app.potluck.dto.EventDTO;
import com.app.potluck.service.EventService;
import com.app.potluck.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;
    private final JwtUtil jwtUtil;

    public EventController(EventService eventService, JwtUtil jwtUtil) {
        this.eventService = eventService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDTO, @RequestHeader("Authorization") String token) {
        Long hostId = extractUserIdFromToken(token);
        EventDTO createdEvent = eventService.createEvent(eventDTO, hostId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent); // 201 Created
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable Long eventId) {
        return ResponseEntity.ok(eventService.getEventById(eventId));
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<EventDTO>> getUpcomingEvents(@RequestHeader("Authorization") String token) {
        Long userId = extractUserIdFromToken(token);
        List<EventDTO> events = eventService.getUpcomingEventsForUser(userId);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/past")
    public ResponseEntity<List<EventDTO>> getPastEvents(@RequestHeader("Authorization") String token) {
        Long userId = extractUserIdFromToken(token);
        List<EventDTO> events = eventService.getPastEventsForUser(userId);
        return ResponseEntity.ok(events);
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<EventDTO> updateEvent(@RequestBody EventDTO eventDTO, @PathVariable Long eventId, @RequestHeader("Authorization") String token) {
        Long hostId = extractUserIdFromToken(token);
        return ResponseEntity.ok(eventService.updateEvent(eventDTO, eventId, hostId));
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long eventId, @RequestHeader("Authorization") String token) {
         Long hostId = extractUserIdFromToken(token);
        eventService.deleteEvent(eventId, hostId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<EventDTO>> searchEvents(
            @RequestParam(value = "location", required = false) String location,
            @RequestParam(value = "radius", required = false) Integer radius,
            @RequestParam(value = "type", required = false) String type) {
        List<EventDTO> events = eventService.searchEvents(location, radius, type);
        return ResponseEntity.ok(events);
    }

    private Long extractUserIdFromToken(String token) {
        token = token.replace("Bearer ", "");
        if (!jwtUtil.validateToken(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }
        return jwtUtil.extractUserId(token);
    }
}