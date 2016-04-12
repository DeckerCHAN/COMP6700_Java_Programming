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

import com.decker.javaProgramming.assignment.ass1.feture.TextProcessor;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

public final class WordFrequencyTable {
    private long buildTime;
    private TextProcessor processor;
    private Map<String, Long> zipfTable;

    public WordFrequencyTable(Path textPath, TextProcessor processor) {
        this.processor = processor;
        this.zipfTable = buildTable(textPath);
    }

    private Map<String, Long> buildTable(Path file) {
        long start = System.currentTimeMillis();
        this.processor.load(file);
        this.zipfTable = this.processor.getSortedMap();
        long finish = System.currentTimeMillis();
        this.setBuildTime(finish - start);

        return null;

    }

    public long getBuildTime() {
        return buildTime;
    }

    public void printTable() {

    }

    private void setBuildTime(long buildTime) {
        this.buildTime = buildTime;
    }

    public WordFrequencyTable combine(WordFrequencyTable table) {
        throw new NotImplementedException();
    }
}
