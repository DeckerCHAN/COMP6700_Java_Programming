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

package com.decker.javaProgramming.assignment.ass1.cli;


import java.util.ArrayList;

public class Cli {
    Argument keyLessArgument;
    ArrayList<Argument> arguments;
    private String[] args;

    public Cli(String[] args) {
        this.args = args;
        this.keyLessArgument = new Argument("");
        this.arguments = new ArrayList<Argument>();
        this.resolveArguments();
    }

    public Argument getKeyLessArgument() {
        return keyLessArgument;
    }

    public Argument getArgument(String key) {
        for (Argument arg :
                this.arguments) {
            if (arg.getArgumentKey().equals(key)) {
                return arg;
            }
        }
        return null;
    }

    public Boolean hasArgument(String key) {
        for (Argument arg :
                this.arguments) {
            if (arg.getArgumentKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    private void resolveArguments() throws IllegalArgumentException {

        for (String arg : this.args) {
            if (arg.startsWith("-")) {
                this.arguments.add(new Argument(arg.substring(1)));
            } else {
                if (this.arguments.size() == 0) {
                    this.keyLessArgument.getValues().add(arg);
                } else {
                    arguments.get(arguments.size() - 1).getValues().add(arg);
                }
            }
        }
    }


}
