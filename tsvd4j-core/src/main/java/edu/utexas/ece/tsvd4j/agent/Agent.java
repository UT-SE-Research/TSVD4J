package edu.utexas.ece.tsvd4j.agent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class Agent {

    public static boolean blackListContains(String s) {
        final String[] blackList = new String[]{"java.", "javax.", "sun.", "com.sun.", "jdk.", "org.xml.sax.", "net.bytebuddy.", "org.mockito.", "org.omg.CORBA.", "org.omg.CORBA_2_3.", "org.omg.CosNaming.", "org.omg.SendingContext.", "org.omg.stub.java.rmi.", "org.objenesis.", "org.mockito.", "com.google.", "org.apache.maven.", "org.apache.logging.", "org.eclipse.jetty.", "org.slf4j.", "org.junit.", "org.hamcrest.", "junit.", "sunw.io.", "sunw.util.", "edu.utexas.ece.tsvd4j."};

        for (int i = 0; i < blackList.length; ++i) {
            String libPackage = blackList[i];
            if (s.startsWith(libPackage)) {
                return true;
            }
        }
        return false;
    }

    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader classLoader, String s, Class<?> aClass,
                    ProtectionDomain protectionDomain, byte[] bytes) throws IllegalClassFormatException {
                s = s.replaceAll("[/]","."); 
                if (!blackListContains(s)) {
                    final ClassReader reader = new ClassReader(bytes);
                    final ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES|ClassWriter.COMPUTE_MAXS );
                    ClassTracer visitor = new ClassTracer(writer);
                    reader.accept(visitor, 0);
                    return writer.toByteArray();
                 }
            return null;
            }
        });
        printStartStopTimes();
    }

    private static void writeTo(final String outputPath, String output) {
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

    private static void printStartStopTimes() {
        final long start = System.currentTimeMillis();
        Thread hook = new Thread() {
            @Override
            public void run() {
                List<String> conflictingListPair = new ArrayList();
                long timePassed = System.currentTimeMillis() - start;
                System.err.println("Stop at ............................. " );
                BufferedWriter bf = null;

                String curDir = new File("").getAbsolutePath();

                for (Map.Entry<InterceptionPoint, InterceptionPoint > entry : Utility.resultInterception.entrySet()){
                    String conflictingPair = entry.getKey() + ":" + entry.getValue();
                    if (!conflictingListPair.contains(conflictingPair)) {
                        conflictingListPair.add(conflictingPair);
                		System.out.println("Conflicting pairs found: " + conflictingPair);
                    	writeTo(curDir + "/.tsvd4j/Conflicting-Pairs.txt", conflictingPair + "\n");
		            }
                }

                int size = conflictingListPair.size();
                System.out.println(" Total # Conflicting items are = " + size);
                //System.out.println(conflictingListPair);
                int totalThreadCount = Helper.threadCountList.size();
                System.out.println(" Total # Thread Count = " + totalThreadCount);
                //System.out.println(Helper.threadCountList);
            }
        };
        Runtime.getRuntime().addShutdownHook(hook);
    }
}
