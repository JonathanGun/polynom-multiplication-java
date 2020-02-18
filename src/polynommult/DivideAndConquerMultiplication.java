package polynommult;

import java.util.Map;
import java.util.Scanner;

public class DivideAndConquerMultiplication implements MultBehav {
	public Polynom multiply(Polynom p1, Polynom p2){
        // Basis
        if(p1.coef.size() == 1){
            p1.multBehav = new BruteforceMultiplication();
            return p1.multiply(p2);
        }
        
        // Divide
        Polynom A0 = new Polynom(p1.multBehav);
        Polynom A1 = new Polynom(p1.multBehav);
        this.divide(p1, A0, A1);
        A1 = A1.promote(-p1.coef.size()/2);

        Polynom B0 = new Polynom(p1.multBehav);
        Polynom B1 = new Polynom(p1.multBehav);
        this.divide(p2, B0, B1);
        B1 = B1.promote(-p2.coef.size()/2);

        // Conquer
        Polynom Y = this.multiply(A0.add(A1), B0.add(B1));
        Polynom U = this.multiply(A0, B0);
        Polynom Z = this.multiply(A1, B1).promote(p2.coef.size()/2*2);
        
        // Merge
        Polynom V = Y.subtract(U).subtract(Z).promote(A0.coef.size());
		return U.add(V).add(Z);
	}

    private void divide(Polynom p, Polynom p1, Polynom p2){
        int size = 0;
        for(Map.Entry<Integer, Integer> term : p.coef.entrySet()){
            size++;
            if(size > p.coef.size()/2){
                p2.coef.put(term.getKey(), term.getValue());
            } else {
                p1.coef.put(term.getKey(), term.getValue());
            }
        }
    }
}
