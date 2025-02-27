package com.example.demo.DAO;

import com.example.demo.entities.Event;
import com.example.demo.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventDAO {
    private final EventRepository eventRepository;

    Event getEventById(long id){
        return eventRepository.findEventById(id);
    }
}
