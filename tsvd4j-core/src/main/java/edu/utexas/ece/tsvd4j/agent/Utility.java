package edu.utexas.ece.tsvd4j.agent;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Utility {

    public static Map<InterceptionPoint, InterceptionPoint> resultInterception = new HashMap<>();

    private static Map<Integer, LinkedList<InterceptionPoint>> globalTrapList = new HashMap<>();   // All the current traps, mapping from object hashcode to points using the object
    private static Map<Integer, LinkedList<InterceptionPoint>> lastInterceptionPointForOBJ = new HashMap<>();
    private static int windowSize = 5;
    private static double delayProbability = 1;
    private static double decayFactor = 0.1;
    private static int planDistance = 10000000;
    private static int lastTPWindow = 5;
    private static double DelayProbabilityAtDangerousInterceptionPoint = 0.99;
    private static List<String> dangerousTPPairs = new ArrayList<String>();
    private static HashMap<String, Integer> hitTime = new HashMap<String, Integer>();
    private static List<String> blacklist = new ArrayList<String>();
    private static HashMap<String, InterceptionPoint> perThreadBlockedTP = new HashMap<>();
    private static HashMap<String, InterceptionPoint> perThreadLastTP = new HashMap<>();
    private static LinkedList<InterceptionPoint> globalTPHistory = new LinkedList<>();
    private static int historyWindowSize = 32;
    private static HashMap<String, Integer> perThreadTPCount = new HashMap<>();
    private static int inferSize = 5;
    private static boolean isTrapActive;
    private static int delayPerDangerousInterceptionPoint = 100;
    private static double inferLimit = 0.5;

    // General delay method
    // TODO: Make it configurable amount of delay (currently constant 100ms)
    public static void delay() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("Exception!");
            e.printStackTrace();
        }
    }

    // Sets trap at specified point
    public static synchronized void setTrap(InterceptionPoint interception) {
        int objId = interception.getObjectId();

        // Get the current list of traps mapped to this same object, if it exists
        LinkedList<InterceptionPoint> threadOpList;
        if (!globalTrapList.containsKey(objId)){
            threadOpList = new LinkedList<>();
            globalTrapList.put(objId, threadOpList);
        }

        // Add the trap to the list for keeping track; globalTrapList contains objectId corresponding to interceptionPointList
        threadOpList = globalTrapList.get(objId);
        threadOpList.add(interception);
        isTrapActive = true;    // Some trap is now active
    }

    // Clears the trap, since it is finished
    // TODO: Double-check that "trap" and "InterceptionPoint" should mean the same thing
    public static synchronized void clearTrap(InterceptionPoint interception) {
        int obj_id = interception.getObjectId();

        // Clear the trap if it exists
        // TODO: Is it guaranteed that the object must be tracked in the current global trap mapping?
        if (globalTrapList.containsKey(obj_id)) {
            globalTrapList.remove(obj_id);  // TODO: Is it supposed to remove all entries mapped to the same object when you want to clear?
            interception.setDelayCredit(inferSize);
            for (Map.Entry<String, InterceptionPoint> x : perThreadLastTP.entrySet()) {
                // Get the most recent previous interception point per thread
                String threadId = x.getKey();
                InterceptionPoint tp1 =  x.getValue();
                // These other interception points started running close to when this point that is about to be cleared (within (delayPerDangerousInterceptionPoint * inferLimit) time)
                if ((interception.getTimeMillis() + (delayPerDangerousInterceptionPoint * inferLimit)) > tp1.getTimeMillis()) {
                    // TODO: What is the goal of perThreadBlockedTP?
                    perThreadBlockedTP.put(threadId, interception);
                    //System.out.println("CLEAR TRAP +++++++++++++++++++++++&&&&&&&");
                 }
            }
            interception.setTrapped(true);  // TODO: What does the setTrapped configuration do for the sake of interception point?
            isTrapActive = false;
        }
    }

    // Searching for when there is a race
    public static synchronized void findRacingTP(InterceptionPoint interception) {
        List<InterceptionPoint> list = new ArrayList<InterceptionPoint>();  // TODO: Is this the list of all races?
        int obj_id = interception.getObjectId();
        // Check last interception points to see if there could be a recent conflict
        if (lastInterceptionPointForOBJ.containsKey(obj_id)) {
            //System.out.println("Within 1st IF FindRacingTP");
            LinkedList<InterceptionPoint> qList = lastInterceptionPointForOBJ.get(obj_id);
            int listSize = qList.size();
            for (InterceptionPoint thOpEntity : qList) {
                String threadId = thOpEntity.getThreadId();
                Operation opId = thOpEntity.getOpId();
                // If multiple interception points have different threads and one of them is a write, then potential race
                if ((threadId != interception.getThreadId()) && (opId == Operation.WRITE || interception.getOpId() == Operation.WRITE)) {
                    long existingTime = thOpEntity.getTimeMillis();
                    // Only a race if the new interception point comes in within planDistance of this previous interception point
                    if ((existingTime + planDistance) > interception.getTimeMillis()) {
                        // Also only a race if there is more than one active thread for any of these interception points
                        if ((thOpEntity.getActiveThdNum() > 1) || (interception.getActiveThdNum() > 1)) {
                            list.add(thOpEntity);
                        }
                    }
                }
            }
            // Record this interception point into the list of tracked previous ones
            qList.add(interception);
            // If now there are too many entries, remove the first one
            if (qList.size() > lastTPWindow) {
                qList.removeFirst();
            }
        } else {
            // System.out.println("Within ELSE FindRacingTP");
            LinkedList<InterceptionPoint> threadOpList = new LinkedList<>();
            threadOpList.add(interception);
            lastInterceptionPointForOBJ.put(obj_id, threadOpList);
        }

        // Record racing pairs by matching this interception point with previously identified points that are close to each other
        for (InterceptionPoint tp2 : list) {
            String st = interception.toString() + " ! " + tp2.toString();
            String st2 = tp2.toString() + " ! " + interception.toString();
            String shortst = getPairID(st, st2);
            String pairname = getPairID(interception.getLocation(), tp2.getLocation());
            // No need to record pair if the locations have been hit more than 10 times total and already have recorded this pair
            if (hitTime.containsKey(interception.getLocation()) && hitTime.containsKey(tp2.getLocation()) && (hitTime.get(interception.getLocation()) + hitTime.get(tp2.getLocation()) >= 10) && dangerousTPPairs.contains(shortst)){
                continue;
            }
            // The hitTime is how many times this dangerous pair has caused trap; it is used for decay
            hitTime.put(interception.getLocation(), 0);
            hitTime.put(tp2.getLocation(), 0);
            dangerousTPPairs.add(shortst);
        }
    }

    private static String getPairID(String s1, String s2) {
        return s1.compareTo(s2) > 0 ? s1 + " " + s2 : s2 + " " + s1;
    }

    // The main intention is to build perThreadLastTp, globalTPHistory, perThreadCount. In addition to this, active thread number is set by the perThreadTpCOunt for the coming interception point.
    private static synchronized void updateTPHistory(InterceptionPoint interception) {
        String thdid = interception.getThreadId();
        perThreadLastTP.put(thdid, interception);
        globalTPHistory.add(interception);
        // To keep track how many times a thread comes in
        if (perThreadTPCount.containsKey(thdid)) {
            int count = perThreadTPCount.get(thdid);
            count++;
            perThreadTPCount.put(thdid, count);
        } else {
            perThreadTPCount.put(thdid, 1);
        }

        // Only keep track of most recent historyWindowSize interception points
        if (globalTPHistory.size() > historyWindowSize) {
            InterceptionPoint tp = globalTPHistory.removeFirst();
            int count = perThreadTPCount.get(tp.getThreadId());
            count--;
            perThreadTPCount.put(tp.getThreadId(), count);
            if (count == 0) {
                perThreadTPCount.remove(tp.getThreadId());
            }
        }
        // Record for this interception point how many threads are considered active at this moment
        interception.setActiveThdNum(perThreadTPCount.size());
    }

    // For a given interception point check if it triggers a trap with existing interception points
    // This method figures out if there is a bug if multiple writes across different threads are happening on the same object
    public static synchronized boolean checkForTrap(InterceptionPoint interception) {
        boolean bugFound = false;
        if (globalTrapList.isEmpty()){
           // System.out.println("*** Global Map is Empty From check_for_trap" );
        } else {
            int objId = interception.getObjectId();
            String threadId = interception.getThreadId();
            Operation opId = interception.getOpId();
            if (globalTrapList.containsKey(objId)) {
                //System.out.println("************************************ From check_for_trap****" );
                LinkedList<InterceptionPoint> interceptionPointList = globalTrapList.get(objId);
                for (InterceptionPoint interception2 : interceptionPointList) {
                    String threadId2 = interception2.getThreadId();
                    Operation opId2 = interception2.getOpId();
                    // System.out.println(" From Check For Trap threadId = " + threadId + " " + " thread id = "+thread_id +"opId= " + opId  +  " op_id "+ op_id + " Write " + Operation.WRITE );
                    if ((threadId != threadId2) && (opId == Operation.WRITE || opId2 == Operation.WRITE)) {
                        //System.out.println("======>Lock is needed" );
                        bugFound = true;
                        resultInterception.put(interception, interception2);    // Store found pair of points that are racing
                    } else {
                        System.out.println("======> Global Table will be updated " );
                    }
                }
            }
        }
        return bugFound;
    }

    // Determine whether we should be delaying at this interception point
    public static synchronized boolean shouldDelay(InterceptionPoint interception) {
        boolean ret = false;
        double prob = 1.0;
        int sleepDuration = 0;
        int danger = -1;
        int objId = interception.getObjectId();

        //System.out.println("==================***Danger Initial = " +danger);
        // Check if this interception point overlaps with another previous one on same object that is in some dangerous location
        if (globalTrapList.containsKey(objId)) {
            LinkedList<InterceptionPoint> thOpList = globalTrapList.get(objId);
            //for (int i=0; i<thOpList.size(); i++){
            for (InterceptionPoint thOpEntity : thOpList) {
                //InterceptionPoint thOpEntity = thOpList.get(i);
                String location = thOpEntity.getLocation();
                danger = isInsideDangerList(location);
                //System.out.println("==================***Danger = " +danger);
            }
        }

        // Delay if no trap active or in danger location, and this interception is a write
        if ((danger >= 0 || !isTrapActive) && (interception.getOpId() == Operation.WRITE)) {
            // TODO: What is trapPlan?
            /* if (trapPlan != null) {
                prob = trapPlan.delayProbability;
            }
            else {
                prob = this.delayProbability;
            }*/

            Random rand = new Random();

            // Compute probability of needing to delay based on danger score
            if (danger >= 0) {
                prob = DelayProbabilityAtDangerousInterceptionPoint - (decayFactor * danger);
            }
            if (rand.nextDouble() <= prob) {
                ret = true;
            }
        }

        //System.out.println("%%%%%%%%%%%Should Delay is =  "+ret);
        return ret;
    }

    private static synchronized void removeDependentInterceptionPoints(InterceptionPoint tp) {
        // Check whether there is a previous interception point for this same thread ID (in perThreadBlockedTP)
        // Goal is to remove dangerous pairs based off of the perThreadBlockedTP
        // TODO: What is the exact meaning behind perThreadBlockedTP?
        if (perThreadBlockedTP.containsKey(tp.getThreadId())) {
            // find the torchpint that blocked this thread if exists.
            InterceptionPoint tp2 = perThreadBlockedTP.get(tp.getThreadId());
            //System.out.println("Within RemoveDependentInterception +++++++++++++++++++++++");
            // delaycredit is k means a InterceptionPoint block the next-k operation in other thread
            int valDelayCredit = tp2.getDelayCredit();
            // Remove as dangerous pair if there is still credit remaining, allowance for number of times found to be close to each other
            if (valDelayCredit > 0) {
                removeDangerItem(tp.getLocation(), tp2.getLocation());
                // DebugLog.Log("HB Order " + tp.Tostring() + " " + tp2.Tostring());
                valDelayCredit--;
                tp2.setDelayCredit(valDelayCredit);
             } else {
                perThreadBlockedTP.remove(tp.getThreadId());
             }
        }
    }

    private static synchronized void removeDangerItem(String tpstr1, String tpstr2) {
        String st = getPairID(tpstr1, tpstr2);
        dangerousTPPairs.remove(st);
    }

    private static synchronized int isInsideDangerList(String tpstr) {
        // Considered danger if there is a hitTime entry for this location
        if (hitTime.containsKey(tpstr)) {
            int count = hitTime.get(tpstr) + 1;
            hitTime.put(tpstr, count);
            return count;
        }
        return -1;
    }

    // This is the big entry point, is called when we encounter a relevant location being executed
    public static void onCall(InterceptionPoint interception) {
        isTrapActive = false;   // TODO: This variable is used in synchronized methods but this is a non-synchronized method
        updateTPHistory(interception);
        findRacingTP(interception);
        //System.out.println("After FindRacingTP");
        removeDependentInterceptionPoints(interception);
        //System.out.println("After RemoveDependentInterception");
        boolean bugFound = checkForTrap(interception);
        if (!bugFound && shouldDelay(interception)) {
            //System.out.println("Should Delay");
            setTrap(interception);
            delay();
            clearTrap(interception);
        }
    }
}
