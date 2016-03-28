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

package com.decker.javaProgramming.exam.mid;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class PasswordGenerator {
    private final static char[] SPECIAL_CHARS = "#$%&’()*+,-./:;<=>?@[]ˆ_‘{|}˜}".toCharArray();
    private final static char[] NUMBER_CHARS = "0123456789".toCharArray();
    private final static char[] LETTER_CHARS = "qwertyuiopasdfghjklzxcvbnm".toCharArray();

    public static void main(String[] args) {
        Random random = new Random();
        int passwordLength, specialCharacterLength, numberLength;
        char[] password;
        LinkedList<Character> passwordList;
        Scanner scanner = new Scanner(System.in);
        System.out.print("What’s the minimum length?");
        passwordLength = scanner.nextInt();
        passwordLength += random.nextInt(passwordLength);
        System.out.print("How many special characters?");
        specialCharacterLength = scanner.nextInt();
        System.out.print("How many numbers?");
        numberLength = scanner.nextInt();

        passwordList = new LinkedList<Character>();

        for (int i = 0; i < specialCharacterLength; i++) {
            passwordList.add(SPECIAL_CHARS[random.nextInt(SPECIAL_CHARS.length - 1)]);
        }

        for (int i = 0; i < numberLength; i++) {
            passwordList.add(NUMBER_CHARS[random.nextInt(NUMBER_CHARS.length - 1)]);
        }

        for (int i = 0; i < passwordLength - numberLength - specialCharacterLength; i++) {
            passwordList.add(LETTER_CHARS[random.nextInt(LETTER_CHARS.length - 1)]);
        }

        password = new char[passwordLength];

        for (int i = 0; passwordList.size() > 0; i++) {
            if(passwordList.size()==1)
            {
                password[i] = passwordList.get(0);
                break;
            }
            int index = random.nextInt(passwordList.size() - 1);
            password[i] = passwordList.get(index);
            passwordList.remove(index);
        }

        System.out.printf("Your password is: %s", password);

    }
}
