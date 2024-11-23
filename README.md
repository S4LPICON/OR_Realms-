<h1><strong>OR - REALMS</strong></h1>
<p>
  <img src="https://img.shields.io/badge/Minecraft-Plugin-brightgreen" alt="Minecraft Plugin Badge">
  <img src="https://img.shields.io/badge/Java-21-orange" alt="Java Badge">
  <img src="https://img.shields.io/badge/license-MIT-blue.svg" alt="MIT License Badge">
</p>

<p><strong>OR - REALMS</strong> es un plugin para Minecraft que replica la forma en la que <strong>Origin Realms</strong> gestiona los realms de sus jugadores, incluyendo permisos, comandos y diversas funciones.</p>

<h2><strong>Comandos Disponibles</strong></h2>
<ul>
  <li><code>/island ban</code> <br>
    Banea a un jugador de tu isla. Si el jugador está en tu isla al momento del baneo, será expulsado automáticamente.</li>
  <li><code>/island create</code> <br>
    Crea una isla privada. El tamaño de la isla dependerá de tu nivel de permisos.</li>
  <li><code>/island invite &lt;nivel de permisos 1-2&gt;</code> <br>
    Invita a un jugador a tu isla con 2 niveles de permisos: <br>
    <ul>
      <li>Nivel 1: Puede romper y poner bloques.</li>
      <li>Nivel 2: Puede hacer todo.</li>
    </ul>
  </li>
  <li><code>/island join</code> <br>
    Acepta la invitación a una isla.</li>
  <li><code>/island leave</code> <br>
    Abandonas la isla de la que eres miembro.</li>
  <li><code>/island member</code> <br>
    <em>#EN DESARROLLO</em></li>
  <li><code>/island members</code> <br>
    <em>#EN DESARROLLO</em></li>
  <li><code>/island remove</code> <br>
    Remueve los permisos de un jugador en tu isla.</li>
  <li><code>/island reset</code> <br>
    <em>#EN DESARROLLO</em></li>
  <li><code>/island setspawn</code> <br>
    Establece el punto de aparición de tu isla.</li>
  <li><code>/island settings</code> <br>
    <em>#EN DESARROLLO</em></li>
  <li><code>/island tp [Player]</code> <br>
    Te teletransporta a la isla de un jugador. Si no se especifica un jugador, te lleva a tu propia isla.</li>
  <li><code>/island unban</code> <br>
    Desbanea a un jugador de tu isla.</li>
</ul>

<h2><strong>Permisos</strong></h2>
<table>
  <thead>
    <tr>
      <th>Permiso</th>
      <th>Descripción</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><code>mczofrenicisland.admin</code></td>
      <td>Otorga control absoluto sobre el plugin.</td>
    </tr>
    <tr>
      <td><code>mczofrenicisland.pro</code></td>
      <td>Rango pro del plugin. <em>#NO IMPLEMENTADO</em></td>
    </tr>
    <tr>
      <td><code>mczofrenicisland.elite</code></td>
      <td>Rango elite del plugin. <em>#NO IMPLEMENTADO</em></td>
    </tr>
  </tbody>
</table>

<h2><strong>Datos del Jugador</strong></h2>
<p>Los datos de los jugadores se almacenan en la carpeta <code>plugins/mczofrenic-island/</code>.<br>
Los archivos son en formato <code>.json</code> y contienen toda la información relacionada con cada isla en el servidor.</p>

<h2><strong>Consideraciones</strong></h2>
<blockquote>
  <p><strong>Nota:</strong><br>
  El plugin está fuera de desarrollo (estoy trabajando en una versión más optimizada y escalable). Las funciones más importantes que faltan son:</p>
  <ul>
    <li>Carga y descarga de islas para algunas situaciones.</li>
    <li>Implementación de los comandos faltantes.</li>
    <li>Generación de mundos personalizados (actualmente, los mundos son planos).</li>
    <li>Implementación de inventarios para diversas acciones.</li>
  </ul>
</blockquote>

<hr>

<p>Desarrollado por Jeferson Pinzon (S4LPICON)<br>
<p>Licenciado bajo <a href="https://opensource.org/licenses/MIT">Licencia MIT</a>.</p>
</p>
