package mi.s4lpicon.mczofrenicislands.islandsPermissions;

public class IslandsPermissions {

    public static String getStringAfterSlash(String input) {
        // Dividir el string en partes utilizando el carácter '/'
        String[] parts = input.split("/");

        // Verificar si hay al menos dos partes
        if (parts.length > 1) {
            // Retornar la parte después del '/'
            return parts[1];
        } else {
            // Si no hay '/' en el string, retornar el string original o manejar el caso como desees
            return input;
        }
    }
}
