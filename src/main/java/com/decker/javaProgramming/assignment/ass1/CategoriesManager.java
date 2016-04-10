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

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Properties;

public class CategoriesManager {

    private  Path propertiesPath;

    private HashMap<String, String> categories;

    public CategoriesManager(Path runningFolder) throws IOException {
            this.propertiesPath = runningFolder.resolve("ass1.txt");
        if(propertiesPath.toFile().exists())
        {
            this.loadCategoriesFromPropertiesFile();
        }else {
            this.searchFolderBuildCategories();
            this.saveCategoriesFromPropertiesFile();
        }
    }

    public HashMap<String, String> getCategories() {
        return categories;
    }

    private void setCategories(HashMap<String, String> categories) {
        this.categories = categories;
    }

    private boolean loadCategoriesFromPropertiesFile() {
        this.setCategories(new HashMap<>());
        Properties prop = new Properties();
        try {
            prop.load(new FileReader(propertiesPath.toFile()));
            for (final String name :
                    prop.stringPropertyNames()) {
                this.getCategories().put(name, prop.getProperty(name));
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
            prop.setProperty(key,this.getCategories().get(key));
        }
        try {
            prop.store(new FileWriter(propertiesPath.toFile()), null);
            return true;
        } catch (IOException e) {
            return false;
        }
    }


    private void searchFolderBuildCategories() {

    }


}
