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
    private static int listSize;

    public static void main(String[] args) {
        window = new Window(5);
    }

    // tells the window to update and updates the list and bar being moved position
    public synchronized static void updateDisplay(List<Integer> list, int barBeingMovedPosition, List<Integer> evaluatedBarPositions, String sortName) {
        int index = getIndex(sortName);
        window.validate();
        window.updateBarBeingMoved(barBeingMovedPosition, index);
        window.updateBarBeingEvaluated(evaluatedBarPositions, index);
        window.updateList(list, index);
        window.refresh(index);
    }

    public static void startSorting(int listSize) {
        Main.listSize = listSize;
        Thread thread = new Thread(Main::startSorting);
        Window.replacePanels();
        thread.start();
    }

    private static void startSorting() {
        List<Integer> list = getNonOrderedList();
        runAllAlgorithms(list);
//        System.exit(0);
    }

    private static void runAllAlgorithms(List<Integer> list) {
        // add to array every new sorting algorithm
        SortingAlgorithm[] sortingAlgorithms = new SortingAlgorithm[]{new BubbleSort(new ArrayList<>(list)), new InsertionSort(new ArrayList<>(list)), new MergeSort(new ArrayList<>(list)), new QuickSort(new ArrayList<>(list)), new SelectionSort(new ArrayList<>(list))};
        for (SortingAlgorithm sortingAlgorithm: sortingAlgorithms) {
            Thread sortThread = new Thread(() -> runSingeAlgorithm(sortingAlgorithm));
            sortThread.start();
        }
    }

    private static void runSingeAlgorithm(SortingAlgorithm sortingAlgorithm) {
        sortingAlgorithm.sort();
        window.displayFinish(sortingAlgorithm.getClass().getSimpleName(), sortingAlgorithm.getSwapCount(), getIndex(sortingAlgorithm.getClass().getSimpleName()));
        window.refresh(getIndex(sortingAlgorithm.getClass().getSimpleName()));
        try {
            sleep(2500);
        } catch (InterruptedException ignored) {}
    }

    private synchronized static int getIndex(String sortName) {
        String[] sortNames = {BubbleSort.class.getSimpleName(), InsertionSort.class.getSimpleName(), MergeSort.class.getSimpleName(), QuickSort.class.getSimpleName(), SelectionSort.class.getSimpleName()};
        return Arrays.asList(sortNames).indexOf(sortName);
    }

    // will return an array to sort
    private synchronized static List<Integer> getNonOrderedList() {
        List<Integer> list = new ArrayList<>();
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

}
