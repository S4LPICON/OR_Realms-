# OR - REALMS
Plugin que replica la manera en la que Origin Realms gestiona los realms de sus jugadores, incluyendo permisos, commandos y demas funciones.
# COMANDOS DISPONIBLES
- island ban <Player> -> banea un jugador de la isla (si esta en tu isla al momento de banearlo lo saca de esta)
- island create -> crear una isla privada, segun tu nivel de permisos sera mas grande o pequeña
- island invite <Player> <nivel de permisos 1-2> -> invita a un jugador a tu isla con 2 niveles de permisos : lv 1 solo puede romper y poner bloques lv2 puede hacer todo
- island join <Player> -> acepta la invitacion de un jugador a su isla
- island leave -> sales de ser miembro de una isla
- island member -> #EN DESARROLLO
- island members -> #EN DESARROLLO
- island remove <Player> -> le quita los permisos a un jugador de tu isla
- island reset -> #EN DESARROLLO
- island setspawn -> seteas el punto de aparicion de tu isla al hacer  /island tp 
- island settings -> #EN DESARROLLO
- island tp [Player] -> te teletrasnporta a la isla de un jugador, si no pones argumentos te lleva a tu isla
- island unban <Player> -> desbaneas a un jugador de tu isla

# PERMISOS
- mczofrenicisland.admin -> otorga poder absoluto del plugin
- mczofrenicisland.pro -> rango pro del plugin #NO IMPLEMENTADO
- mczofrenicisland.elite -> rango elite del plugin #NO IMPLEMENTADO

# LOS DATOS
Los datos de los jugadores son guardados en la carpeta plugins/mczofrenic-island/, los archivos son .json y contienen la informacion de cada isla del servidor

# A TENER EN CUENTA
Plugin no terminado, lo mas importante que falta es la carga y descarga de las islas, ademas de los comandos faltantes, la generacion de los mundos que por el momento son mundos planos y la implementacion de inventarios para las distintas acciones
