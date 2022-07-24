package de.linux3000.commands;

import de.linux3000.commands.cmds.create.CreateNewCloudServerGroupCommand;

import java.util.ArrayList;
import java.util.List;

public class CommandRegistry {

    private List<ICommand> commands = new ArrayList<>();

    public void registerCommands() {
        this.commands.add(new CreateNewCloudServerGroupCommand());
    }

    public ICommand getCommand(String cmd) {
        for(ICommand cmds:commands) {
            if(cmds.prefix().equalsIgnoreCase(cmd)) {
                return cmds;
            }
        }
        return null;
    }

}
