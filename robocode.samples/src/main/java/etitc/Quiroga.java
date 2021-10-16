package etitc;

import robocode.HitByBulletEvent;
import robocode.HitWallEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;

public class Quiroga extends Robot {
    /**
     * run: Tatan's default behavior
     * dentro del run se colocaran las acciones que se ejecutaran
     * mientras el robot permanezco con vida
     */
    public void run() {
        // Initialization of the robot should be put here

        // After trying out your robot, try uncommenting the import at the top,
        // and the next line:

        // setColors(Color.red,Color.blue,Color.green); // body,gun,radar

        // Robot main loop
        // Se ejecuta ciclo while y llama al los metodos
        while(true) {
            // Replace the next 4 lines with any behavior you would like
            ahead(200); // avanzar 100 unidades
            turnGunRight(180); // girar el cañon 360 grados
            back(150); // retroceder 100 unidades
            turnGunRight(260); // girar el cañon 360 grados
        }
    }

    /**
     * onScannedRobot: What to do when you see another robot
     * Robot al alcance
     */
    public void onScannedRobot(ScannedRobotEvent e) {
        // Replace the next line with any behavior you would like
        // disparar, recibe como parametro la potencia del disparo, a mayor potencia menos velocidad
        fire(5);
    }

    /**
     * onHitByBullet: What to do when you're hit by a bullet
     * Cuando hemos sido alcanzados por una bala
     */
    public void onHitByBullet(HitByBulletEvent e) {
        // Replace the next line with any behavior you would like
        back(20);
    }

    /**
     * onHitWall: What to do when you hit a wall
     *
     */
    public void onHitWall(HitWallEvent e) {
        // Replace the next line with any behavior you would like
        back(10);
    }


}
