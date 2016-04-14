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

import com.decker.javaProgramming.assignment.ass1.feture.TableBuilder;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public final class WordFrequencyTable {


    private void setBuilder(TableBuilder builder) {
        this.builder = builder;
    }

    private TableBuilder builder;

    public Map<String, Long> getZipfTable() {
        return zipfTable;
    }

    private void setZipfTable(Map<String, Long> zipfTable) {
        this.zipfTable = zipfTable;
    }

    private Map<String, Long> zipfTable;

    public WordFrequencyTable(Path textPath, TableBuilder builder) throws IOException {
        this.builder = builder;
        buildTable(textPath);
    }

    private void buildTable(Path file) throws IOException {
        this.builder.load(file);
        this.zipfTable = this.builder.getMap();


    }
}
