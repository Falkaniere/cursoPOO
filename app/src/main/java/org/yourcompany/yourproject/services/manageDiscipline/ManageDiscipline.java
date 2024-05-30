package org.yourcompany.yourproject.services.manageDiscipline;

import org.yourcompany.yourproject.models.IDisciplineModel;

public interface ManageDiscipline {

    void registerDiscipline(IDisciplineModel discipline);

    IDisciplineModel consultDiscipline(String code);

    void removeDiscipline(String code);

    void updateDiscipline(IDisciplineModel updatedDiscipline);
}
