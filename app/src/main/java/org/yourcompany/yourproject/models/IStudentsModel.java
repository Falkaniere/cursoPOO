package org.yourcompany.yourproject.models;

import java.util.Random;

public class IStudentsModel {

    private String id;
    private String registrationCode;
    private String name;
    private int age;
    private String courseCode;

    public IStudentsModel(String registrationCode, String name, int age, String courseCode) {
        this.id = generateId();
        this.registrationCode = registrationCode;
        this.name = name;
        this.age = age;
        this.courseCode = courseCode;
    }

    // getters
    public String getId() {
        return id;
    }

    public String getRegistrationCode() {
        return registrationCode;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCourseCode() {
        return courseCode;
    }

    // setters
    public void setId(String id) {
        this.id = id;
    }

    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
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
