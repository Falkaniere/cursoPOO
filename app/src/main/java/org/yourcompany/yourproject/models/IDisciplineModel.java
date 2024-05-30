package org.yourcompany.yourproject.models;

import java.util.Random;

public class IDisciplineModel {

    private final String id;
    private String name;
    private int workload;
    private String code;
    private double rating;

    public IDisciplineModel(String name, int workload, String code, double rating) {
        this.id = generateId();
        this.name = name;
        this.workload = workload;
        this.code = code;
        this.rating = rating;
    }

    // getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWorkload() {
        return workload;
    }

    public String getCode() {
        return code;
    }

    public double getRating() {
        return rating;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setWorkload(int workload) {
        this.workload = workload;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    private String generateId() {
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int randomIndex = random.nextInt(allowedChars.length());
            char randomChar = allowedChars.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
