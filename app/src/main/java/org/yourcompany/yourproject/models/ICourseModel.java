package org.yourcompany.yourproject.models;

import java.util.Random;

public class ICourseModel {

    private final String id;
    private String name;
    private String code;
    private String courseShift;
    private String disciplineCode;
    private IDisciplineModel discipline;

    public ICourseModel(String name, String code, String courseShift, String disciplineCode) {
        this.id = generateId();
        this.name = name;
        this.code = code;
        this.courseShift = courseShift;
        this.disciplineCode = disciplineCode;
    }

    // getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getCourseShift() {
        return courseShift;
    }

    public String getDisciplineCode() {
        return disciplineCode;
    }

    public IDisciplineModel getDiscipline() {
        return discipline;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCourseShift(String courseShift) {
        this.courseShift = courseShift;
    }

    public void setDiscipline(String disciplineCode) {
        this.disciplineCode = disciplineCode;
    }

    public void setDiscipline(IDisciplineModel discipline) {
        this.discipline = discipline;
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
