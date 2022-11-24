package edu.utexas.ece.tsvd4j.listener;

import edu.utexas.ece.tsvd4j.agent.InterceptionPoint;
import edu.utexas.ece.tsvd4j.agent.Utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class TestListener extends RunListener {

    public static List<String> conflictingListPair = new ArrayList();

    @Override
    public void testStarted(Description description) throws Exception {
        String curDir = new File("").getAbsolutePath();
        writeTo(Paths.get(curDir, ".tsvd4j", "listener.log").toString(),
            "Test started: " + description.getClassName() + "." + description.getMethodName() + "\n");
    }

    public void writeTo(final String outputPath, String output) {
        if (!Files.exists(Paths.get(outputPath))) {
            try {
                Files.createFile(Paths.get(outputPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            Files.write(Paths.get(outputPath), output.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    @Override
    public void testFailure(Failure failure) throws Exception {
        failure.getException().printStackTrace();
    }

    @Override
    public void testAssumptionFailure(Failure failure) {
        failure.getException().printStackTrace();
    }

    @Override
    public void testFinished(Description description) throws Exception {
        String curDir = new File("").getAbsolutePath();
        writeTo(Paths.get(curDir, ".tsvd4j", "listener.log").toString(),
            "Test finished: " + description.getClassName() + "." + description.getMethodName() + "\n");
        String testName = description.getClassName() + "." + description.getMethodName();

        for (Map.Entry<InterceptionPoint, InterceptionPoint> entry : Utility.resultInterception.entrySet()) {
            String conflictingPair = entry.getKey() + ":" + entry.getValue();
            if (!conflictingListPair.contains(conflictingPair)) {
                conflictingListPair.add(conflictingPair);
                writeTo(Paths.get(curDir, ".tsvd4j", "listener.log").toString(), conflictingPair + "\n");
                writeTo(Paths.get(curDir, ".tsvd4j", testName).toString(), conflictingPair + "\n");
            }
        }
    }
}
