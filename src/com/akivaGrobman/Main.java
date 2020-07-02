package com.akivaGrobman;

import com.akivaGrobman.backend.*;
import com.akivaGrobman.frontend.Window;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Main {

    private static Window window;

    public static void main(String[] args) throws InterruptedException {
        window = new Window();
        List<Integer> list = new ArrayList<>();
        for (int i = 50; i > 0; i--) {
            list.add(i);
        }
        SortingAlgorithm sortingAlgorithm = getSortingAlgorithm(list);
        sortingAlgorithm.sort();
        window.displayFinish(sortingAlgorithm.getClass().getSimpleName(), sortingAlgorithm.getChangeCount());
        sleep(3000);
        System.exit(0);

    }

    private static SortingAlgorithm getSortingAlgorithm(List<Integer> list) {
        return new BubbleSort(list);
    }

    public static void updateDisplay(List<Integer> list) {
        window.validate();
        window.updateList(list);
    }

}
