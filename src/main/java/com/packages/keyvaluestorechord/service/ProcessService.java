package com.packages.keyvaluestorechord.service;

import com.packages.keyvaluestorechord.model.ProcessAttr;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProcessService {
    List<ProcessAttr> processes = new ArrayList<>();
    private int nextProcessId = 1;
    private int basePort = 8081;

    public List<ProcessAttr> getProcesses () {
        return processes;
    }

    public ProcessAttr addNewProcess (int startRange, int endRange) {
        ProcessAttr newProcess = new ProcessAttr(nextProcessId++, startRange, endRange, basePort++);
        if (!processes.isEmpty()) {
            adjustRange(processes, newProcess);
        }
        processes.add(newProcess);
        Collections.sort(processes, Comparator.comparingInt(ProcessAttr::getStartRange));
        updateProcessLinks();
        for (ProcessAttr process : processes) {
            System.out.println(getProcessDetails(process));
        }
        return newProcess;
    }

    private void adjustRange (List<ProcessAttr> processes, ProcessAttr newProcess) {
        for (ProcessAttr process : processes) {
            if (newProcess.getStartRange() <= process.getEndRange()
                    && newProcess.getEndRange() >= process.getStartRange()) {
                if (process.getEndRange() >= newProcess.getStartRange()
                        && process.getStartRange() < newProcess.getStartRange()) {
                    process.setEndRange(newProcess.getStartRange() - 1);
                } else if (process.getStartRange() <= newProcess.getEndRange()
                        && process.getEndRange() > newProcess.getEndRange()) {
                    process.setStartRange(newProcess.getEndRange() + 1);
                }
            }
        }
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

    public void buildFingerTables () {
        int n = processes.size();
        for (int i = 0; i < n; i++) {
            ProcessAttr currentProcess = processes.get(i);
            ProcessAttr[] fingerTable = new ProcessAttr[100];

            for (int k = 0; k < fingerTable.length; k++) {
                // Calculate the start of the range for each entry in the finger table
                int start = (currentProcess.getStartRange() + (1 << k)) % 100;

                // Find the process responsible for this range
                ProcessAttr successor = findSuccessor(start);
                fingerTable[k] = successor;
            }
            currentProcess.setFingerTable(fingerTable);
        }
    }

    private ProcessAttr findSuccessor (int start) {
        // Find the process responsible for the given range
        for (ProcessAttr process : processes) {
            if (start >= process.getStartRange() && start <= process.getEndRange()) {
                return process;
            }
        }
        return processes.get(0); // Fallback to the first process if none found
    }

}
