package com.example.trackersystem.Controller;

import com.example.trackersystem.Api.ApiResponse;
import com.example.trackersystem.Model.Tracker;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/tracker")
public class TrackerController {

    ArrayList<Tracker> trackers =  new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Tracker> getTrackers () {
        return trackers;
    }

    @PostMapping("/add")
    public ApiResponse addTracker (@RequestBody Tracker tracker) {
        trackers.add(tracker);
        return new ApiResponse("Tracker added  successfully");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateTracker (@PathVariable int index, @RequestBody Tracker tracker) {
        trackers.set(index, tracker);
        return new ApiResponse("Tracker updated successfully");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteTracker (@PathVariable int index) {
        trackers.remove(index);
        return new ApiResponse("Tracker deleted Successfully");
    }

    @PutMapping("/status/{index}")
    public ApiResponse updateStatus (@PathVariable int index) {
        if (trackers.get(index).getStatus().equalsIgnoreCase("not done")) {
            trackers.get(index).setStatus("done");
            return new ApiResponse("Status updated successfully");
        }
        return null;
    }

    @PutMapping("/search/{title}")
    public Tracker searchByTitle (@PathVariable String title) {
        for (int i = 0; i < trackers.size(); i++) {
            if (trackers.get(i).getTitle().equalsIgnoreCase(title)) {
                return trackers.get(i);
            }
        }
        return null;
    }

    @PutMapping("/all/{compName}")
    public ArrayList<Tracker> projectByCompanyName (@PathVariable String compName) {
        ArrayList<Tracker> companyName = new ArrayList<>();

        for (int i = 0; i < trackers.size(); i++) {
            if (trackers.get(i).getCompanyName().equalsIgnoreCase(compName)) {
                companyName.add(trackers.get(i));
            }
        }
        return companyName;
    }



}
