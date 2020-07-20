package com.akivaGrobman;

import com.akivaGrobman.backend.*;
import com.akivaGrobman.frontend.Window;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import static java.lang.Thread.sleep;

public class Main {

    /*
     *  This program is dependent on the sortNames array.
     *  The display and backend are determined by the length and sort names in side it.
     *  Every new Algorithm that's added, should be represented in the sort names array. (preferably SortingAlgorithmName.class.getSimpleName() )
     */

    // every time a new sort is added this must be updated
    private final static String[] sortNames = {BubbleSort.class.getSimpleName(), InsertionSort.class.getSimpleName(), MergeSort.class.getSimpleName(), QuickSort.class.getSimpleName(), SelectionSort.class.getSimpleName()};
    private static Window window;
    private static int listSize;

    public static void main(String[] args) {
        // the parameter is the number of sorts on display
        window = new Window(sortNames.length);
    }

    // tells the window to update and updates the list and bar being moved position
    public synchronized static void updateDisplay(List<Integer> list, int barBeingMovedPosition, List<Integer> evaluatedBarPositions, int id) {
        window.validate();
        window.updateBarBeingMoved(barBeingMovedPosition, id);
        window.updateBarBeingEvaluated(evaluatedBarPositions, id);
        window.updateList(list, id);
        window.refresh(id);
    }

    // simple getter
    public static String getSortName(int index) {
        return sortNames[index];
    }

    // will be called when the button(start sorting) is clicked on the screen
    public static void startSorting(int listSize) {
        Main.listSize = listSize;
        Thread thread = new Thread(Main::startSorting);
        window.replacePanels();
        thread.start();
    }

    // will generate a list to sort and then sort it
    private static void startSorting() {
        List<Integer> list = getNonOrderedList(false);
        runAllAlgorithms(list);
    }

    // will run all the algorithms in the sortNames array
    private static void runAllAlgorithms(List<Integer> list) {
        int index = 0;
        for (String sortingAlgorithmName: sortNames) {
            int finalIndex = index;
            Thread sortThread = new Thread(() -> runSingeAlgorithm(getSortingAlgorithmByName(sortingAlgorithmName, new ArrayList<>(list), finalIndex)));
            index++;
            sortThread.start();
        }
    }

    // runs the actual sorting algorithm
    private static void runSingeAlgorithm(SortingAlgorithm sortingAlgorithm) {
        sortingAlgorithm.sort();
        window.refresh(sortingAlgorithm.Id);
        try {
            sleep(2500);
        } catch (InterruptedException ignored) {}
    }

    // will return a SortingAlgorithm object from sortingAlgorithm name(String)
    private static SortingAlgorithm getSortingAlgorithmByName(String sortingAlgorithmName, List<Integer> list, int index) {
        try {
            //todo change to non literal
            Class<?> cls = Class.forName("com.akivaGrobman.backend." + sortingAlgorithmName);
            Class<?>[] paramTypes = {java.util.List.class, Integer.class};
            return (SortingAlgorithm) cls.getDeclaredConstructor(paramTypes).newInstance(list, index);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            throw new Error(e.getMessage());
        }
    }

    // will return an array to sort
    private static List<Integer> getNonOrderedList(@SuppressWarnings("SameParameterValue") boolean withRepetitions) {
        List<Integer> list = new ArrayList<>();
        Random randomNumberGenerator = new Random();
        if(withRepetitions) {
            for (int i = 0; i < listSize; i++) {
                list.add(randomNumberGenerator.nextInt(listSize));
            }
            return list;
        }
        // this represents the numbers already used
        boolean[] usedNumbers = new boolean[listSize];
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
