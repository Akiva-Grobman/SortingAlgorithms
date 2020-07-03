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
        // pretty self explanatory
        window = new Window();
        List<Integer> list = getNonOrderedList();
        SortingAlgorithm sortingAlgorithm = getSortingAlgorithm(list);
        sortingAlgorithm.sort();
        window.displayFinish(sortingAlgorithm.getClass().getSimpleName(), sortingAlgorithm.getSwapCount());
        sleep(3000);
        System.exit(0);

    }

    // will return an array to sort
    private static List<Integer> getNonOrderedList() {
        List<Integer> list = new ArrayList<>();
        final int listSize = 150;
        // this represents the numbers already used
        boolean[] usedNumbers = new boolean[listSize];
        Random randomNumberGenerator = new Random();
        // this is because we start off with no numbers used
        Arrays.fill(usedNumbers, false);
        for (int i = 0; i < listSize; i++) {
            int randomNumber;
            do {
                // will generate a new random number until we find one not used
                randomNumber  = randomNumberGenerator.nextInt(listSize);
            }while (usedNumbers[randomNumber]);
            // the plus one is so we don't have a 0 element(not really an issue i'm just insane)
            list.add(randomNumber + 1);
            usedNumbers[randomNumber] = true;
        }
        return list;
    }

    // will determine what sort to use
    private static SortingAlgorithm getSortingAlgorithm(List<Integer> list) {
        return new SelectionSort(list);
    }

    // tells the window to update and updates list that will be on display
    public static void updateDisplay(List<Integer> list) {
        window.validate();
        window.updateList(list);
    }

}
