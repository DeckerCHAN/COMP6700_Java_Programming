/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Derek.CHAN
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.decker.javaProgramming.assignment.ass1;

import com.decker.javaProgramming.assignment.ass1.entities.Category;
import com.decker.javaProgramming.assignment.ass1.utls.FileUtils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Properties;

import static java.lang.System.out;

public class CategoriesManager {

    private Path propertiesPath;

    private HashMap<String, Category> categories;

    private Path runningFolder;

    public CategoriesManager(Path runningFolder) throws IOException {
        this.propertiesPath = runningFolder.resolve("ass1.properties");
        this.setRunningFolder(runningFolder);
        this.setCategories(new HashMap<>());
        if (propertiesPath.toFile().exists()) {
            this.loadCategoriesFromPropertiesFile();
        } else {
            out.println("Can not find ass1.properties. Search folder and generate this file instead.");
            this.searchFolderBuildCategories(runningFolder);
            this.saveCategoriesFromPropertiesFile();
        }
    }

    public HashMap<String, Category> getCategories() {
        return categories;
    }

    private void setCategories(HashMap<String, Category> categories) {
        this.categories = categories;
    }

    private boolean loadCategoriesFromPropertiesFile() {
        this.setCategories(new HashMap<>());
        Properties prop = new Properties();
        try {
            prop.load(new FileReader(propertiesPath.toFile()));
            for (final String name :
                    prop.stringPropertyNames()) {
                Category category = new Category();
                category.setName(name);
                category.setLiteratureFiles(new LinkedList<>());
                category.setPath(Paths.get(prop.getProperty(name).replace(".", System.getProperty("file.separator"))));
                this.getCategories().put(name, category);
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private boolean saveCategoriesFromPropertiesFile() {
        Properties prop = new Properties();
        for (String key :
                this.getCategories().keySet()) {
            prop.setProperty(key, this.getCategories().get(key).getPath().toString().replace(System.getProperty("file.separator"), "."));
        }
        try {
            prop.store(new FileWriter(propertiesPath.toFile()), null);
            return true;
        } catch (IOException e) {
            return false;
        }
    }


    private void searchFolderBuildCategories(Path folder) throws IOException {
        try (DirectoryStream<Path> dir = Files.newDirectoryStream(folder, file -> (Files.isDirectory(file)))) {
            for (Path subDir : dir) {
                this.searchFolderBuildCategories(subDir);
            }
        }

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(folder, file -> (FileUtils.getFileExtensionName(file.getFileName()).equals("txt")))) {


            Category category = new Category();
            category.setName(folder.getFileName().toString());
            category.setPath(getRunningFolder().relativize(folder));
            category.setLiteratureFiles(new ArrayList<>());
            for (Path file : stream) {
                category.getLiteratureFiles().add(getRunningFolder().relativize(file));
            }
            if (category.getLiteratureFiles().size() > 0) {
                this.getCategories().put(category.getName(), category);
            }
        }
    }


    private Path getRunningFolder() {
        return this.runningFolder;
    }

    private void setRunningFolder(Path runningFolder) {
        this.runningFolder = runningFolder;
    }
}
