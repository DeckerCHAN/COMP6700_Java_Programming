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

public class BottleShopTest {

    public static void main(String[] args) {
        BottleShop bottleShop = new BottleShop();
        Beer snowBeer = new Beer("Snow Beer", 0.08);
        Beer tsingtao = new Beer("Tsingtao", 0.047);
        Beer budweiser = new Beer("Budweiser", 0.05);
        Beer yanjing = new Beer("Yanjing", 0.1);
        Beer budLight = new Beer("Bud Light", 0.042);

        bottleShop.addBottle(new Bottle(snowBeer, Color.Blue, 800D, 15, 2.4));
        bottleShop.addBottle(new Bottle(tsingtao, Color.Red, 700D, 17, 0.9));
        bottleShop.addBottle(new Bottle(tsingtao, Color.Red, 350D, 7, 0.9));
        bottleShop.addBottle(new Bottle(budweiser, Color.Blue, 350D, 3, 1.3));
        bottleShop.addBottle(new Bottle(yanjing, Color.Yellow, 500D, 19, 1.8));
        bottleShop.addBottle(new Bottle(budLight, Color.Orange, 500D, 21, 4.5));

        bottleShop.printBottleListByBrandName();
        bottleShop.printBottleListByPrice();
        bottleShop.printBottleListByAlcoholStrength();
        bottleShop.printBottleListByAlcoholContent();
        bottleShop.printBottleListByBottleColor();


    }
}
