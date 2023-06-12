package ru.stqa.pft.sandbox;
import java.util.Scanner;


public class RunPoint {
    public static void main(String[] args) {

            Scanner in = new Scanner (System.in);
            System.out.print("Введите координаты первой точки: ");
            Point p1 = new Point(in.nextDouble(), in.nextDouble());
            System.out.print("Введите координаты второй точки: ");
            Point p2 = new Point(in.nextDouble(), in.nextDouble());
            System.out.println("Расстояние между двумя точками = " + p1.distance(p2));

    }
}