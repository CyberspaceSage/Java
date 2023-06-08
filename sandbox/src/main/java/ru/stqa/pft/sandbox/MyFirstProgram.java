package ru.stqa.pft.sandbox;


import java.util.Scanner;

public class MyFirstProgram {


	public static void main (String[] args){
		hello("!");

		Scanner in = new Scanner (System.in);

		Point P = new Point ();
		System.out.print("Введите координаты точки А (_,_) : ");
		P.p1 = in.nextInt();
		P.p2 = in.nextInt();
		System.out.print("Введите координаты точки B (_,_) : ");
		P.p3 = in.nextInt();
		P.p4 = in.nextInt();
		System.out.println("Points distance = " + distance(P));

		System.out.println();


		Square s = new Square (5);
		System.out.println("Площаль квадрата со стороной "+ s.l + " = " + area(s));

		System.out.println();

		Rectangle r = new Rectangle (4,6);
		System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + area  (r));
		}

		public static void hello (String somebody) {
			System.out.println("Hello my dear friend" + somebody + "!");
		}

		public static double area (Square s){
		return s.l * s.l;
		}
		public static double area (Rectangle r){
		return r.a * r.b;
		}
		public static double distance (Point P){
		return Math.sqrt((P.p3-P.p1)*(P.p3-P.p1)+(P.p4-P.p2)*(P.p4-P.p2));
		}
}


