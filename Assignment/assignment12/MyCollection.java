package com.fsd.assignment12;

import java.util.Arrays;
import java.util.Scanner;

public class MyCollection {

    public static void main(String args[]) {
        Integer intArray[];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of elements");
        final int ARR_SIZE = scanner.nextInt();
        intArray = new Integer[ARR_SIZE];
        System.out.println("Enter elements");
        for(int i = 0; i < ARR_SIZE; i++) {
            intArray[i] = scanner.nextInt();
        }
        System.out.println("Before sorting elements");
        for(int num: intArray) {
            System.out.print(num + " ");
        }
        System.out.println();
        Arrays.sort(intArray, new MyComparator());
        System.out.println("After sorting elements");
        for(int num: intArray) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
