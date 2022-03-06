package Strips;
/**
 * warriorStrips.java
 * 2020.5.28
 * STRIPS for warrior problem
 * @author Ruiqing Xu
 */

import pmatch.MStringVector;

import java.util.ArrayList;

public class warriorStrips {
  public static void main(String[] args) {

    //no-snake warriorStrips operator
    StripsOperator move = new StripsOperator("move from W to L", "Warrior at L", "Warrior at W",
            "Warrior at W");
    StripsOperator carry = new StripsOperator("carry ?obj1 to P", "?obj1 at P", "?obj1 at L",
            "Warrior at L|?obj1 at L");
    StripsOperator climbDown = new StripsOperator("climb down from to P", "Warrior at P", "Warrior at L",
            "?obj1 at P|Warrior at P");
    StripsOperator liftUp = new StripsOperator("lift up from P", "?obj2 at L|Warrior at L", "?obj2 at T|Warrior at P",
            "?obj2 at T|Warrior at P|?obj1 at P");

    /*
    //snake warriorStrips operator
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
     */


    ArrayList<StripsOperator> warriorOperators = new ArrayList<StripsOperator>();

    //no-snake warriorStrips
    warriorOperators.add(move);
    warriorOperators.add(carry);
    warriorOperators.add(climbDown);
    warriorOperators.add(liftUp);

    /*
    //snake warriorStrips
    warriorOperators.add(move);
    warriorOperators.add(carry1);
    warriorOperators.add(attach);
    warriorOperators.add(carry2);
    warriorOperators.add(lift);
     */

    // create instance of StripsDiffFinder, give it the operators, init state & goal state
    StripsDiffFinder str = new StripsDiffFinder(warriorOperators,

        //no-snake init state & goal state
        new MStringVector("Warrior at W|Ladder at L|TreasureChest at T"),
        new MStringVector("Ladder at P|Warrior at L|TreasureChest at L"));

        /*
        //snake init state & goal state
        new MStringVector("Warrior at W|Hook at H|Rope at R|TreasureChest at T"),
        new MStringVector("Warrior at R|Hook at R|Rope at R|TreasureChest at R"));
         */



    // run Strips
    boolean res = str.run();
    System.out.println("Result is " + res); // result
    System.out.println("Plan is   " + str.getPlan()); // plan

  }
}
