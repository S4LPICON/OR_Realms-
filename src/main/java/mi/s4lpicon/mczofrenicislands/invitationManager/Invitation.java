package mi.s4lpicon.mczofrenicislands.invitationManager;

import org.bukkit.entity.Player;

public class Invitation {

    private Player sender;
    private Player receiver;
    private int permissionLevel;

    public Invitation(Player sender, Player receiver, int permissionLevel){
        this.sender = sender;
        this.receiver = receiver;
        this.permissionLevel = permissionLevel;
    }

    public void acceptInvitation(){

    }

    public String getSenderName(){
        return this.sender.getName();
    }

    public String getReceiverName(){
        return this.receiver.getName();
    }

    public int getPermissionLevel(){
        return this.permissionLevel;
    }
}
