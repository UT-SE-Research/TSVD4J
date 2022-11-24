package edu.utexas.ece.tsvd4j.agent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Helper {

    public static Map<String, Integer> threadCountInSubjectProject = new ConcurrentHashMap<>();
    public static List<String> threadCountList = new ArrayList();

    public static InterceptionPoint createInstance(Object obj, String className, String methodName, int lineNumber, Operation op) {
        long opTime = System.currentTimeMillis();
        int hashCode = System.identityHashCode(obj);

        long currentThreadId = Thread.currentThread().getId();
        String uniqueThread= String.valueOf(currentThreadId) + Thread.currentThread().getName();
        if (!threadCountList.contains(currentThreadId)) {
            threadCountList.add(uniqueThread);
        }

        InterceptionPoint interception = new InterceptionPoint(hashCode, uniqueThread, className, methodName, lineNumber, op, opTime);
        return interception;
    }

    public static InterceptionPoint createInstance(Object obj, String fieldName, String className, String methodName, int lineNumber, Operation op) {
        long opTime = System.currentTimeMillis();
        Pair pair = new Pair(obj, fieldName);
        int hashCode = pair.hashCode();

        long currentThreadId = Thread.currentThread().getId();
        String uniqueThread= String.valueOf(currentThreadId) + Thread.currentThread().getName();
        if (!threadCountList.contains(currentThreadId)) {
            threadCountList.add(uniqueThread);
        }

        InterceptionPoint interception = new InterceptionPoint(hashCode, uniqueThread, className, methodName, lineNumber, op, opTime);
        return interception;
    }
}
