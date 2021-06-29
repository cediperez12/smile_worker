package com.smile.worker.Models;

public class WorkerProfession {
    private String workerProfession, workerDescription;

    public WorkerProfession(String workerProfession, String workerDescription) {
        this.workerProfession = workerProfession;
        this.workerDescription = workerDescription;
    }

    public WorkerProfession() {
    }

    public String getWorkerProfession() {
        return workerProfession;
    }

    public void setWorkerProfession(String workerProfession) {
        this.workerProfession = workerProfession;
    }

    public String getWorkerDescription() {
        return workerDescription;
    }

    public void setWorkerDescription(String workerDescription) {
        this.workerDescription = workerDescription;
    }
}
