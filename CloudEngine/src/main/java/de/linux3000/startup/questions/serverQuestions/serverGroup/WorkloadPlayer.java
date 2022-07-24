package de.linux3000.startup.questions.serverQuestions.serverGroup;

import de.linux3000.Cloud;
import de.linux3000.startup.StartupQuestion;

public class WorkloadPlayer implements StartupQuestion<Integer> {

    int answer = 0;

    @Override
    public String question() {
        return "With how many players should a new server be opened?";
    }

    @Override
    public Integer answer() {
        return answer;
    }

    @Override
    public int priority() {
        return 3;
    }

    @Override
    public Class getTypeParameter() {
        return Integer.class;
    }

    @Override
    public boolean setAnswer(Integer answer) {

        this.answer =answer;
        Cloud.getINSTANCE().getCloudServerGroupCacheManager().get().setPlayerForNew(answer);
        return true;
    }

    @Override
    public void clearAnswers() {
        this.answer = 0;
    }
}
