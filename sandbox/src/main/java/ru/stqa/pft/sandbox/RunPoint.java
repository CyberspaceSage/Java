package ru.stqa.pft.sandbox;
import java.util.Scanner;

public class RunPoint {
    public static void main(String[] args) {

            Scanner in = new Scanner (System.in);
            System.out.print("Введите координаты точек через пробел А (),() ; B (),() ");
            Point sq = new Point (in.nextDouble(), in.nextDouble(),in.nextDouble(),in.nextDouble());
            System.out.println("Расстояние между двумя точками = " + sq.distance());
    }
}