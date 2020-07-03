package com.akivaGrobman;

import com.akivaGrobman.backend.*;
import com.akivaGrobman.frontend.Window;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Main {

    private static Window window;

    public static void main(String[] args) throws InterruptedException {
        window = new Window();
        List<Integer> list = getNonOrderedList();
        SortingAlgorithm sortingAlgorithm = getSortingAlgorithm(list);
        sortingAlgorithm.sort();
        window.displayFinish(sortingAlgorithm.getClass().getSimpleName(), sortingAlgorithm.getSwapCount());
        sleep(3000);
        System.exit(0);

    }

    private static List<Integer> getNonOrderedList() {
        List<Integer> list = new ArrayList<>();
        final int listSize = 50;
        boolean[] usedNumbers = new boolean[listSize];
        Random randomNumberGenerator = new Random();
        Arrays.fill(usedNumbers, false);
        for (int i = 1; i <= listSize; i++) {
            int randomNumber;
            do {
               randomNumber  = randomNumberGenerator.nextInt(listSize);
            }while (usedNumbers[randomNumber]);
            list.add(randomNumber);
            usedNumbers[randomNumber] = true;
        }
        return list;
    }

    private static SortingAlgorithm getSortingAlgorithm(List<Integer> list) {
        return new QuickSort(list);
    }

    public static void updateDisplay(List<Integer> list) {
        window.validate();
        window.updateList(list);
    }

}
