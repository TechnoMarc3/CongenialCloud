package de.linux3000.startup;

import de.linux3000.startup.questions.serverQuestions.serverGroup.*;
import de.linux3000.startup.questions.cloudQuestions.CloudName;

import java.util.ArrayList;
import java.util.List;

public class StartupOrganizer {

    private List<StartupQuestion<?>> questions = new ArrayList<>();
    private List<StartupQuestion<?>> serverQuestions = new ArrayList<>();

    public void registerQuestions() {
        questions.add(new CloudName());
        questions.add(new de.linux3000.startup.questions.cloudQuestions.Ram());
    }

    public void registerServerQuestions() {
        serverQuestions.add(new Name());
        serverQuestions.add(new Ram());
        serverQuestions.add(new Version());

        serverQuestions.add(new Workload());
        serverQuestions.add(new WorkloadPlayer());
    }


    public List<StartupQuestion<?>> getQuestionsOrdered() {
        questions.sort(new StartupQuestionComparator());
        return questions;
    }

    public List<StartupQuestion<?>> getServerQuestionsOrdered() {
        serverQuestions.sort(new StartupQuestionComparator());
        return serverQuestions;
    }



}
