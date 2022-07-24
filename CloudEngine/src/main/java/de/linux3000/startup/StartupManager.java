package de.linux3000.startup;


import de.linux3000.Cloud;
import de.linux3000.base.CloudBaseValues;
import de.linux3000.utils.Downloader;
import de.linux3000.utils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class StartupManager {

    File cloudFolder = new File("./Cloud");
    File essentialsFolder = new File("./Cloud/Essentials");
    File databaseFolder = new File("./Cloud/Essentials/Base");
    File pluginsFolder = new File("./Cloud/Essentials/Base/Plugins");
    File baseFolder = new File("./Cloud/Essentials/Database");
    File serverFolder = new File("./Cloud/Server");


    public boolean hasDoneSetup = false;
    public boolean isDoingAnySetup = false;

    public void doStartup() {
        try {
            createFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!hasDoneSetup) {
          askStartupQuestions();
        }
    }

    private void createFiles() throws IOException {

        System.out.println(cloudFolder.mkdirs());
        System.out.println(essentialsFolder.mkdirs());
        System.out.println(databaseFolder.mkdirs());
        System.out.println(baseFolder.mkdirs());
        System.out.println(serverFolder.mkdirs());
        System.out.println(pluginsFolder.mkdirs());

        new Thread(() -> {
            File f = new File(pluginsFolder.getPath() + "/spigot-1.19.jar");
            new Downloader("https://download.getbukkit.org/spigot/spigot-1.19.jar", f).run();
            CloudBaseValues.spigot_1_19 = f;

        })
        .start();
        new Thread(() -> {
            File f = new File(pluginsFolder.getPath() + "/spigot-1.16.4.jar");
    new Downloader("https://cdn.getbukkit.org/spigot/spigot-1.16.4.jar", f).run();
    CloudBaseValues.spigot_1_16_4 = f;
        })
            .start();


    }

    private void askStartupQuestions() {
        isDoingAnySetup = true;
        Scanner scanner = new Scanner(System.in);
        for(StartupQuestion question : Cloud.getINSTANCE().getOrganizer().getQuestionsOrdered()) {
            System.out.println(question.question());
            if(question.getTypeParameter().equals(String.class)) {


                String s;
                while((s = scanner.nextLine())!= null) {

                    if (StringUtils.onlyDigits(s)) {
                        System.out.println("Please type a String (...some words)");
                        System.out.println(question.question());

                    } else {
                        question.setAnswer(s);
                        break;
                    }
                }

            }
            if(question.getTypeParameter().equals(Integer.class)) {
                String s;
                while((s = scanner.nextLine())!= null) {
                    if (!StringUtils.onlyDigits(s)) {
                        System.out.println("Please type an Integer (1 - 2,1b)");
                        System.out.println(question.question());
                    } else {
                        if(question.setAnswer(Integer.parseInt(s))) {
                            break; }
                    }
                }

            }
            if(question.getTypeParameter().equals(Double.class)) {
                String s;
                while((s = scanner.nextLine())!= null) {
                    if (!StringUtils.onlyDigits(s)) {
                        System.out.println("Please type a Double (e.g. 0.01)");
                        System.out.println(question.question());
                    } else {
                        if(question.setAnswer(Double.parseDouble(s))) {
                            break; }
                    }
                }
            }
            if(question.getTypeParameter().equals(Float.class)) {
                String s;
                while((s = scanner.nextLine())!= null) {
                    if (!StringUtils.onlyDigits(s)) {
                        System.out.println("Please type a Float (e.g. 0.01)");
                        System.out.println(question.question());
                    } else {
                        if(question.setAnswer(Float.parseFloat(s))) {
                            break; }
                    }
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
        this.hasDoneSetup = true;
        isDoingAnySetup = false;

        System.out.println("Setup finished");
    }

    public void askServerGroupQuestions() {
        List<StartupQuestion<?>> questions = Cloud.getINSTANCE().getOrganizer().getServerQuestionsOrdered();
        isDoingAnySetup = true;
        Scanner scanner = new Scanner(System.in);
        for(StartupQuestion question : questions) {
            System.out.println(question.question());
            if(question.getTypeParameter().equals(String.class)) {


                String s;
                while((s = scanner.nextLine())!= null) {

                    if (StringUtils.onlyDigits(s)) {
                        System.out.println("Please type a String (...some words)");
                        System.out.println(question.question());

                    } else {
                        if(question.setAnswer(s)) {
                            break; }
                    }
                }

            }
            if(question.getTypeParameter().equals(Integer.class)) {
                String s;
                while((s = scanner.nextLine())!= null) {
                    if (!StringUtils.onlyDigits(s)) {
                        System.out.println("Please type an Integer (1 - 2,1b)");
                        System.out.println(question.question());
                    } else {
                        if(question.setAnswer(Integer.parseInt(s))) {
                            break; }
                    }
                }

            }
            if(question.getTypeParameter().equals(Double.class)) {
                String s;
                while((s = scanner.nextLine())!= null) {
                    if (!StringUtils.onlyDigits(s)) {
                        System.out.println("Please type a Double (e.g. 0.01)");
                        System.out.println(question.question());
                    } else {
                        if(question.setAnswer(Double.parseDouble(s))) {
                            break; }
                    }
                }
            }
            if(question.getTypeParameter().equals(Float.class)) {
                String s;
                while((s = scanner.nextLine())!= null) {
                    if (!StringUtils.onlyDigits(s)) {
                        System.out.println("Please type a Float (e.g. 0.01)");
                        System.out.println(question.question());
                    } else {
                        if(question.setAnswer(Float.parseFloat(s))) {
                            break; }
                    }
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

        Cloud.getINSTANCE().getCloudServerGroupManager().handleAnswers();
        questions.forEach(StartupQuestion::clearAnswers);
        this.hasDoneSetup = true;
        isDoingAnySetup = false;


        System.out.println("Setup finished");

    }



}
