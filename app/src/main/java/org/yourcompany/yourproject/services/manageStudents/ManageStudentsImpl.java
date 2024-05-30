package org.yourcompany.yourproject.services.manageStudents;

import org.yourcompany.yourproject.models.IStudentsModel;
import org.yourcompany.yourproject.services.filesService.FileService;
import org.yourcompany.yourproject.services.manageCourse.ManageCourse;
import org.yourcompany.yourproject.services.manageCourse.ManageCourseImpl;

public class ManageStudentsImpl implements ManageStudents {

    public static final String PATH = "students.txt";

    private final FileService fileService;

    public ManageStudentsImpl(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public void registerStudent(IStudentsModel student) {
        ManageCourse courseManage = new ManageCourseImpl(fileService);

        if (consultStudent(student.getId()) != null) {
            System.out.println("Student already registered!");
        } else if (courseManage.consultCourse(student.getCourseCode()) == null) {
            System.out.println("Course not found!");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("registrationCode:");
            sb.append(student.getRegistrationCode().trim());
            sb.append(",name:");
            sb.append(student.getName().trim());
            sb.append(",age:");
            sb.append(student.getAge());
            sb.append(",courseCode:");
            sb.append(student.getCourseCode());
            sb.append(";");
            fileService.writeToFile(PATH, sb.toString());
        }
    }

    @Override
    public IStudentsModel consultStudent(String registrationCode) {
        String student = fileService.readFromFile(PATH);
        String[] students = student.split(";");
        for (String s : students) {
            String[] studentData = s.split(",");
            for (String data : studentData) {
                if (data.contains(registrationCode.trim())) {
                    String[] studentInfo = s.split(",");
                    String name = studentInfo[1].replace("name:", "");
                    int age = Integer.parseInt(studentInfo[2].replace("age:", ""));
                    String courseCode = studentInfo[3].replace("courseCode:", "");
                    IStudentsModel newStudent = new IStudentsModel(registrationCode, name, age, courseCode);

                    return newStudent;
                }
            }
        }
        return null;
    }

    @Override
    public void removeStudent(String code) {
        String tempFilePath = "tempFile.txt";
        fileService.createFile(tempFilePath);

        String reader = fileService.readFromFile(PATH);
        String[] students = reader.split(";");
        for (String student : students) {
            if (!student.contains("registrationCode:" + code.trim() + ",")) {
                fileService.writeToFile(tempFilePath, student + ";");
            }
        }

        fileService.clearFile(PATH);
        String tempData = fileService.readFromFile(tempFilePath);
        fileService.writeToFile(PATH, tempData);
        fileService.removeFile(tempFilePath);
    }

    @Override
    public void updateStudent(IStudentsModel updatedStudent) {
        ManageCourse manegeCourse = new ManageCourseImpl(fileService);

        if (consultStudent(updatedStudent.getRegistrationCode()) == null) {
            System.out.println("Student not found!");
        } else if (manegeCourse.consultCourse(updatedStudent.getCourseCode()) == null) {
            System.out.println("Course not found!");
        } else {
            removeStudent(updatedStudent.getRegistrationCode());

            StringBuilder sb = new StringBuilder();
            sb.append("registrationCode:");
            sb.append(updatedStudent.getRegistrationCode().trim());
            sb.append(",name:");
            sb.append(updatedStudent.getName().trim());
            sb.append(",age:");
            sb.append(updatedStudent.getAge());
            sb.append(",courseCode:");
            sb.append(updatedStudent.getCourseCode());
            sb.append(";");

            fileService.writeToFile(PATH, sb.toString());
        }
    }
}
