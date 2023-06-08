package ru.stqa.pft.sandbox;
import java.util.Scanner;

public class RunPoint {
    public static void main(String[] args) {

            Scanner in = new Scanner (System.in);

            Point P = new Point ();
            System.out.print("Введите координаты точки А (_,_) : ");
            P.p1 = in.nextInt();
            P.p2 = in.nextInt();
            System.out.print("Введите координаты точки B (_,_) : ");
            P.p3 = in.nextInt();
            P.p4 = in.nextInt();
            System.out.println("Points distance = " + distance(P));
        }
        public static double distance (Point P){
            return Math.sqrt((P.p3-P.p1)*(P.p3-P.p1)+(P.p4-P.p2)*(P.p4-P.p2));
    }
}
