package polynommult;

import java.util.TreeMap;
import java.util.Map;
import java.util.Random;

public class Polynom{
    private static final int MAX_ORDE = 5;
    private static final int MAX_COEF = 10;
	protected TreeMap<Integer, Integer> coef;
    public MultBehav multBehav;

	public Polynom(MultBehav multBehav) {
		this.coef = new TreeMap<Integer, Integer>();
		this.multBehav = multBehav;
	}

	public Polynom multiply(Polynom other){
		return this.multBehav.multiply(this, other);
	}

    protected Polynom promote(int degree){
        Polynom p = new Polynom(this.multBehav);
        for (Map.Entry<Integer, Integer> term : this.coef.entrySet()) {
            p.coef.put((int)term.getKey() + degree, term.getValue());
        }
        return p;
    }

    public Polynom add(Polynom other){
        return this.doOperation("+", other);
    }

    public Polynom subtract(Polynom other){
        return this.doOperation("-", other);
    }

    private Polynom doOperation(String op, Polynom other){
        Polynom p = new Polynom(this.multBehav);
        // copy from this
        for(Map.Entry<Integer, Integer> term : this.coef.entrySet()){
            int degree = term.getKey();
            int coef = term.getValue();
            p.coef.put(degree, coef);
        }

        // do op from other
        for(Map.Entry<Integer, Integer> term : other.coef.entrySet()){
            int degree = term.getKey();
            int coef = 0;
            if(p.coef.containsKey(degree)){
                coef += p.coef.get(term.getKey());
            }
            switch(op){
                case "+":
                    coef += term.getValue();
                    break;
                case "-":
                    coef -= term.getValue();
                    break;
            }
            p.coef.put(degree, coef);
        }
        return p;
    }

    // generate random coef of polynom orde n
    public void generateRandomPolynom(int n){
        Random rand = new Random();
        for(; n > 0; n--){
            int coef = rand.nextInt(MAX_COEF-1)+1;
            this.setTerm(n-1, coef);
        }
    }

    private void setTerm(int degree, int coef){
        this.coef.put(degree, coef);
    }

    public void print(){
        for (Map.Entry term : this.coef.entrySet()) {
          if((int) term.getValue() != 0){
              System.out.print(term.getValue());
              if((int) term.getKey() != 0){
                  System.out.print("x"+term.getKey());
              }
              System.out.print(" + ");
          }
        }
        System.out.println();
    }
}
