package com.example.demo.util;

import java.time.LocalDate;

public class DateUtil {
    public static boolean isFuture(LocalDate d){
        return d.isAfter(LocalDate.now());
    }
}
