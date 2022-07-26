package de.linux3000.startup.questions.cloudQuestions;

import de.linux300.api.versions.Versions;
import de.linux3000.Cloud;
import de.linux3000.base.CloudBaseValues;
import de.linux3000.startup.StartupQuestion;

import java.util.ArrayList;
import java.util.List;

public class Proxy implements StartupQuestion<String> {
    String answer;

    private List<String> versionsList = new ArrayList<>();

    @Override
    public String question() {
        String s = "";
        for(Versions versions : Versions.values()) {
            if(versions.getKey().equalsIgnoreCase("proxy")) {
                versionsList.add(versions.name());
                s+=versions.name() + " ";
            }
        }
        return "Which proxy should this cloud use (" + s + ")?";
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
        return check(answer);
    }

    private boolean check(String answer) {
        for(String s : versionsList) {
            if(s.equalsIgnoreCase(answer)) {

                CloudBaseValues.proxy = Versions.valueOf(s.toUpperCase()).getFile();
            }
        }
        return false;
    }

    @Override
    public void clearAnswers() {

    }
}
