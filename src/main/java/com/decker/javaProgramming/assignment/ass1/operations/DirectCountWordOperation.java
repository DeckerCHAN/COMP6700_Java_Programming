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
import com.decker.javaProgramming.assignment.ass1.feture.CommonTableBuilder;
import com.decker.javaProgramming.assignment.ass1.feture.OptimisedTableBuilder;
import com.decker.javaProgramming.assignment.ass1.feture.TableBuilder;
import javafx.scene.control.Tab;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;

public final class DirectCountWordOperation extends CountWordOperation {
    public DirectCountWordOperation(Path file, Integer outputIndex) {
        super(file, outputIndex);
    }

    public DirectCountWordOperation(Collection<Path> files, Integer outputIndex) {
        super(files, outputIndex);
    }

    public DirectCountWordOperation(Path file) {
        super(file);
    }

    public DirectCountWordOperation(Collection<Path> files) {
        super(files);
    }

    @Override
    public void execute() {
        for (Path path : this.getFiles()) {
            try {
                TableBuilder builder = new CommonTableBuilder();
                WordFrequencyTable table = new WordFrequencyTable(path, builder);
                this.getTables().add(table);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.printResult();

    }

}
