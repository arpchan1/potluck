package com.app.potluck.repository;

import com.app.potluck.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByHostUserId(Long userId);

    List<Event> findByHostUserIdAndDateTimeAfter(Long userId, LocalDateTime now);

    List<Event> findByHostUserIdAndDateTimeBefore(Long userId, LocalDateTime now);

    @Query("SELECT e FROM Event e WHERE " +
            "(:location is null or lower(e.location) like lower(concat('%', :location, '%'))) and " +
            "(:type is null or (case when :type = 'public' then e.isPrivate = false when :type = 'private' then e.isPrivate = true else 1=1 end))")
    List<Event> searchEvents(@Param("location") String location, @Param("type") String type);

}