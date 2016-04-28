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


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
        System.out.println("Bottle list by Price");
        this.printList(this.getSortedByPrice());
    }

    public void printBottleListByBrandName() {
        System.out.println("Bottle list by Name");
        this.printList(this.getSortedByBrand());
    }

    public void printBottleListByAlcoholStrength() {
        System.out.println("Bottle list by Alcohol Strength");
        this.printList(this.getSortedByAlcoholStrength());
    }

    public void printBottleListByAlcoholContent() {
        System.out.println("Bottle list by Alcohol Content");
        this.printList(this.getSortedByAlcoholContent());
    }

    public void printBottleListByBottleColor() {
        System.out.println("Bottle list by Bottle Color");
        this.printList(this.getSortedByBottleColor());
    }

    public LinkedList<Bottle> getSortedByPrice() {
        LinkedList<Bottle> result = new LinkedList<>(this.bottles);
        Collections.sort(result, (o1, o2) -> o1.getPrice().compareTo(o2.getPrice()));

        return result;
    }


    public LinkedList<Bottle> getSortedByBrand() {
        LinkedList<Bottle> result = new LinkedList<>(this.bottles);
        Collections.sort(result, (o1, o2) -> o1.getBeer().getBrandName().compareTo(o2.getBeer().getBrandName()));

        return result;
    }

    public LinkedList<Bottle> getSortedByAlcoholStrength() {
        LinkedList<Bottle> result = new LinkedList<>(this.bottles);
        Collections.sort(result, (o1, o2) -> o1.getBeer().getStrength().compareTo(o2.getBeer().getStrength()));

        return result;
    }

    public LinkedList<Bottle> getSortedByAlcoholContent() {
        LinkedList<Bottle> result = new LinkedList<>(this.bottles);
        Collections.sort(result, (o1, o2) -> o1.getAlcoholContent().compareTo(o2.getAlcoholContent()));

        return result;
    }

    public LinkedList<Bottle> getSortedByBottleColor() {
        LinkedList<Bottle> result = new LinkedList<>(this.bottles);
        Collections.sort(result, (o1, o2) -> o1.getGlassColour().compareTo(o2.getGlassColour()));

        return result;
    }

    private void printList(List<Bottle> list) {
        list.forEach(out::println);
    }

    public void addBottle(Bottle bottle) {
        this.bottles.add(bottle);
    }

}
