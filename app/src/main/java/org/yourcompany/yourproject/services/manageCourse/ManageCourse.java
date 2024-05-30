package org.yourcompany.yourproject.services.manageCourse;

import org.yourcompany.yourproject.models.ICourseModel;

public interface ManageCourse {

    void registerCourse(ICourseModel course);

    ICourseModel consultCourse(String code);

    void removeCourse(String code);

    void updateCourse(ICourseModel updatedCourse);
}
