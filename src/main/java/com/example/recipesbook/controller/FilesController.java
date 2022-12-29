package com.example.recipesbook.controller;

import com.example.recipesbook.service.FileServiceIngredientImpl;
import com.example.recipesbook.service.FileServiceRecipeImpl;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/files")
public class FilesController {

    private final FileServiceIngredientImpl fileServiceIngredient;
    private final FileServiceRecipeImpl fileServiceRecipe;

    public FilesController(FileServiceIngredientImpl fileServiceIngredient, FileServiceRecipeImpl fileServiceRecipe) {
        this.fileServiceIngredient = fileServiceIngredient;
        this.fileServiceRecipe = fileServiceRecipe;
    }

    @GetMapping("/export/recipes")
    public ResponseEntity<InputStreamResource> downloadDataFileRecipe() throws FileNotFoundException{
        File fileRecipe = fileServiceRecipe.getDataFile();
        if(fileRecipe.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(fileRecipe));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(fileRecipe.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"RecipeLog.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/import/recipes", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadDataFileRecipe(@RequestParam MultipartFile file) {
        fileServiceRecipe.cleanDataFile();
        File dataFileRecipe = fileServiceRecipe.getDataFile();

        try (FileOutputStream fos = new FileOutputStream(dataFileRecipe)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
    }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping(value = "/import/ingredients", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadDataFileIngredient(@RequestParam MultipartFile file) {
        fileServiceIngredient.cleanDataFile();
        File dataFileIngredient = fileServiceIngredient.getDataFile();

        try (FileOutputStream fos = new FileOutputStream(dataFileIngredient)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
