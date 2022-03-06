package Strips;
/**
 * cakeStrips.java
 * Strips on the cake problem
 *
 * Created: Jan 2005
 * STRIPS for the cake problem
 * @author pdg
 * 2013 Version
 * 
 * 2020 - Heidi Christensen (heidi.christensen@sheffield.ac.uk)
 * 
 */

import pmatch.MStringVector;

import java.util.ArrayList;

//import sheffield.*;

public class warriorStrips2 {
  public static void main(String[] args) {

    StripsOperator move = new StripsOperator("move from W to H", "Warrior at H", "Warrior at W",
            "Warrior at W");
    StripsOperator carry1 = new StripsOperator("carry1 Hook from H to R", "Warrior at R|?obj1 at R", "?obj1 at H|Warrior at H",
            "?obj1 at H|Warrior at H");
    StripsOperator attach = new StripsOperator("attach Hook and Rope at R", "", "",
            "?obj1 at R|Warrior at R|?obj2 at R");
    StripsOperator carry2 = new StripsOperator("carry2 Hook and Rope to P", "?obj2 at P|?obj1 at P", "?obj2 at R|?obj1 at R",
            "?obj2 at R|Warrior at R|?obj1 at R");
    StripsOperator lift = new StripsOperator("lift TreasureChest to R", "?obj3 at R|?obj2 at R|?obj1 at R", "?obj2 at P|?obj1 at P",
            "?obj3 at T|?obj2 at P|?obj1 at P|Warrior at R");


    // form them into a vector
    ArrayList<StripsOperator> cakeOperators = new ArrayList<StripsOperator>();

    cakeOperators.add(move);
    cakeOperators.add(carry1);
    cakeOperators.add(attach);
    cakeOperators.add(carry2);
    cakeOperators.add(lift);
    // create instance of StripsDiffFinder, give it the operators, init state & goal state
    StripsDiffFinder str = new StripsDiffFinder(cakeOperators,
        new MStringVector("Warrior at W|Hook at H|Rope at R|TreasureChest at T"),
        new MStringVector("Warrior at R|Hook at R|Rope at R|TreasureChest at R"));

    // run Strips
    boolean res = str.run();
    System.out.println("Result is " + res); // result
    System.out.println("Plan is   " + str.getPlan()); // plan

  }
}
