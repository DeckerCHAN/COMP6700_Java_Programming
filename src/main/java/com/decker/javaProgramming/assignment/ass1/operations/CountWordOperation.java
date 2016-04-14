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

package com.decker.javaProgramming.assignment.ass1.operations;

import com.decker.javaProgramming.assignment.ass1.WordFrequencyTable;
import com.decker.javaProgramming.assignment.ass1.utls.CollectionUtils;

import java.nio.file.Path;
import java.util.*;

import static java.lang.System.out;

public abstract class CountWordOperation implements Operation {

    private final static Integer DEFAULT_OUTPUT_INDEX = 50;
    private List<Path> files;
    private Integer outputIndex;
    private List<WordFrequencyTable> tables;

    private CountWordOperation(Integer outputIndex) {
        this.files = new LinkedList<>();
        this.tables = new LinkedList<>();
        this.outputIndex = outputIndex;
    }

    public CountWordOperation(Path file, Integer outputIndex) {
        this(outputIndex);
        files.add(file);
    }

    public CountWordOperation(Collection<Path> files, Integer outputIndex) {
        this(outputIndex);
        this.getFiles().addAll(files);
    }

    public CountWordOperation(Path file) {
        this(DEFAULT_OUTPUT_INDEX);
        files.add(file);
    }

    public CountWordOperation(Collection<Path> files) {
        this(DEFAULT_OUTPUT_INDEX);
        this.getFiles().addAll(files);
    }

    public List<Path> getFiles() {
        return files;
    }

    private void setFiles(List<Path> files) {
        this.files = files;
    }

    public List<WordFrequencyTable> getTables() {
        return tables;
    }

    protected void setTables(List<WordFrequencyTable> tables) {
        this.tables = tables;
    }

    public LinkedHashMap<String, Long> getCombinedTable() {
        HashMap<String, Long> result = new HashMap<>();
        for (WordFrequencyTable table : this.getTables()) {
            Map<String, Long> currentZipfTable =table.getZipfTable();
            for (String word : currentZipfTable.keySet()) {
                if (result.containsKey(word)) {
                    result.put(word, result.get(word) + currentZipfTable.get(word));
                } else {
                    result.put(word, currentZipfTable.get(word));
                }
            }
        }
        return CollectionUtils.sortByValue(result);
    }

    public void printResult() {
        out.println("The following files from the poetry category have been analysed:");
        for (Path file :
                this.getFiles()) {
            out.println(file.getFileName().toString());
        }
        out.println();
        out.println(String.format("The cumulative rank-frequency table (first %d entries) ", this.getOutputIndex()));
        LinkedHashMap<String, Long> result = this.getCombinedTable();
        Iterator<String> ite = result.keySet().iterator();
        for (int i = 0; i < this.getOutputIndex() && ite.hasNext(); i++) {
            String key = ite.next();
            out.println(String.format("%d (%s) %d", i + 1, key, result.get(key)));
        }
        out.println();
    }

    public Integer getOutputIndex() {
        return outputIndex;
    }

    private void setOutputIndex(Integer outputIndex) {
        this.outputIndex = outputIndex;
    }
}
