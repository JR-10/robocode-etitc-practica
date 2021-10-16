package etitc;

import robocode.*;
import robocode.Robot;

import java.awt.*;

import static robocode.util.Utils.normalRelativeAngleDegrees;

public class Tatan extends Robot {
    /**
     * run: Tatan's default behavior
     * dentro del run se colocaran las acciones que se ejecutaran
     * mientras el robot permanezco con vida
     */
    int contadorTiempo = 0; // contador del tiempo
    double giroCanon; // cuanto gira el cañon para buscar el objetivo
    String robotObjetivo; // Nombre del robot objetivo

    public void run() {
        // Initialization of the robot should be put here

        // After trying out your robot, try uncommenting the import at the top,
        // and the next line:

        // Setear los colores del Robot
        setBodyColor(Color.red); // color cuerpo del robot
        setGunColor(Color.red); // color parte del cañon
        setRadarColor(Color.orange); // color radar
        setScanColor(Color.green);
        setBulletColor(Color.blue);


        // Preparar el cañon
        robotObjetivo = null; // inicializar en null para no rastrear a nadie
        setAdjustGunForRobotTurn(true); // tener el arma quieta mientras se gira
        giroCanon = 10; // inicializar en 10



        // Robot main loop
        // Se ejecuta ciclo while y llama a los metodos
        while(true) {

            // Gira el cañon, le pasa como parametro cuanto debe girar
            turnGunRight(giroCanon);

            // Lleva la cuenta del tiempo que hemos estado buscando
            contadorTiempo++;
            // Si no hemos visto a nuestro objetivo durante 2 turnos, mira a la izquierda
            if (contadorTiempo > 2) {
                giroCanon = -10;
            }
            // Si todavía no hemos visto a nuestro objetivo durante 5 turnos, mira a la derecha
            if (contadorTiempo > 5) {
                giroCanon = 10;
            }
            // Si todavía no hemos visto a nuestro objetivo después de 10 turnos, buscamos otro objetivo
            if (contadorTiempo > 11) {
                robotObjetivo = null;
            }


            // Replace the next 4 lines with any behavior you would like
            ahead(100); // avanzar 100 unidades
            // turnGunRight(360); // girar el cañon 360 grados
            back(100); // retroceder 100 unidades
            // turnGunRight(360); // girar el cañon 360 grados

        }
    }

    /**
     * onScannedRobot: What to do when you see another robot
     * Robot al alcance
     */
    public void onScannedRobot(ScannedRobotEvent e) {
        // Replace the next line with any behavior you would like
        // disparar, recibe como parametro la potencia del disparo, a mayor potencia menos velocidad

        // Valida la distancia para determinar la potencia del disparo
        if (e.getDistance() < 50 && getEnergy() > 50) {
            fire(3);
        } // otherwise, fire 1.
        else {
            fire(1);
        }
        // Call scan again, before we turn the gun
        scan();
    }

    /**
     * onHitByBullet: What to do when you're hit by a bullet
     * Cuando hemos sido alcanzados por una bala
     */
    public void onHitByBullet(HitByBulletEvent e) {
        // Replace the next line with any behavior you would like
        back(10);
    }

    /**
     * onHitWall: What to do when you hit a wall
     *
     */
    public void onHitWall(HitWallEvent e) {
        // Replace the next line with any behavior you would like
        back(20);
    }



    /**
     * onHitRobot:  Set him as our new target
     * onHitRobot Buscar un nuevo objetivo
     */
    public void onHitRobot(HitRobotEvent e) {
        // Only print if he's not already our target.
        if (robotObjetivo != null && !robotObjetivo.equals(e.getName())) {
            out.println("Tracking " + e.getName() + " due to collision");
        }
        // Establecer el objetivo
        robotObjetivo = e.getName();

        // Retrocede un poco.
        // Nota: ¡No recibiremos eventos de escaneo mientras hacemos esto
        giroCanon = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));
        turnGunRight(giroCanon);
        fire(3); // tipo de disparo
        back(50); // retroceso
    }



    /**
     * onWin: metodo de baile si gana la batalla
     * @param e
     */
    public void onWin(WinEvent e) {
        for (int i = 0; i < 50; i++) {
            turnRight(30);
            turnLeft(30);
        }
    }


}
