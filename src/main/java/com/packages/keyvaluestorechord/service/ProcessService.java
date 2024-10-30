package com.packages.keyvaluestorechord.service;

import com.packages.keyvaluestorechord.model.ProcessAttr;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProcessService {
    List<ProcessAttr> processes = new ArrayList<>();
    private int nextProcessId = 1;
    private int basePort = 8081;

    public ProcessAttr addNewProcess (int startRange, int endRange) {
        ProcessAttr newProcess = new ProcessAttr(nextProcessId++, startRange, endRange, basePort++);
        processes.add(newProcess);
        Collections.sort(processes, Comparator.comparingInt(ProcessAttr::getStartRange));
        updateProcessLinks();
        System.out.println(getProcessDetails(newProcess));
        return newProcess;
    }

    private void updateProcessLinks () {
        int len = processes.size();
        for (int i = 0; i < processes.size(); i++) {
            ProcessAttr currentProcess = processes.get(i);
            ProcessAttr predecessor = null;
            ProcessAttr successor = null;
            if (i > 0) {
                predecessor = processes.get(i - 1);
            }
            if (i < processes.size() - 1) {
                successor = processes.get(i + 1);
                if (predecessor == null)
                    predecessor = processes.get(len - 1);
            } else {
                successor = processes.get(0);
            }
            currentProcess.setSuccessor(successor);
            currentProcess.setPredecessor(predecessor);
        }
    }

    private String getProcessDetails (ProcessAttr process) {
        return "Process ID: " + process.getProcessId() +
                ", Start Range: " + process.getStartRange() +
                ", End Range: " + process.getEndRange() +
                ", Port: " + process.getPort() +
                ", Successor ID: " + (process.getSuccessor() != null ? process.getSuccessor().getProcessId() : "None") +
                ", Predecessor ID: " + (process.getPredecessor() != null ? process.getPredecessor().getProcessId() : "None");
    }
}