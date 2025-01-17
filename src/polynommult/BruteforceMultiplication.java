package polynommult;

import java.util.Map;

public class BruteforceMultiplication extends MultBehav {
	public BruteforceMultiplication(){
        this(0);
    }
    
    public BruteforceMultiplication(int prevOps){
        this.ops = prevOps;
    }

    public Polynom multiply(Polynom p1, Polynom p2){
		Polynom p3 = new Polynom(p1.multBehav);
        for (Map.Entry<Integer, Integer> term1 : p1.coef.entrySet()) {
            for (Map.Entry<Integer, Integer> term2 : p2.coef.entrySet()) {
                int degree = (int) term1.getKey() + (int) term2.getKey();
                int coef = (int) term1.getValue() * (int) term2.getValue();
                if(p3.coef.containsKey(degree)){
                    coef += p3.coef.get(degree);
                    this.ops++;
                }
                this.ops++;
                p3.coef.put(degree, coef);
            }
        }
        return p3;
	}
} 