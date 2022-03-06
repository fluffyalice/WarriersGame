package Strips;
/**
 * StripsDiffFinder.java
 * find difference, call strips2
 * if that succeeds, call strips1 to deal with any remaining diffs
 * Created: Sun Jan 14 21:06:02 2001
 *
 * Phil Green
 * 2013 Version
 * 
 * 2020 Heidi Christensen (heidi.christensen@sheffield.ac.uk)
 */

import java.util.*;
import pmatch.*;

public class StripsDiffFinder extends StripsFn {

  // constructor called internally, passed opLis directly

  public StripsDiffFinder(ArrayList<StripsOperator> opl, MStringVector is, MStringVector gs) {
    operatorList = opl;
    initState = is;
    goalState = gs;
  }

  // run problem solver

  public boolean run() {
    // commentary
    System.out.println("-----------------");
    System.out.println("Strips1");
    System.out.println("currrent state " + initState.toString());
    System.out.println("goal state " + goalState.toString());

    // look for a difference
    boolean diffound = false; // set to true when diff found
    String g = new String(); // where the diff found will go
    Iterator git = goalState.getV().iterator();
    while (git.hasNext() && !diffound) { // iterate over goals
      MString gnext = (MString) git.next();
      g = gnext.getStr();
      // try to match goal g within state - initState is an MStringVector
      diffound = !initState.match(g);
    }

    if (!diffound) {
      result = true; // no difference found - trivial success
      newState = initState; // end state same as initState
      System.out.println("all goals met");
      System.out.println("-----------------");
      plan = new ArrayList<String>(); // empty plan
      return result;
    } else { // found a diff, use Strips2
      System.out.println("working on goal " + g);
      StripsOperatorFinder stripsOperatorFinder = new StripsOperatorFinder(operatorList, initState, goalState, g); // make
                                                                                                                   // instance
      boolean sOperatorRes = stripsOperatorFinder.run(); // run it
      if (!sOperatorRes) { // did StripsOperatorFinder succeed?
        result = false; // no, failure
        return result;
      } else { // stripsFindOperator succeeded - call StripsDiffFinder again with state returned
        // must be a new instance - state will have changed
        StripsDiffFinder newStripsDiff = new StripsDiffFinder(operatorList, stripsOperatorFinder.getNewState(), goalState);
        boolean nDiffRes = newStripsDiff.run();
        if (!nDiffRes) { // did strips1 succeed?
          result = false; // no, failure
          return result;
        } else { // success
          result = true;
          // plan is stripsOperatorFinder plan + stripsDiffFinder plan
          plan = stripsOperatorFinder.getPlan();
          plan.addAll(newStripsDiff.getPlan());
          newState = newStripsDiff.getNewState();// final state is that returned by strips1
          return result;
        }
      }
    }
  }
}
