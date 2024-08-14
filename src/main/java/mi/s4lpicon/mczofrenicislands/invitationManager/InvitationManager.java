package mi.s4lpicon.mczofrenicislands.invitationManager;

import mi.s4lpicon.mczofrenicislands.islandsManager.IslandsManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class InvitationManager {

    public static ArrayList<Invitation> activeInvitations = new ArrayList<>();

    public static void sendInvitation(Player sender, String receiver, int permission){
        Player preceiver = Bukkit.getPlayer(receiver);
        if (receiver.equals(sender.getName())){
            sender.sendMessage("No puedes invitarte a tu propia isla!");
            return;
        }
        if (preceiver == null){
            sender.sendMessage("Ese jugador no existe, o no se encuentra conectado!");
            return;
        }
        Invitation invitation = new Invitation(sender,preceiver, permission);
        if (invitationExist(sender,receiver) != -1){
            sender.sendMessage("Ya le enviaste una invitación a este jugador!");
            return;
        }
        activeInvitations.add(invitation);
        sender.sendMessage("Has invitado a "+preceiver.getName()+" a tu isla!");
        preceiver.sendMessage(sender.getName()+" te ha invitado a unirte a su isla! usa /island join para aceptarla");
    }

    public static void acceptInvitation(String sender,Player receiver){
        Player psender = Bukkit.getPlayer(sender);
        int posInvitation = invitationExist(psender,receiver.getName());
        if (posInvitation == -1){
            receiver.sendMessage("No existe tal invitación");
            return;
        }
        IslandsManager.addPlayerToIsland(receiver.getName(), sender, activeInvitations.get(posInvitation).getPermissionLevel());
        receiver.sendMessage("Has aceptado correctamente la invitación de "+sender);
        activeInvitations.remove(posInvitation);
    }

    public static int invitationExist(Player sender, String receiver){
        if (sender != null) {
            for (Invitation invitation : activeInvitations) {
                if (invitation.getSenderName().equals(sender.getName()) && invitation.getReceiverName().equals(receiver)) {
                    return activeInvitations.indexOf(invitation);
                }
            }
        }
        return -1;
    }

    public static ArrayList<String> getInvitationsOfPlayer(Player receiver){
        ArrayList<String> invites = new ArrayList<>();
        for (Invitation invitation : activeInvitations){
            if (invitation.getReceiverName().equals(receiver.getName())){
                invites.add(invitation.getSenderName());
            }
        }
        return invites;
    }
}
