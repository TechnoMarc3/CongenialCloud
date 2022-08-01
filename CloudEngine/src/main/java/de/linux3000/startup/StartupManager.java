package de.linux3000.startup;


import de.linux300.api.versions.Versions;
import de.linux3000.Cloud;
import de.linux3000.base.CloudBaseValues;
import de.linux3000.utils.Downloader;
import de.linux3000.utils.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class StartupManager {

    File cloudFolder = new File("./Cloud");
    private String path = "./Cloud/Server";
    File wrapper;
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
        // askStartupQuestions();
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
            Versions.SPIGOT_1_19.setFile(f);
            new Downloader("https://download.getbukkit.org/spigot/spigot-1.19.jar", f).run();


        })
        .start();

        new Thread(() -> {
            File f = new File(pluginsFolder.getPath() + "/bungee-cord-1.16.4.jar");
            Versions.BUNGEECORD.setFile(f);
            new Downloader("https://ci.md-5.net/job/BungeeCord/lastStableBuild/artifact/bootstrap/target/BungeeCord.jar", f).run();

        })
                .start();
        new Thread(() -> {
            File f = new File(pluginsFolder.getPath() + "/spigot-1.16.4.jar");
            Versions.SPIGOT_1_16_4.setFile(f);
    new Downloader("https://cdn.getbukkit.org/spigot/spigot-1.16.4.jar", f).run();

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

     createProxy();

        this.hasDoneSetup = true;
        isDoingAnySetup = false;

        System.out.println("Setup finished");
    }

    private void createProxy() {
        File proxyFolder = new File(path + "/Proxy");
        System.out.println(proxyFolder.mkdirs());
        File o = new File("C:\\Users\\marco\\Documents\\Development\\Cloud\\ProxyWrapper\\out\\artifacts\\ProxyWrapper_jar\\ProxyWrapper.jar");
        wrapper= new File(path + "/Proxy/ProxyWrapper.jar");

        try {
            Files.copy(o.toPath(), wrapper.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Copied Wrapper");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(wrapper == null) {
            return;
        }

        ProcessBuilder builder = new ProcessBuilder();

        builder.directory(new File(wrapper.getAbsolutePath().replace("\\ProxyWrapper.jar", "")));
        builder.command("java","-javaagent:" + wrapper.getAbsolutePath(), "-jar", wrapper.getAbsolutePath(), CloudBaseValues.proxy.getAbsolutePath());
        try {
            Process p =  builder.start();
            System.out.println("started wrapper: " +p);

            new Thread(() -> {
                InputStreamReader reader = new InputStreamReader(p.getInputStream());
                InputStreamReader er = new InputStreamReader(p.getErrorStream());
                BufferedReader r = new BufferedReader(reader);
                BufferedReader err = new BufferedReader(er);
                String s;
                String error;
                while (true) {
                    try {
                        if((error = err.readLine()) != null) {
                            System.out.println(error);
                        }else {
                            break;
                        }
                        if ((s = r.readLine()) != null)  {
                            System.out.println(s);
                        }else {
                            break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();


        } catch (IOException e) {
            e.printStackTrace();
        }



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
