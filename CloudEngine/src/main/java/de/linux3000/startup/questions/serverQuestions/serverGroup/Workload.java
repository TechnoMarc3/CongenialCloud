package de.linux3000.startup.questions.serverQuestions.serverGroup;

import de.linux3000.Cloud;
import de.linux3000.startup.StartupQuestion;

public class Workload implements StartupQuestion<Integer> {

    int answer = 0;

    @Override
    public String question() {
        return "At what load should a new server be opened?";
    }

    @Override
    public Integer answer() {
        return answer;
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public Class getTypeParameter() {
        return Integer.class;
    }

    @Override
    public boolean setAnswer(Integer answer) {
        this.answer =answer;
        Cloud.getINSTANCE().getCloudServerGroupCacheManager().get().setPercentageForNew(answer);
        return true;
    }

    @Override
    public void clearAnswers() {
        this.answer = 0;
    }
}
