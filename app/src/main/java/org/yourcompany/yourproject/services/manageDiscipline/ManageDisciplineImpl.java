package org.yourcompany.yourproject.services.manageDiscipline;

import org.yourcompany.yourproject.models.IDisciplineModel;
import org.yourcompany.yourproject.services.filesService.FileService;

public class ManageDisciplineImpl implements ManageDiscipline {

    public static final String PATH = "discipline.txt";

    private final FileService fileService;

    public ManageDisciplineImpl(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public void registerDiscipline(IDisciplineModel discipline) {
        StringBuilder sb = new StringBuilder();
        sb.append("id:");
        sb.append(discipline.getId().trim());
        sb.append(",name:");
        sb.append(discipline.getName().trim());
        sb.append(",workload:");
        sb.append(discipline.getWorkload());
        sb.append(",code:");
        sb.append(discipline.getCode().trim());
        sb.append(",rating:");
        sb.append(discipline.getRating());
        sb.append(";");

        fileService.writeToFile(PATH, sb.toString());
    }

    @Override
    public IDisciplineModel consultDiscipline(String code) {
        String data = fileService.readFromFile(PATH);
        String[] disciplines = data.split(";");

        for (String discipline : disciplines) {
            if (discipline.contains("code:" + code.trim() + ",")) {
                String[] disciplineData = discipline.split(",");
                String name = disciplineData[1].replace("name:", "");
                int workload = Integer.parseInt(disciplineData[2].replace("workload:", ""));
                String codee = disciplineData[3].replace("code:", "");
                double rating = Double.parseDouble(disciplineData[4].replace("rating:", ""));

                return new IDisciplineModel(name, workload, codee, rating);
            }
        }

        return null;
    }

    @Override
    public void removeDiscipline(String code) {
        String tempFilePath = "tempFile.txt";
        fileService.createFile(tempFilePath);

        String reader = fileService.readFromFile(PATH);
        String[] disciplines = reader.split(";");
        for (String discipline : disciplines) {
            if (!discipline.contains("code:" + code.trim() + ",")) {
                fileService.writeToFile(tempFilePath, discipline + ";");
            }
        }

        fileService.clearFile(PATH);
        String tempData = fileService.readFromFile(tempFilePath);
        fileService.writeToFile(PATH, tempData);
        fileService.removeFile(tempFilePath);
    }

    @Override
    public void updateDiscipline(IDisciplineModel updatedDiscipline) {
        removeDiscipline(updatedDiscipline.getCode());

        StringBuilder sb = new StringBuilder();
        sb.append("id:");
        sb.append(updatedDiscipline.getId().trim());
        sb.append(",name:");
        sb.append(updatedDiscipline.getName().trim());
        sb.append(",workload:");
        sb.append(updatedDiscipline.getWorkload());
        sb.append(",code:");
        sb.append(updatedDiscipline.getCode().trim());
        sb.append(",rating:");
        sb.append(updatedDiscipline.getRating());
        sb.append(";");

        fileService.writeToFile(PATH, sb.toString());
    }
}
