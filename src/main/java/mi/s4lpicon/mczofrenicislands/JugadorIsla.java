package mi.s4lpicon.mczofrenicislands;

import org.bukkit.*;
import org.bukkit.entity.Player;

import java.io.File;

public class JugadorIsla {

    Player jugador;
    int id;
    int spawn_x, spawn_y, spawn_z;
    String tipo;
    String escala;
    World mundo;
    //entidades
    //permisos

    public JugadorIsla(Player jugador, int id, String tipo, String escala){
        this.jugador = jugador;
        this.id = id;
        this.tipo = tipo;
        this.escala = escala;
        crearMundo();
    }

    public void crearMundo() {
        // Especificar el nombre del mundo
        String nombreDelMundo = "IslasJugadores/" + this.jugador.getName();

        // Configurar el creador del mundo
        WorldCreator creator = new WorldCreator(nombreDelMundo);
        creator.environment(World.Environment.NORMAL); // Tipo de entorno
        creator.type(WorldType.NORMAL); // Tipo de mundo

        // Crear el mundo
        World mundo = creator.createWorld();

        // Configurar el borde del mundo
        assert mundo != null;
        WorldBorder border = mundo.getWorldBorder();
        border.setCenter(0, 0);
        border.setSize(500);
        border.setWarningDistance(10);
        border.setDamageAmount(2.0);
        this.mundo = mundo;
    }



}
