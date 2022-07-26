package de.linux3000;

import java.lang.instrument.Instrumentation;
import java.util.jar.JarFile;

public class Agent {

    static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation inst) {
        System.out.println("in premain");
        instrumentation = inst;
    }

    public static void agentmain(String args, Instrumentation inst) {
        System.out.println("in agentmain");
        instrumentation = inst;
    }

    public static void appendJarFile(JarFile file) {
        System.out.println("agent: " + instrumentation);

            instrumentation.appendToSystemClassLoaderSearch(file);

    }




}