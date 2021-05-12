package com.example.demo.services;

import com.example.demo.models.Activity;
import com.example.demo.repo.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public List<Activity> findAll() {
        ArrayList<Activity> activities = new ArrayList<>();
        activityRepository.findAll().forEach(activities::add);
        return activities;
    }

    public Activity save(Activity activity) {
        return activityRepository.save(activity);
    }

    public Optional<Activity> findById(long id) {
        return  activityRepository.findById(id);
    }

    public boolean existsById(long id) {
        return activityRepository.existsById(id);
    }

    public void delete(Activity activity) {
        activityRepository.delete(activity);
    }
}

