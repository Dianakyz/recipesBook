package com.example.recipesbook.service;

import org.springframework.stereotype.Service;

@Service
public interface FileService {

    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanDataFile();
}
