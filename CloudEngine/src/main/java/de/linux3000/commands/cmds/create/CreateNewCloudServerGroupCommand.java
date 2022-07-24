package de.linux3000.commands.cmds.create;

import de.linux3000.Cloud;
import de.linux3000.commands.ICommand;

public class CreateNewCloudServerGroupCommand implements ICommand {

    @Override
    public String prefix() {

        return "createServer";
    }

    @Override
    public void function() {
        Cloud.getINSTANCE().getStartupManager().askServerGroupQuestions();

    }
}
