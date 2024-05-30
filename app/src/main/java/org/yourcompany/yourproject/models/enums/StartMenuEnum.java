package org.yourcompany.yourproject.models.enums;

public enum StartMenuEnum {
    GERENCIAR_ALUNOS(1),
    GERENCIAR_DISCIPLINAS(2),
    GERENCIAR_CURSOS(3),
    SAIR(4);

    private final int value;

    StartMenuEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static StartMenuEnum fromValue(int value) {
        for (StartMenuEnum menuOption : StartMenuEnum.values()) {
            if (menuOption.getValue() == value) {
                return menuOption;
            }
        }
        return null;
    }

    // throw new IllegalArgumentException("Invalid value for StartMenuEnum: " + value);
}
