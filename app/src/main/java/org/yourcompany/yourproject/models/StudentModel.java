package org.yourcompany.yourproject.models;

import org.yourcompany.yourproject.services.filesService.FileService;

public interface StudentModel {

    void registerStudent();

    void consultStudent(FileService fileService);

    void removeStudent();

    void updateStudent();
}
