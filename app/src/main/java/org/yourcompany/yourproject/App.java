package org.yourcompany.yourproject;

import org.yourcompany.yourproject.models.enums.ManageCourseMenuEnum;
import org.yourcompany.yourproject.models.enums.ManageDisciplineMenuEnum;
import org.yourcompany.yourproject.models.enums.ManageStudentsMenuEnum;
import org.yourcompany.yourproject.models.enums.StartMenuEnum;
import org.yourcompany.yourproject.services.filesService.FileService;
import org.yourcompany.yourproject.services.filesService.FileServiceImpl;
import org.yourcompany.yourproject.services.manageCourse.ManageCourseImpl;
import org.yourcompany.yourproject.services.manageDiscipline.ManageDisciplineImpl;
import org.yourcompany.yourproject.services.manageStudents.ManageStudentsImpl;

public class App {

    Boolean finishSystem = false;

    public void getStart() {
        FileService fileService = new FileServiceImpl();

        while (finishSystem == false) {
            SystemApp systemApp = new SystemApp();
            createMockDbFile(fileService);

            onShowPrompt(fileService, systemApp);
        }
    }

    private void onShowPrompt(FileService fileService, SystemApp systemApp) {
        StartMenuEnum startMenuOption = systemApp.onStartMenu();

        // Validação para caso digite algum número diferente das opções
        if (startMenuOption == null) {
            System.out.println("Opção inválida. Tente novamente.");
            onShowPrompt(fileService, systemApp);
        } else {
            switch (startMenuOption) {
                case GERENCIAR_ALUNOS -> {
                    // Código para gerenciar alunos
                    onManageStudents(fileService, systemApp);
                }

                case GERENCIAR_CURSOS -> {
                    // Código para gerenciar cursos
                    onManegeCourse(fileService, systemApp);
                }

                case GERENCIAR_DISCIPLINAS -> {
                    // Código para gerenciar disciplinas
                    onManageDiscipline(fileService, systemApp);
                }

                case SAIR -> {
                    // Fechar o sistema
                    finishSystem = true;
                }

                default -> {
                }
            }
        }
    }

    private void onManageStudents(FileService fileService, SystemApp systemApp) {
        ManageStudentsMenuEnum menuOption = systemApp.onManageStudentsMenu(fileService);

        if (menuOption == null) {
            System.out.println("Opção inválida. Tente novamente.");
            onManageStudents(fileService, systemApp);
        } else {
            switch (menuOption) {
                case CADASTRAR_ALUNO -> {
                    // Cadastrar aluno
                    systemApp.registerStudent();
                }

                case CONSULTAR_ALUNO -> {
                    // Consultar aluno
                    systemApp.consultStudent(fileService);
                }

                case REMOVER_ALUNO -> {
                    // Remover aluno
                    systemApp.removeStudent();
                }

                case ATUALIZAR_ALUNO -> {
                    // Atualizar aluno
                    systemApp.updateStudent();
                }

                case VOLTAR_AO_MENU_INICIAL -> {
                    // Voltar ao menu inicial
                    onShowPrompt(fileService, systemApp);
                }

                default -> {
                }
            }
        }

    }

    private void onManegeCourse(FileService fileService, SystemApp systemApp) {
        ManageCourseMenuEnum menuOption = systemApp.onManageCourseMenu(fileService);

        if (menuOption == null) {
            System.out.println("Opção inválida. Tente novamente.");
            onManegeCourse(fileService, systemApp);
        } else {
            switch (menuOption) {
                case CADASTRAR_CURSO -> {
                    // Cadastrar curso
                    systemApp.registerCourse();
                }

                case CONSULTAR_CURSO -> {
                    // Consultar curso
                    systemApp.consultCourse();
                }

                case REMOVER_CURSO -> {
                    // Remover curso
                    systemApp.removeCourse();
                }

                case ATUALIZAR_CURSO -> {
                    // Atualizar curso
                    systemApp.updateCourse();
                }

                case VOLTAR_AO_MENU_INICIAL -> {
                    // Voltar ao menu inicial
                    onShowPrompt(fileService, systemApp);
                }

                default -> {
                }
            }
        }
    }

    private void onManageDiscipline(FileService fileService, SystemApp systemApp) {
        ManageDisciplineMenuEnum menuOption = systemApp.onManageDisciplineMenu(fileService);

        if (menuOption == null) {
            System.out.println("Opção inválida. Tente novamente.");
            onManageDiscipline(fileService, systemApp);
        } else {
            switch (menuOption) {
                case REGISTRAR_DISCIPLINA -> {
                    // Cadastrar disciplina
                    systemApp.registerDiscipline();
                }

                case CONSULTAR_DISCIPLINA -> {
                    // Consultar disciplina
                    systemApp.consultDiscipline();
                }

                case REMOVER_DISCIPLINA -> {
                    // Remover disciplina
                    systemApp.removeDiscipline();
                }

                case ATUALIZAR_DISCIPLINA -> {
                    // Atualizar disciplina
                    systemApp.updateDiscipline();
                }

                case VOLTAR_AO_MENU_INICIAL -> {
                    // Voltar ao menu inicial
                    onShowPrompt(fileService, systemApp);
                }

                default -> {
                }
            }
        }
    }

    private void createMockDbFile(FileService fileService) {
        fileService.createFile(ManageDisciplineImpl.PATH);
        fileService.createFile(ManageCourseImpl.PATH);
        fileService.createFile(ManageStudentsImpl.PATH);
    }

    public static void main(String[] args) {
        new App().getStart();
    }
}
