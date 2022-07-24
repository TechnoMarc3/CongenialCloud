package de.linux3000.startup.questions.serverQuestions.serverGroup;

import de.linux3000.Cloud;
import de.linux3000.base.CloudBaseValues;
import de.linux3000.startup.StartupQuestion;

public class Ram implements StartupQuestion<Integer> {

    int answer = 0;

    @Override
    public String question() {
        return "How much ram should this Group use (this will be splitted for each Server and CANNOT be higher than the CloudRAM!)    - kB";
    }

    @Override
    public Integer answer() {
        return this.answer;
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
        if(answer >= (CloudBaseValues.ram * 0.97)) {
            System.out.println("Your input : " + answer + "    is higher than the CloudRAM, please select a smaller number!");
            return false;
        }
        Cloud.getINSTANCE().getCloudServerGroupCacheManager().get().setRam(answer);
        this.answer = answer;
        return true;
    }

    @Override
    public void clearAnswers() {
        this.answer = 0;
    }
}
