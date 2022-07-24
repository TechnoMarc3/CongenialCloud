package de.linux3000.startup.questions.cloudQuestions;

import de.linux3000.Cloud;
import de.linux3000.base.CloudBaseValues;
import de.linux3000.startup.StartupQuestion;

public class Ram implements StartupQuestion<Integer> {

    int answer;

    @Override
    public String question() {
        return "How much ram should the cloud use?    - kB";
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
        this.answer = answer;
        CloudBaseValues.ram = answer;
        return true;
    }

    @Override
    public void clearAnswers() {
        this.answer = 0;
    }


}
