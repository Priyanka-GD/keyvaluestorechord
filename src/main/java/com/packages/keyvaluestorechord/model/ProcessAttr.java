package com.packages.keyvaluestorechord.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;

public class ProcessAttr {
    private int processId;
    private String processName;
    private int startRange;
    private int endRange;
    private int port;
    @JsonIgnore
    ProcessAttr successor;
    @JsonIgnore
    ProcessAttr predecessor;
    Map<Integer, User> dataStore;
    private ProcessAttr[] fingerTable;

    public ProcessAttr (int processId, int startRange, int endRange, int port) {
        this.processId = processId;
        this.startRange = startRange;
        this.endRange = endRange;
        this.port = port;
        this.fingerTable = new ProcessAttr[100];
    }

    public int getProcessId () {
        return processId;
    }

    public void setProcessId (int processId) {
        this.processId = processId;
    }

    public String getProcessName () {
        return processName;
    }

    public void setProcessName (String processName) {
        this.processName = processName;
    }

    public int getStartRange () {
        return startRange;
    }

    public void setStartRange (int startRange) {
        this.startRange = startRange;
    }

    public int getEndRange () {
        return endRange;
    }

    public void setEndRange (int endRange) {
        this.endRange = endRange;
    }

    public int getPort () {
        return port;
    }

    public void setPort (int port) {
        this.port = port;
    }

    public ProcessAttr getSuccessor () {
        return successor;
    }

    public void setSuccessor (ProcessAttr successor) {
        this.successor = successor;
    }

    public ProcessAttr getPredecessor () {
        return predecessor;
    }

    public void setPredecessor (ProcessAttr predecessor) {
        this.predecessor = predecessor;
    }

    public Map<Integer, User> getDataStore () {
        return dataStore;
    }

    public void setDataStore (Map<Integer, User> dataStore) {
        this.dataStore = dataStore;
    }

    public ProcessAttr[] getFingerTable () {
        return fingerTable;
    }

    public void setFingerTable (ProcessAttr[] fingerTable) {
        this.fingerTable = fingerTable;
    }
}
