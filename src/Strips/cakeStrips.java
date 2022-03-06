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

import pmatch.*;
//import sheffield.*;
import java.io.*;
import java.util.*;

public class cakeStrips {
  public static void main(String[] args) {

    // create the operators

    StripsOperator open = new StripsOperator("open door from ?r1 to ?r2", "door open ?r1 ?r2", "door closed ?r1 ?r2",
        "Robbie in ?r1|door closed ?r1 ?r2");
    StripsOperator closed = new StripsOperator("close door from ?r1 to ?r2", "door closed ?r1 ?r2", "door open ?r1 ?r2",
        "Robbie in ?r1|door open ?r1 ?r2");
    StripsOperator move = new StripsOperator ("move from ?r1 to ?r2", "Robbie in ?r2", "Robbie in ?r1",
        "Robbie in ?r1|door open ?r1 ?r2");
    StripsOperator carry = new StripsOperator("carry ?obj from ?r1 to ?r2", "Robbie in ?r2|?obj in ?r2",
        "?obj in ?r1|Robbie in ?r1", "?obj in ?r1|Robbie in ?r1|door open ?r2 ?r1");

    // form them into a vector
    ArrayList<StripsOperator> cakeOperators = new ArrayList<StripsOperator>();
    cakeOperators.add(open);
    cakeOperators.add(closed);
    cakeOperators.add(move);
    cakeOperators.add(carry);

    // create instance of StripsDiffFinder, give it the operators, init state & goal state
    StripsDiffFinder str = new StripsDiffFinder(cakeOperators,
        new MStringVector("Robbie in living_room|cake in kitchen|door closed living_room kitchen"),
        new MStringVector("cake in living_room"));

    // run Strips
    boolean res = str.run();
    System.out.println("Result is " + res); // result
    System.out.println("Plan is   " + str.getPlan()); // plan

  }
}
