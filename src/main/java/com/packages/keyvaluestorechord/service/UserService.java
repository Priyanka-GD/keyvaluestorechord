package com.packages.keyvaluestorechord.service;

import com.packages.keyvaluestorechord.model.ProcessAttr;
import com.packages.keyvaluestorechord.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private final ProcessService processService;

    @Autowired
    public UserService (ProcessService processService) {
        this.processService = processService;
    }

    public String assignUserToProcess (User user) {
        List<ProcessAttr> processes = processService.getProcesses();
        for (ProcessAttr process : processes) {
            if (user.getUserId() >= process.getStartRange() && user.getUserId() <= process.getEndRange()) {
                Map<Integer, User> processDataStore = new HashMap<>();
                if (process.getDataStore() != null)
                    processDataStore = process.getDataStore();
                processDataStore.put(user.getUserId(), user);
                process.setDataStore(processDataStore);
                return "User " + user.getUserId() + " assigned to process in range: " +
                        process.getStartRange() + " - " + process.getEndRange();
            }
        }
        return "No process found for the user ID: " + user.getUserId();
    }

   /* public String getUserFromProcess (String userName) {
        int hashValue = calculateHash(userName);
        List<ProcessAttr> processes = processService.getProcesses();
        for (ProcessAttr process : processes) {
            if (hashValue >= process.getStartRange() && hashValue <= process.getEndRange()) {
                Map<Integer, User> processDataStore = process.getDataStore();
                if (processDataStore != null) {
                    User foundUSer = processDataStore.get(hashValue);
                    return getUserDetails(foundUSer);
                }
                return "No user found with name : " + userName + " in Process :" + process.getProcessId();
            }
        }
        return "No user found with name : " + userName;
    }*/

    private int calculateHash (String userId) {
        return Math.abs(userId.hashCode()) % 100;
    }

    private String getUserDetails (User user) {
        return "User Found : " + user.getName() + " address : " + user.getAddress() + " email : " + user.getEmail();
    }

    public String getUserFromProcess (String userName) {
        int hashValue = calculateHash(userName);
        ProcessAttr currentProcess = processService.getProcesses().get(0);

        while (true) {
            if (hashValue >= currentProcess.getStartRange() && hashValue <= currentProcess.getEndRange()) {
                Map<Integer, User> processDataStore = currentProcess.getDataStore();
                User existingUser = processDataStore.get(hashValue);
                return getUserDetails(existingUser);
            }
            ProcessAttr[] fingerTable = currentProcess.getFingerTable();
            ProcessAttr nextProcess = null;

            for (int k = fingerTable.length - 1; k >= 0; k--) {
                if (fingerTable[k] != null && fingerTable[k].getStartRange() <= hashValue) {
                    nextProcess = fingerTable[k];
                    break;
                }
            }
            if (nextProcess == null) {
                nextProcess = currentProcess.getSuccessor();
            }
            currentProcess = nextProcess;
        }
    }
}
