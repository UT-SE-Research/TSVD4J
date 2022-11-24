package edu.utexas.ece.tsvd4j.agent;

public class InterceptionPoint {
    String threadId;
    Operation opId;
    String location;
    long opTime;
    int activeThdNum;
    int lineNumber;
    String className;
    String methodName;
    int delayCredit;
    int objId;
    boolean trapped;

    public InterceptionPoint(int objId, String threadId, String className, String methodName, int lineNumber, Operation opId, long opTime) {
        this.threadId = threadId;
        this.className = className;
        this.lineNumber = lineNumber;
        this.methodName = methodName;
        this.opId = opId;
        this.opTime = opTime;
        this.objId = objId;
    }

    public void setObjectId(int objId) {
        this.objId = objId;
    }

    public int getObjectId() {
        return this.objId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public String getThreadId() {
        return this.threadId;
    }

    public void setOpID(Operation Op) {
        this.opId = Op;
    }

    public Operation getOpId() {
        return this.opId;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return this.className;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return this.lineNumber;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public String getLocation() {
        return this.methodName + " " + this.className + " " + this.lineNumber;
    }

    public void setOpTime(long opTime) {
        this.opTime = opTime;
    }

    public long getOpTime() {
        return this.opTime;
    }

    public long getTimeMillis() {
        return this.opTime;
    }

    public int getActiveThdNum() {
        return this.activeThdNum;
    }

    public void setActiveThdNum(int activeThdNum) {
        this.activeThdNum = activeThdNum;
    }

    public void setDelayCredit(int delayCredit) {
        this.delayCredit = delayCredit;
    }

    public int getDelayCredit() {
        return this.delayCredit;
    }

    public void setTrapped(boolean trapped) {
        this.trapped = trapped;
    }

    public boolean getTrapped() {
        return this.trapped;
    }

    public String toString() {
        if (this.methodName == " " || this.methodName == "") {
            return this.className + "|" + this.lineNumber;
        } else {
            return this.className + "|" + this.methodName + "|" + this.lineNumber;
        }
    }
}
