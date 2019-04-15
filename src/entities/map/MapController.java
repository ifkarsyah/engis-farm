package entities.map;

import entities.Cell.Land;
import entities.animals.FarmAnimal;
import entities.product.Burger;

import java.awt.*;
import java.util.Scanner;

public class MapController {
    static MapModel mapModel = new MapModel();
    static Point truckPoint = mapModel.truck.getPoint();
    static Point mixerPoint = mapModel.mixer.getPoint();
    static Point wellPoint = mapModel.well.getPoint();

    public static void main(String[] args) {
        while (FarmAnimal.numOfFarmAnimal > 0) {
            System.out.println(mapModel);
            Scanner sc = new Scanner(System.in);
            System.out.print("Command: "); String cmd = sc.nextLine();

            FarmAnimal targetAnimal = mapModel.animalIsAroundPlayer();
            Point playerPoint = mapModel.player.getPoint();

            switch (cmd){
                case "talk":
                    if (targetAnimal != null) {
                        System.out.println("talk");
                        mapModel.player.talk(targetAnimal);
                    } else {
                        System.out.println("notalk");
                    }
                    break;
                case "interact":
                    if (targetAnimal != null) mapModel.player.interact(targetAnimal);
                    else if ((int) playerPoint.distanceSq(truckPoint) < 2) mapModel.player.interact(mapModel.truck);
                    else if ((int) playerPoint.distanceSq(wellPoint) < 2) mapModel.player.interact(mapModel.well);
                    break;
                case "kill":
                    if (targetAnimal != null) mapModel.player.kill(targetAnimal); break;
                case "grow":
                    mapModel.player.grow((Land) mapModel.mapLands.get(playerPoint)); break;
                case "mix":
                    if ((int) playerPoint.distanceSq(mixerPoint) < 2)
                        mapModel.mixer.mix(mapModel.player.getInventory(), new Burger());
                    break;
                case "a":
                    mapModel.playerMove(0, -1); break;
                case "s":
                    mapModel.playerMove(1, 0); break;
                case "d":
                    mapModel.playerMove(0, 1); break;
                case "w":
                    mapModel.playerMove(-1, 0); break;
                default:
                    System.out.println("typo");
            }

            mapModel.updateTick();
        }
    }
}
