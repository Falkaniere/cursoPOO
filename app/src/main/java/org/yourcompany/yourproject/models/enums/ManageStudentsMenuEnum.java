package org.yourcompany.yourproject.models.enums;

public enum ManageStudentsMenuEnum {
    CADASTRAR_ALUNO(1),
    CONSULTAR_ALUNO(2),
    REMOVER_ALUNO(3),
    ATUALIZAR_ALUNO(4),
    VOLTAR_AO_MENU_INICIAL(5);

    private final int value;

    ManageStudentsMenuEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ManageStudentsMenuEnum fromValue(int value) {
        for (ManageStudentsMenuEnum menuOption : ManageStudentsMenuEnum.values()) {
            if (menuOption.getValue() == value) {
                return menuOption;
            }
        }
        return null;
    }
}
