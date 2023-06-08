package ru.stqa.pft.sandbox;

public class MyFirstProgram {


	public static void main (String[] args){
		hello("world");
		hello("user");
		hello("Alex");


		Point P = new Point ();
		P.p1 = 4;
		P.p2 = 7;
		P.p3 = 8;
		P.p4 = 9;
		System.out.println("Points distance =" + distance(P));


		Square s = new Square(5);
		System.out.println("Площаль квадрата со стороной "+ s.l + " = " + area(s));

		Rectangle r = new Rectangle(4,6);
		System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + area  (r));
		}

		public static void hello(String somebody) {
			System.out.println("Hello," + somebody + "!");
		}

		public static double area (Square s){
		return s.l * s.l;
		}
		public static double area (Rectangle r){
		return r.a * r.b;
		}
		public static double distance  (Point P){
		return Math.sqrt((P.p3-P.p1)*(P.p3-P.p1)+(P.p4-P.p2)*(P.p4-P.p2));
				}
       }


