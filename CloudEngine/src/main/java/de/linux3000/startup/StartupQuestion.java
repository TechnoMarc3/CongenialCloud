package de.linux3000.startup;

public interface StartupQuestion<T> {

    String question();
    T answer();

    //0 -> highest
    //1 -> normal
    //2 -> can be ignored
    //3 -> optional, not necessary

    int priority();

    Class getTypeParameter();

    boolean setAnswer(T answer);

    void clearAnswers();






}
