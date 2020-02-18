import polynommult.*;
import java.util.Scanner;

public class Main{
    // public static final int n = 5000;
	public static void main(String[] args){
		System.out.print("Masukkan nilai n: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Polynom p;
        Polynom p1 = new Polynom(new BruteforceMultiplication());
        Polynom p2 = new Polynom(new DivideAndConquerMultiplication());
        
        p1.generateRandomPolynom(n);
        p2.generateRandomPolynom(n);
        
        System.out.println("Polynom 1:");
        p1.print();
        System.out.println("Polynom 2:");
        p2.print();
        System.out.println();

        // System.out.println("Add");
        // p1.subtract(p2).print();
        // System.out.println("Subtract");
        // p1.add(p2).print();
        
        long startTime, endTime;

        startTime = System.nanoTime();
        Polynom p3 = p1.multiply(p2);
        endTime = System.nanoTime();
        System.out.println("Multiplication using Bruteforce algorithm");
        p3.print();
        System.out.println("Time elapsed: " + (endTime-startTime)/1000000 + " ms");
        System.out.println("Total operations: " + p3.multBehav.ops);
        System.out.println();

        startTime = System.nanoTime();
        Polynom p4 = p2.multiply(p1);
        endTime = System.nanoTime();
        System.out.println("Multiplication using Divide and Conquer algorithm");
        p4.print();
        System.out.println("Time elapsed: " + (endTime-startTime)/1000000 + " ms");
        System.out.println("Total operations: " + p4.multBehav.ops);
        System.out.println();
	}
}