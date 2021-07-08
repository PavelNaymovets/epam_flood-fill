package com.epam.rd.autocode.floodfill;

public interface FloodLogger {
    default void log(String floodState){
        System.out.println(floodState + "\n");
    };
}
