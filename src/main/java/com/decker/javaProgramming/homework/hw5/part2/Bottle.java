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


public class Bottle {
    private Beer beer;
    private Color glassColour;
    private Double volume;
    private Integer quantity;
    private Double price;


    public Bottle(Beer beer, Color glassColour, Double volume, Integer quantity, Double price) {
        this.beer = beer;
        this.glassColour = glassColour;
        this.volume = volume;
        this.quantity = quantity;
        this.price = price;
    }

    public Double getAlcoholContent() {
        return this.getBeer().getStrength() * this.getVolume();
    }

    public Beer getBeer() {
        return beer;
    }


    public Double getVolume() {
        return volume;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("Beer name:%10s Bottle color:%6s Volume: %8f Price:%8f AlcoholContent:%5f", this.getBeer().getBrandName(), this.getGlassColour(), this.getVolume(), this.getPrice(), this.getAlcoholContent());
    }

    public Color getGlassColour() {
        return glassColour;
    }
}
