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
import com.decker.javaProgramming.assignment.ass1.feture.CommonTableBuilder;
import com.decker.javaProgramming.assignment.ass1.operations.DirectCountWordOperation;
import com.decker.javaProgramming.assignment.ass1.operations.ListingOperation;
import com.decker.javaProgramming.assignment.ass1.operations.Operation;
import com.decker.javaProgramming.assignment.ass1.operations.OptimisedCountWordOperation;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.nio.file.Paths;

import static java.lang.System.getProperty;
import static java.lang.System.out;

public final class Zipfung {
    public static void main(String[] args) throws Exception {


        Cli cli = new Cli(args);
        CategoriesManager categoriesManager = new CategoriesManager(Paths.get(getProperty("user.dir")));
        Operation operation = null;
        if (cli.hasArgument("l")) {
            operation = new ListingOperation(categoriesManager);
        } else if (cli.hasArgument("c")) {
            Argument arg = cli.getArgument("c");


            if (cli.hasArgument("o")) {
                if (arg.getValuesCount() == 1) {
                    operation = new OptimisedCountWordOperation(categoriesManager.getCategories().get(arg.getValues().get(0)).getLiteratureFiles());
                } else if (arg.getValuesCount() == 2) {
                    operation = new OptimisedCountWordOperation(categoriesManager.getCategories().get(arg.getValues().get(0)).getLiteratureFiles(), Integer.valueOf(arg.getValues().get(1)));

                }
            } else {
                if (arg.getValuesCount() == 1) {
                    operation = new DirectCountWordOperation(categoriesManager.getCategories().get(arg.getValues().get(0)).getLiteratureFiles());
                } else if (arg.getValuesCount() == 2) {
                    operation = new DirectCountWordOperation(categoriesManager.getCategories().get(arg.getValues().get(0)).getLiteratureFiles(), Integer.valueOf(arg.getValues().get(1)));

                }

            }

        } else if (cli.hasArgument("h")) {
            //TODO: print help
        } else {

        }

        long startTime = System.currentTimeMillis();
        if (operation != null) {
            operation.execute();
        }
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.printf("Totally %d(ms) used.%n", totalTime);

    }

}
