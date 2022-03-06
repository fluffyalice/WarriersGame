package Strips;
/**
 * StripsOperatorFinder java
 * in Strips, given a difference
 * looks for an operator to deal with it
 * calls StripsApplyOperator to apply the op
 *
 * Created: Sun Jan 14 21:58:54 2001
 *
 * @author Phil Green
 * 2013 Version
 * 
 * 
 * 2020 Heidi Christensen (heidi.christensen@sheffield.ac.uk)
 */

import java.util.*;
import pmatch.*;

public class StripsOperatorFinder extends StripsFn {

  String diff; // difference to reduce

  // constructor
  public StripsOperatorFinder(ArrayList<StripsOperator> opl, MStringVector is, MStringVector gs, String d) {
    operatorList = opl;
    initState = is;
    goalState = gs;
    diff = d;
  }

  // run problem solver

  public boolean run() {
    // commentary
    System.out.println("------------------");
    System.out.println("StripsOperatorFinder");
    System.out.println("working on " + diff);

    result = false; // set to true when diff dealt with
    Iterator opit = operatorList.iterator();

    while (opit.hasNext() && !result) { // try ops till one succeeds or none left
      StripsOperator op = (StripsOperator) opit.next();

      boolean matchres = op.getAddList().match(diff); // match diff against addlist
      if (matchres) { // op can deal with diff
        HashMap con = op.getAddList().getContext(); // in this context
        System.out.println("calling StripsApplyOperator to apply operator " + op.getActList());

        // call strips3 to attempt to apply op
        StripsApplyOperator stripsApplyOperator = new StripsApplyOperator(operatorList, initState, goalState, op, con);
        System.out.println("back from Strips3");
        boolean operatorApplied = stripsApplyOperator.run();
        if (operatorApplied) { // stripsApplyOperator succeeded
          plan = stripsApplyOperator.getPlan(); // StripsOperatorFind plan is StripsApplyOperator plan
          newState = stripsApplyOperator.getNewState(); // new state is StripsApplyOperator new state
          result = true;
        }
      }
    }
    return result;
  }
}
