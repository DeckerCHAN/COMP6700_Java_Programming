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

package com.decker.javaProgramming.homework.hw5.part2;

import java.util.*;

import static java.lang.System.out;

public class BottleShop {
    ArrayList<Bottle> bottles;

    public BottleShop() {
        this.bottles = new ArrayList<>();
    }

    public Integer bottleCount(Beer brand) {
        Integer result = 0;
        for (Bottle bottle :
                this.bottles) {
            if (bottle.getBeer().equals(brand)) {
                result++;
            }
        }
        return result;
    }

    public Double priceOfWholeStock() {
        Double result = 0D;
        for (Bottle bottle :
                this.bottles) {
            result += bottle.getPrice();
        }
        return result;
    }

    public void printBottleListByPrice() {
        this.printList(this.getSortedByPrice());
    }

    public void printBottleListByBrandName() {
        this.printList(this.getSortedByBrand());
    }

    public LinkedList<Bottle> getSortedByPrice() {
        LinkedList<Bottle> result = new LinkedList<>(this.bottles);
        Collections.sort(result, (o1, o2) -> {
            if (o1.getPrice() > o2.getPrice()) {
                return 1;
            } else {
                return 2;
            }
        });

        return result;
    }


    public LinkedList<Bottle> getSortedByBrand() {
        LinkedList<Bottle> result = new LinkedList<>(this.bottles);
        Collections.sort(result, (o1, o2) -> o1.getBeer().getBrandName().compareTo(o2.getBeer().getBrandName()));

        return result;
    }

    private void printList(List<Bottle> list) {
        list.forEach(out::println);
    }

    private void randomBottles(Integer number) {
        for (int i = 0; i < number; i++) {
            Bottle bottle = new Bottle(new Beer(this.getRandomWord(5),new Random().nextDouble()),new Color());
        }
    }

    private String getRandomWord(int length) {
        String r = "";
        for (int i = 0; i < length; i++) {
            r += (char) (Math.random() * 26 + 97);
        }
        return r;
    }

}
