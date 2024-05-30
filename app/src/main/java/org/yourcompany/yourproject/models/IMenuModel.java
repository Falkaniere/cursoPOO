package org.yourcompany.yourproject.models;

import org.yourcompany.yourproject.models.enums.ManageCourseMenuEnum;
import org.yourcompany.yourproject.models.enums.ManageDisciplineMenuEnum;
import org.yourcompany.yourproject.models.enums.ManageStudentsMenuEnum;
import org.yourcompany.yourproject.models.enums.StartMenuEnum;
import org.yourcompany.yourproject.services.filesService.FileService;

public interface IMenuModel {

    StartMenuEnum onStartMenu();

    ManageStudentsMenuEnum onManageStudentsMenu(FileService fileService);

    ManageCourseMenuEnum onManageCourseMenu(FileService fileService);

    ManageDisciplineMenuEnum onManageDisciplineMenu(FileService fileService);
}
