package org.yourcompany.yourproject.models.enums;

public enum ManageDisciplineMenuEnum {
    REGISTRAR_DISCIPLINA(1),
    CONSULTAR_DISCIPLINA(2),
    REMOVER_DISCIPLINA(3),
    ATUALIZAR_DISCIPLINA(4),
    VOLTAR_AO_MENU_INICIAL(5);

    private final int value;

    ManageDisciplineMenuEnum(int value) {
        this.value = value;
    }

    public static ManageDisciplineMenuEnum fromValue(int value) {
        for (ManageDisciplineMenuEnum menu : ManageDisciplineMenuEnum.values()) {
            if (menu.value == value) {
                return menu;
            }
        }
        return null;
    }
}
