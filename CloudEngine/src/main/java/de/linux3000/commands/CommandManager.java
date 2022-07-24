package de.linux3000.commands;


import de.linux3000.Cloud;

import java.util.Scanner;

public class CommandManager {

    public void listenForCommands() {

            Scanner scanner = new Scanner(System.in) ;
            String s;
            while((s = scanner.nextLine()) != null) {
                if(!Cloud.getINSTANCE().getStartupManager().isDoingAnySetup) {
                    if(s.equalsIgnoreCase("exit")) {

                        //TODO: Wait for shutdown...

                        System.exit(0);

                    }
                    else if(!s.startsWith("cloud")) {
                    continue;
                    }
                String[] cmd = s.split(" ");
                ICommand command = Cloud.getINSTANCE().getCommandRegistry().getCommand(cmd[1]);
                if(command != null) {
                    command.function();
                }else {
                    System.out.println("This command is unavailable");
                }
            }
        }
    }

}
