package de.linux3000.startup.questions.cloudQuestions;

import de.linux3000.base.CloudBaseValues;
import de.linux3000.startup.StartupQuestion;

public class CloudName implements StartupQuestion<String> {

    String answer = "";

        @Override
    public String question() {
        return "What's the name of the cloud?";
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
        CloudBaseValues.name = answer;

        return true;
    }

    @Override
    public void clearAnswers() {
        this.answer = "";
    }


}
