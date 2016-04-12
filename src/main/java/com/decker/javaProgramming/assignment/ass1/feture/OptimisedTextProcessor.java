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

package com.decker.javaProgramming.assignment.ass1.feture;


import com.decker.javaProgramming.assignment.ass1.utls.CollectionUtils;
import com.decker.javaProgramming.assignment.ass1.utls.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class OptimisedTextProcessor implements TextProcessor {

    private HashMap<String, Long> map;

    public OptimisedTextProcessor() {
        this.map = new HashMap<>();
    }

    @Override
    public void load(Path file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file.toFile()))) {
            for (String line; (line = br.readLine()) != null; ) {
                String normalLine = StringUtils.stringNormalize(line);
                for (String word : normalLine.split("\\s+")) {
                    word = word.intern();
                    if (map.containsKey(word)) {
                        map.put(word, map.get(word) + 1);
                    } else {
                        map.put(word, 1L);
                    }
                }
            }
        }
    }

    @Override
    public LinkedHashMap<String, Long> getSortedMap() {
        return CollectionUtils.sortByValue(this.map);
    }
}
