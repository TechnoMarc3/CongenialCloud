package de.linux3000.utils;

import java.util.stream.IntStream;

public class ArrayUtils {

    public static<T> int find(T[] a, T target)
    {
        return IntStream.range(0, a.length)
                .filter(i -> target.equals(a[i]))
                .findFirst()
                .orElse(-1);    // return -1 if the target is not found
    }
}
