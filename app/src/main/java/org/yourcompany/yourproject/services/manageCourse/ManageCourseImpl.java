package org.yourcompany.yourproject.services.manageCourse;

import org.yourcompany.yourproject.models.ICourseModel;
import org.yourcompany.yourproject.models.IDisciplineModel;
import org.yourcompany.yourproject.services.filesService.FileService;
import org.yourcompany.yourproject.services.manageDiscipline.ManageDiscipline;
import org.yourcompany.yourproject.services.manageDiscipline.ManageDisciplineImpl;

public class ManageCourseImpl implements ManageCourse {

    public static final String PATH = "course.txt";

    private final FileService fileService;

    public ManageCourseImpl(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public void registerCourse(ICourseModel course) {
        ManageDiscipline disciplineManage = new ManageDisciplineImpl(fileService);
        IDisciplineModel discipline = disciplineManage.consultDiscipline(course.getDisciplineCode());

        if (discipline == null) {
            System.out.println("Disciplina não encontrada!");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("id:");
            sb.append(course.getId().trim());
            sb.append(",name:");
            sb.append(course.getName().trim());
            sb.append(",code:");
            sb.append(course.getCode().trim());
            sb.append(",courseShift:");
            sb.append(course.getCourseShift().trim());
            sb.append(",disciplineCode:");
            sb.append(course.getDisciplineCode());
            sb.append(";");

            fileService.writeToFile(PATH, sb.toString());
        }
    }

    @Override
    public ICourseModel consultCourse(String code) {
        String course = fileService.readFromFile(PATH);
        String[] courses = course.split(";");
        for (String c : courses) {
            String[] courseData = c.split(",");
            for (String data : courseData) {
                if (data.contains(code)) {
                    String[] courseInfo = c.split(",");
                    String name = courseInfo[1].replace("name:", "");
                    String courseCode = courseInfo[2].replace("code:", "");
                    String courseShift = courseInfo[3].replace("courseShift:", "");
                    String disciplineCode = courseInfo[4].replace("disciplineCode:", "");
                    ICourseModel newCourse = new ICourseModel(name, courseCode, courseShift, disciplineCode);
                    newCourse.setDiscipline(getDisciplineWithCode(disciplineCode));

                    return newCourse;
                }
            }
        }

        return null;
    }

    private IDisciplineModel getDisciplineWithCode(String disciplineCode) {
        ManageDiscipline disciplineManage = new ManageDisciplineImpl(fileService);
        return disciplineManage.consultDiscipline(disciplineCode);
    }

    @Override
    public void removeCourse(String code) {
        String tempFilePath = "tempFile.txt";
        fileService.createFile(tempFilePath);

        String reader = fileService.readFromFile(PATH);
        String[] courses = reader.split(";");
        for (String course : courses) {
            if (!course.contains("code:" + code.trim() + ",")) {
                fileService.writeToFile(tempFilePath, course + ";");
            }
        }

        fileService.clearFile(PATH);
        String tempData = fileService.readFromFile(tempFilePath);
        fileService.writeToFile(PATH, tempData);
        fileService.removeFile(tempFilePath);
    }

    @Override
    public void updateCourse(ICourseModel updatedCourse) {
        ICourseModel course = consultCourse(updatedCourse.getCode());
        if (course == null) {
            System.out.println("Curso não encontrado!");
        } else {
            if (course.getDisciplineCode().equals(updatedCourse.getDisciplineCode())) {
                System.out.println("Disciplina não encontrada!");
            } else {
                removeCourse(updatedCourse.getCode());

                StringBuilder sb = new StringBuilder();
                sb.append("id:");
                sb.append(updatedCourse.getId().trim());
                sb.append(",name:");
                sb.append(updatedCourse.getName().trim());
                sb.append(",code:");
                sb.append(updatedCourse.getCode().trim());
                sb.append(",courseShift:");
                sb.append(updatedCourse.getCourseShift().trim());
                sb.append(",disciplineCode:");
                sb.append(updatedCourse.getDisciplineCode());
                sb.append(";");

                fileService.writeToFile(PATH, sb.toString());
            }
        }
    }
}
