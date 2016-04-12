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

import com.decker.javaProgramming.assignment.ass1.cli.Argument;
import com.decker.javaProgramming.assignment.ass1.cli.Cli;
import com.decker.javaProgramming.assignment.ass1.operations.ListingOperation;
import com.decker.javaProgramming.assignment.ass1.operations.Operation;

import java.nio.file.Paths;

import static java.lang.System.getProperty;
import static java.lang.System.out;

public class Zipfung {
    public static void main(String[] args) throws Exception {

        long startTime = System.currentTimeMillis();
        Cli cli = new Cli(args);
        CategoriesManager categoriesManager = new CategoriesManager(Paths.get(getProperty("user.dir")));

        if (cli.hasArgument("l")) {
            Operation listOperation =new ListingOperation(categoriesManager);
            listOperation.execute();
        } else if (cli.hasArgument("c")) {
            Argument arg = cli.getArgument("c");
            if (cli.hasArgument("o")) {

            } else {

            }

        } else if (cli.hasArgument("h")) {
            //TODO: print help
        } else {

        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.printf("Totally %d(ms) used.%n", totalTime);

    }

}
