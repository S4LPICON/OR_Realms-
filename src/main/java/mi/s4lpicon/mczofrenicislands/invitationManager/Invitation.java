package mi.s4lpicon.mczofrenicislands.invitationManager;

import org.bukkit.entity.Player;

public class Invitation {

    private Player sender;
    private Player receiver;
    private String uuid;
    private String islandName;

    public Invitation(Player sender, Player receiver, String island){
        this.sender = sender;
        this.receiver = receiver;
        this.islandName = island;
    }

    public void acceptInvitation(){

    }
}
