package org.yourcompany.yourproject;

import org.yourcompany.yourproject.models.CourseModel;
import org.yourcompany.yourproject.models.DisciplineModel;
import org.yourcompany.yourproject.models.ICourseModel;
import org.yourcompany.yourproject.models.IDisciplineModel;
import org.yourcompany.yourproject.models.IMenuModel;
import org.yourcompany.yourproject.models.IStudentsModel;
import org.yourcompany.yourproject.models.StudentModel;
import org.yourcompany.yourproject.models.enums.ManageCourseMenuEnum;
import org.yourcompany.yourproject.models.enums.ManageDisciplineMenuEnum;
import org.yourcompany.yourproject.models.enums.ManageStudentsMenuEnum;
import org.yourcompany.yourproject.models.enums.StartMenuEnum;
import org.yourcompany.yourproject.services.filesService.FileService;
import org.yourcompany.yourproject.services.manageCourse.ManageCourse;
import org.yourcompany.yourproject.services.manageCourse.ManageCourseImpl;
import org.yourcompany.yourproject.services.manageDiscipline.ManageDiscipline;
import org.yourcompany.yourproject.services.manageDiscipline.ManageDisciplineImpl;
import org.yourcompany.yourproject.services.manageStudents.ManageStudents;
import org.yourcompany.yourproject.services.manageStudents.ManageStudentsImpl;
import org.yourcompany.yourproject.services.scannerService.ScannerService;
import org.yourcompany.yourproject.services.scannerService.ScannerServiceImpl;

class SystemApp implements IMenuModel, DisciplineModel, CourseModel, StudentModel {

    private final ScannerService scannerService;
    private ManageCourse manageCourse;
    private ManageDiscipline manageDiscipline;
    private ManageStudents manageStudents;

    public SystemApp() {
        this.scannerService = new ScannerServiceImpl();
    }

    @Override
    public StartMenuEnum onStartMenu() {
        System.out.println("1-Gerenciar ALUNOS");
        System.out.println("2-Gerenciar DISCIPLINAS");
        System.out.println("3-Gerenciar CURSOS");
        System.out.println("4-SAIR");

        String option = scannerService.inputScanner("Escolha uma opção: ");

        return StartMenuEnum.fromValue(Integer.parseInt(option));
    }

    @Override
    public ManageStudentsMenuEnum onManageStudentsMenu(FileService fileService) {
        manageStudents = new ManageStudentsImpl(fileService);

        System.out.println("1-Cadastrar ALUNO");
        System.out.println("2-Consultar ALUNO");
        System.out.println("3-Remover ALUNO");
        System.out.println("4-Atualizar ALUNO");
        System.out.println("5-Voltar ao MENU INICIAL");

        String option = scannerService.inputScanner("Escolha uma opção: ");

        return ManageStudentsMenuEnum.fromValue(Integer.parseInt(option));
    }

    @Override
    public ManageCourseMenuEnum onManageCourseMenu(FileService fileService) {
        manageCourse = new ManageCourseImpl(fileService);

        System.out.println("1-Cadastrar CURSO");
        System.out.println("2-Consultar CURSO");
        System.out.println("3-Remover CURSO");
        System.out.println("4-Atualizar CURSO");
        System.out.println("5-Voltar ao MENU INICIAL");

        String option = scannerService.inputScanner("Escolha uma opção: ");

        return ManageCourseMenuEnum.fromValue(Integer.parseInt(option));
    }

    @Override
    public ManageDisciplineMenuEnum onManageDisciplineMenu(FileService fileService) {
        manageDiscipline = new ManageDisciplineImpl(fileService);

        System.out.println("1-Cadastrar DISCIPLINA");
        System.out.println("2-Consultar DISCIPLINA");
        System.out.println("3-Remover DISCIPLINA");
        System.out.println("4-Atualizar DISCIPLINA");
        System.out.println("5-Voltar ao MENU INICIAL");

        String option = scannerService.inputScanner("Escolha uma opção: ");

        return ManageDisciplineMenuEnum.fromValue(Integer.parseInt(option));
    }

    @Override
    public void registerDiscipline() {
        System.out.println("Cadastrar DISCIPLINA");
        String name = scannerService.inputScanner("Nome da disciplina: ");
        String code = scannerService.inputScanner("Código da disciplina: ");
        String workload = scannerService.inputScanner("Carga horária da disciplina: ");
        String rating = scannerService.inputScanner("Nota da disciplina: ");

        manageDiscipline.registerDiscipline(new IDisciplineModel(
                name,
                Integer.parseInt(workload),
                code,
                Double.parseDouble(rating)
        ));

        System.out.println("Disciplina cadastrada com sucesso!");
    }

    @Override
    public void consultDiscipline() {
        System.out.println("Consultar DISCIPLINA");
        String code = scannerService.inputScanner("Código da disciplina: ");

        IDisciplineModel discipline = manageDiscipline.consultDiscipline(code);

        if (discipline != null) {
            System.out.println("DISCIPLINA: " + discipline.getName());
            System.out.println("Nome: " + discipline.getName());
            System.out.println("Código: " + discipline.getCode());
            System.out.println("Carga horária: " + discipline.getWorkload());
            System.out.println("Nota: " + discipline.getRating());
        } else {
            System.out.println("Disciplina não encontrada!");
        }
    }

    @Override
    public void removeDiscipline() {
        System.out.println("Remover DISCIPLINA");
        String code = scannerService.inputScanner("Código da disciplina: ");

        manageDiscipline.removeDiscipline(code);

        System.out.println("Disciplina removida com sucesso!");
    }

    @Override
    public void updateDiscipline() {
        System.out.println("Atualizar DISCIPLINA");
        String code = scannerService.inputScanner("Código da disciplina: ");
        String name = scannerService.inputScanner("Nome da disciplina: ");
        String workload = scannerService.inputScanner("Carga horária da disciplina: ");
        String rating = scannerService.inputScanner("Nota da disciplina: ");

        manageDiscipline.updateDiscipline(new IDisciplineModel(
                name,
                Integer.parseInt(workload),
                code,
                Double.parseDouble(rating)
        ));

        System.out.println("Disciplina atualizada com sucesso!");
    }

    @Override
    public void registerCourse() {
        System.out.println("Cadastrar CURSO");
        String name = scannerService.inputScanner("Nome do curso: ");
        String code = scannerService.inputScanner("Código do curso: ");
        String courseShift = scannerService.inputScanner("Turno do curso: ");
        String disciplineCode = scannerService.inputScanner("Código da disciplina: ");

        manageCourse.registerCourse(new ICourseModel(
                name,
                code,
                courseShift,
                disciplineCode
        ));

        System.out.println("Curso cadastrado com sucesso!");
    }

    @Override
    public void consultCourse() {

        System.out.println("Consultar CURSO");
        String code = scannerService.inputScanner("Código do curso: ");

        ICourseModel course = manageCourse.consultCourse(code);

        if (course != null) {
            System.out.println("CURSO: " + course.getName());
            System.out.println("Nome: " + course.getName());
            System.out.println("Código: " + course.getCode());
            System.out.println("Turno: " + course.getCourseShift());
            System.out.println("Código da disciplina: " + course.getDisciplineCode());
            System.out.println("DISCIPLINA: " + course.getDiscipline().getName());
        } else {
            System.out.println("Curso não encontrado!");
        }

    }

    @Override
    public void removeCourse() {
        System.out.println("Remover CURSO");
        String code = scannerService.inputScanner("Código do curso: ");

        manageCourse.removeCourse(code);

        System.out.println("Curso removido com sucesso!");
    }

    @Override
    public void updateCourse() {

        System.out.println("Atualizar CURSO");
        String code = scannerService.inputScanner("Código do curso: ");
        String name = scannerService.inputScanner("Nome do curso: ");
        String courseShift = scannerService.inputScanner("Turno do curso: ");
        String disciplineCode = scannerService.inputScanner("Código da disciplina: ");

        manageCourse.updateCourse(new ICourseModel(
                name,
                code,
                courseShift,
                disciplineCode
        ));

        System.out.println("Curso atualizado com sucesso!");
    }

    @Override
    public void registerStudent() {
        System.out.println("Cadastrar ALUNO");
        String id = scannerService.inputScanner("Matrícula do aluno: ");
        String name = scannerService.inputScanner("Nome do aluno: ");
        String age = scannerService.inputScanner("Idade do aluno: ");
        String courseCode = scannerService.inputScanner("Código do curso: ");

        manageStudents.registerStudent(new IStudentsModel(
                id,
                name,
                Integer.parseInt(age),
                courseCode
        ));

        System.out.println("Aluno cadastrado com sucesso!");
    }

    @Override
    public void consultStudent(FileService fileService) {
        System.out.println("Consultar ALUNO");
        String code = scannerService.inputScanner("Matrícula do aluno: ");

        IStudentsModel student = manageStudents.consultStudent(code);

        if (student != null) {
            System.out.println("ALUNO: " + student.getName() + " - " + student.getRegistrationCode());
            System.out.println("Nome: " + student.getName());
            System.out.println("Matrícula: " + student.getRegistrationCode());
            System.out.println("Idade: " + student.getAge());
            System.out.println("Código do curso: " + student.getCourseCode());

            ManageCourse course = new ManageCourseImpl(fileService);
            System.out.println("CURSO: " + course.consultCourse(student.getCourseCode()).getName());
        } else {
            System.out.println("Aluno não encontrado!");
        }
    }

    @Override
    public void removeStudent() {
        System.out.println("Remover ALUNO");
        String code = scannerService.inputScanner("Matrícula do aluno: ");

        manageStudents.removeStudent(code);

        System.out.println("Aluno removido com sucesso!");
    }

    @Override
    public void updateStudent() {
        System.out.println("Atualizar ALUNO");
        String id = scannerService.inputScanner("Matrícula do aluno: ");
        String name = scannerService.inputScanner("Nome do aluno: ");
        String age = scannerService.inputScanner("Idade do aluno: ");
        String courseCode = scannerService.inputScanner("Código do curso: ");

        manageStudents.updateStudent(new IStudentsModel(
                id,
                name,
                Integer.parseInt(age),
                courseCode
        ));

        System.out.println("Aluno atualizado com sucesso!");
    }
}
