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

package com.decker.javaProgramming.assignment.ass1.factories;

import com.decker.javaProgramming.assignment.ass1.entities.Category;
import com.decker.javaProgramming.assignment.ass1.utls.FileUtils;

import java.io.File;
import java.util.ArrayList;

public class CategoryFactory {
    public Category createCategory(String path) {
        if (!this.isValidCategoryFolder(path)) {
            throw new IllegalArgumentException("Provided path is not a valid path.");
        }

        Category category = new Category();
        category.setPath(path);
        ArrayList<File> literFiles = new ArrayList<File>();
        for (File file : new File(path).listFiles()) {
            if (FileUtils.getFileExtensionName(file.getPath()).equals("txt")) {
                literFiles.add(file);
            }

        }

        category.setLiteratureFiles(literFiles);
        return category;
    }

    private Boolean isValidCategoryFolder(String path) {
        File categoryFolder = new File(path);
        if (!categoryFolder.exists()) {
            return false;
        }

        File[] files = categoryFolder.listFiles();

        if (files == null) {
            return false;
        }
        for (File file :
                files) {
            if (FileUtils.getFileExtensionName(file.getName()).equals("txt")) {
                return true;
            }
        }
        return false;
    }
}
