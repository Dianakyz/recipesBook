package com.example.recipesbook.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileServiceRecipeImpl implements FileService{

    @Value("${path.to.data.file}")
    private String dataFilePath;

    @Value("${name1.of.data.file}")
    private String dataFileNameRecipe;

    public boolean saveToFile(String json){
        try {
            cleanDataFile();
            Files.writeString(Path.of(dataFilePath, dataFileNameRecipe), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String readFromFile() {
        try {
            return Files.readString(Path.of(dataFilePath, dataFileNameRecipe));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public File getDataFile() {
        return new File(dataFilePath + "/" + dataFileNameRecipe);
    }

    public boolean cleanDataFile() {
        try {
            Path path = Path.of(dataFilePath, dataFileNameRecipe);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
