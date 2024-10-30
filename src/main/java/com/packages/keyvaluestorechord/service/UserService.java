package com.packages.keyvaluestorechord.service;

import com.packages.keyvaluestorechord.model.ProcessAttr;
import com.packages.keyvaluestorechord.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final List<ProcessAttr> processes;

    public UserService (List<ProcessAttr> processes) {
        this.processes = processes;
    }

    public String assignUserToProcess (User user) {
        for (ProcessAttr process : processes) {
            if (user.getUserId() >= process.getStartRange() && user.getUserId() <= process.getEndRange()) {
                Map<Integer, User> processDataStore = process.getDataStore();
                processDataStore.put(user.getUserId(), user);
                return "User " + user.getUserId() + " assigned to process in range: " +
                        process.getStartRange() + " - " + process.getEndRange();
            }
        }
        return "No process found for the user ID: " + user.getUserId();
    }

    public User getUserFromProcess (String userName) {
        int hashValue = calculateHash(userName);

        for (ProcessAttr process : processes) {
            if (hashValue >= process.getStartRange() && hashValue <= process.getEndRange()) {
                Map<Integer, User> processDataStore = process.getDataStore();
                return processDataStore.get(hashValue);
            }
        }
        return null;
    }

    private int calculateHash (String userId) {
        return Math.abs(userId.hashCode()) % 100;
    }
}
