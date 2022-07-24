package de.linux3000.startup.questions.serverQuestions.serverGroup;

import de.linux3000.Cloud;
import de.linux3000.startup.StartupQuestion;

public class Name implements StartupQuestion<String> {
    String answer = "";

    @Override
    public String question() {
        return "What's the name of this NEW ServerGroup";
    }

    @Override
    public String answer() {
        return answer;
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public Class getTypeParameter() {
        return String.class;
    }

    @Override
    public boolean setAnswer(String answer) {
        this.answer = answer;
        Cloud.getINSTANCE().getCloudServerGroupCacheManager().get().setName(answer);
        return true;
    }

    @Override
    public void clearAnswers() {
        this.answer = "";
    }
}
