package de.linux3000.cache;

import java.util.List;

public interface ICacheManager<T> {

    T get();
    boolean recycle();

}
