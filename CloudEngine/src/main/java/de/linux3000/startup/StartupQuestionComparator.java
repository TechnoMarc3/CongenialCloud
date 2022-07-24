package de.linux3000.startup;

import java.util.Comparator;

public class StartupQuestionComparator implements Comparator<StartupQuestion<?>> {
    @Override
    public int compare(StartupQuestion<?> o1, StartupQuestion<?> o2) {
        return Integer.compare(o1.priority(), o2.priority());
    }
}
