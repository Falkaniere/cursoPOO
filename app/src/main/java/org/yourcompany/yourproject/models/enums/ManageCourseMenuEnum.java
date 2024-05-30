package org.yourcompany.yourproject.models.enums;

public enum ManageCourseMenuEnum {
    CADASTRAR_CURSO(1),
    CONSULTAR_CURSO(2),
    REMOVER_CURSO(3),
    ATUALIZAR_CURSO(4),
    VOLTAR_AO_MENU_INICIAL(5);

    private final int value;

    ManageCourseMenuEnum(int value) {
        this.value = value;
    }

    public static ManageCourseMenuEnum fromValue(int value) {
        for (ManageCourseMenuEnum menu : ManageCourseMenuEnum.values()) {
            if (menu.value == value) {
                return menu;
            }
        }
        return null;
    }
}
