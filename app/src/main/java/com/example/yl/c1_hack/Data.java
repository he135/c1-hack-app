package com.example.yl.c1_hack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YL on 1/11/2018.
 */

public class Data {

    public static List<Task> tasks = new ArrayList<Task>();

    public static void changeTasks(List<Task> tsk) {
        tasks = tsk;
    }
}
