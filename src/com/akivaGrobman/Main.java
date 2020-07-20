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

    private final static String[] sortNames = {BubbleSort.class.getSimpleName(), InsertionSort.class.getSimpleName(), MergeSort.class.getSimpleName(), QuickSort.class.getSimpleName(), SelectionSort.class.getSimpleName()};
    private static Window window;
    private static int listSize;

    public static void main(String[] args) {
        // the parameter is the number of sorts on display
        window = new Window(sortNames.length);
    }

    // will update a specific algorithm on the display window
    public synchronized static void updateDisplay(List<Integer> list, int barBeingMovedPosition, List<Integer> evaluatedBarPositions, int id) {
        // will make sure the change will be visible
        window.validate();
        // will update the sorting algorithms variables(the actual list; what element being moved at the moment; and what elements are evaluated)
        window.updateBarBeingMoved(barBeingMovedPosition, id);
        window.updateBarBeingEvaluated(evaluatedBarPositions, id);
        window.updateList(list, id);
        // will update the specific panel that contains the algorithm being updated
        window.refresh(id);
    }

    // simple getter
    public static String getSortName(int index) {
        return sortNames[index];
    }

    // will be called when the button(start sorting) is clicked on the screen
    public static void startSorting(int listSize) {
        // this will be chosen by the user in the welcome display
        Main.listSize = listSize;
        // this thread will handel the sorting (back and front end)
        Thread thread = new Thread(() -> {
            // will get a non sorted list
            List<Integer> list = getNonOrderedList(false);
            // will sort the list using all the algorithms that are in the sortNames array
            runAllAlgorithms(list);
        });
        // this will put the sorting algorithms on display(and remove the welcome display)
        window.replacePanels();
        // will start the sorting once they are on display
        thread.start();
    }

    // will run all the algorithms in the sortNames array
    private static void runAllAlgorithms(List<Integer> list) {
        int sortingAlgorithmID = 0;
        for (String sortingAlgorithmName: sortNames) {
            // will make a final variable so it can be passed as a parameter to the thread lambda
            final int finalSortingAlgorithmID = sortingAlgorithmID;
            // will get a SortingAlgorithm object by the name of the algorithm(will pass a list to sort, and an id for the algorithm)
            SortingAlgorithm sortingAlgorithm = getSortingAlgorithmByName(sortingAlgorithmName, new ArrayList<>(list), finalSortingAlgorithmID);
            // this thread will sort this specific algorithm (and update the window as it does so)
            Thread sortThread = new Thread(() -> runSingeAlgorithm(sortingAlgorithm));
            // change the id
            sortingAlgorithmID++;
            // start sorting the algorithm
            sortThread.start();
        }
    }

    // runs the actual sorting algorithm
    private static void runSingeAlgorithm(SortingAlgorithm sortingAlgorithm) {
        // will sort the list and update the on screen display in real time
        sortingAlgorithm.sort();
        window.refresh(sortingAlgorithm.Id);
    }

    // will return a SortingAlgorithm object from sortingAlgorithm name(String)
    private static SortingAlgorithm getSortingAlgorithmByName(String sortingAlgorithmName, List<Integer> list, int index) {
        try {
            // will create a SoringAlgorithm class
            //                           todo change to non literal
            Class<?> cls = Class.forName("com.akivaGrobman.backend." + sortingAlgorithmName);
            // array of the constructor arguments the SortingAlgorithm class takes
            Class<?>[] paramTypes = {java.util.List.class, Integer.class};
            // will return an instance of SortingAlgorithm with list and index as it's constructor arguments
            return (SortingAlgorithm) cls.getDeclaredConstructor(paramTypes).newInstance(list, index);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            throw new Error(e.getMessage());
        }
    }

    // will return an array to sort
    private static List<Integer> getNonOrderedList(@SuppressWarnings("SameParameterValue") boolean withRepetitions) {
        List<Integer> list = new ArrayList<>();
        Random randomNumberGenerator = new Random();
        // will return an array that might contain the same element(number)
        if(withRepetitions) {
            for (int i = 0; i < listSize; i++) {
                list.add(randomNumberGenerator.nextInt(listSize));
            }
            return list;
        }
        // will return an array that doesn't contain the same element(number)
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
