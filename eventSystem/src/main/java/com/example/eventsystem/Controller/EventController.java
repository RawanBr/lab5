package com.example.eventsystem.Controller;

import com.example.eventsystem.Api.ApiResponse;
import com.example.eventsystem.Model.Event;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    ArrayList<Event> events = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Event> getEvents () {
        return events;
    }

    @PostMapping("/add")
    public ApiResponse addEvent (@RequestBody Event event) {
        events.add(event);
        return new ApiResponse("Event added successfully");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateEvent (@PathVariable int index, @RequestBody Event event) {
        events.set(index, event);
        return new ApiResponse("Event updated successfully");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteEvent (@PathVariable int index) {
        events.remove(index);
        return new ApiResponse("Event deleted successfully");
    }

    @PutMapping("/change/{index}/{capacity}")
    public ApiResponse changeCapacity (@PathVariable int index, @PathVariable int capacity) {
        events.get(index).setCapacity(capacity);
        return new ApiResponse("Capacity changed successfully");
    }

    @PutMapping("/search/{id}")
    public Event searchById (@PathVariable int id) {
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getId() == id) {
                return events.get(i);
            }
        }
        return null;
    }
}
