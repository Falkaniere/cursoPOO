package org.yourcompany.yourproject.services.manageStudents;

import org.yourcompany.yourproject.models.IStudentsModel;

public interface ManageStudents {

    void registerStudent(IStudentsModel student);

    IStudentsModel consultStudent(String code);

    void removeStudent(String code);

    void updateStudent(IStudentsModel updatedStudent);
}
