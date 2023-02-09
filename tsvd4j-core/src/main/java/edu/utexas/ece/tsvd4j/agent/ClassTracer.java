package edu.utexas.ece.tsvd4j.agent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.TypePath;

public class ClassTracer extends ClassVisitor {

    private String cn;
    int lineNumber;

    static BufferedReader reader;
    static List<String> listAPI;
    static List<String> listVolatile;
    static int field_status=1;
    static int api_status=1;

    public ClassTracer(ClassVisitor cv) {
         super(Opcodes.ASM9, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        this.cn = name;
        super.visit(version, access, name, signature, superName, interfaces);
    }

    public static void loadFile() {
        if(System.getProperty("field") != null) {
            field_status=1;
            api_status = 0;
         } 
         else if(System.getProperty("api") != null) {
              api_status = 1;
              field_status = 0;
          }
        try {

            listAPI = new ArrayList<String>();
            // get the file url, not working in JAR file.
            ClassLoader classloader = ClassTracer.class.getClassLoader();
            InputStream is = classloader.getResourceAsStream("API.txt");
            if (is == null) {
                System.out.println("file not found");
            } else {
                // failed if files have whitespaces or special characters
                InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
                reader = new BufferedReader(streamReader);
                String line = reader.readLine();
                while (line != null) {
                    line = reader.readLine();
                    listAPI.add(line);
                }
                reader.close();
            }
        } catch (IOException e) {
              e.printStackTrace();
        }
    }

    static {
        listVolatile = new ArrayList<String>();
        loadFile();
    }
		
    @Override
    public FieldVisitor visitField(int access, java.lang.String name, java.lang.String descriptor, java.lang.String signature, java.lang.Object value) {
        if ((access & Opcodes.ACC_VOLATILE) != 0) {
            //System.out.println("Got volatile opcode="+ access  +"," + name + ", " + descriptor + ","+signature + ","+value);
            listVolatile.add(name);
        }
        return super.visitField(access, name, descriptor, signature,value);
   	} 

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        final String methodId = this.cn + "." + name;
        final String methodName = name;

        return new MethodVisitor(Opcodes.ASM9, super.visitMethod(access, name, desc, signature, exceptions)) {
            @Override
            public void visitLineNumber(int line, Label start) {
                lineNumber = line;
                super.visitLineNumber(line, start);
            }

            @Override
            public void visitFieldInsn(int opcode, java.lang.String owner, java.lang.String name, java.lang.String descriptor) {

                if(field_status == 1) {
                    // Ignore fields of java/lang/System
                    if (! listVolatile.contains(name)) {
                        if (!"java/lang/System".equals(owner)) {
                            if (opcode == Opcodes.PUTSTATIC || opcode == Opcodes.GETSTATIC) {
                                super.visitLdcInsn(name + owner);
                                super.visitLdcInsn(lineNumber);
                                super.visitLdcInsn(cn);
                                String proxyMethodSignature = "(Ljava/lang/String;"+"ILjava/lang/String;)V";
                                if (opcode == Opcodes.PUTSTATIC) {
                                    super.visitMethodInsn(Opcodes.INVOKESTATIC, "edu/utexas/ece/tsvd4j/agent/Proxy", "setVariables", proxyMethodSignature, false);
                                } else {
                                    super.visitMethodInsn(Opcodes.INVOKESTATIC, "edu/utexas/ece/tsvd4j/agent/Proxy", "readVariables", proxyMethodSignature, false);
                                }
                            // Only track instance field accesses in non-constructor
                            } else if (opcode == Opcodes.GETFIELD && (!"<init>".equals(methodName))) {
                                super.visitInsn(Opcodes.DUP);
                                super.visitLdcInsn(name + owner);
                                super.visitLdcInsn(lineNumber);
                                super.visitLdcInsn(cn);
                                String methodDescriptor = Type.getMethodDescriptor(Type.VOID_TYPE, new Type[] { Type.getMethodType(descriptor) });
                                String proxyMethodSignature = "(Ljava/lang/Object;Ljava/lang/String;ILjava/lang/String;)V";
                                super.visitMethodInsn(Opcodes.INVOKESTATIC, "edu/utexas/ece/tsvd4j/agent/Proxy", "getFieldVariables", proxyMethodSignature, false);
                            // Only track instance field accesses in non-constructor
                            } else if (opcode == Opcodes.PUTFIELD && (!"<init>".equals(methodName))) {
                                String proxyMethodSignature = "(Ljava/lang/Object;Ljava/lang/String;ILjava/lang/String;)V";
                                // Deal with multiple slots on top of stack if long or double
                                if (descriptor.equals("J") || descriptor.equals("D")) {
                                    super.visitInsn(Opcodes.DUP2_X1);
                                    super.visitInsn(Opcodes.POP2);
                                    super.visitInsn(Opcodes.DUP);
                                    super.visitLdcInsn(name + owner);
                                    super.visitLdcInsn(lineNumber);
                                    super.visitLdcInsn(cn);
                                    super.visitMethodInsn(Opcodes.INVOKESTATIC, "edu/utexas/ece/tsvd4j/agent/Proxy", "putFieldVariables", proxyMethodSignature, false);
                                    super.visitInsn(Opcodes.DUP_X2);
                                    super.visitInsn(Opcodes.POP);
                                } else {
                                    super.visitInsn(Opcodes.SWAP);
                                    super.visitInsn(Opcodes.DUP);
                                    super.visitLdcInsn(name + owner);
                                    super.visitLdcInsn(lineNumber);
                                    super.visitLdcInsn(cn);
                                    super.visitMethodInsn(Opcodes.INVOKESTATIC, "edu/utexas/ece/tsvd4j/agent/Proxy", "putFieldVariables", proxyMethodSignature, false);
                                    super.visitInsn(Opcodes.SWAP);
                                }
                            }
                        }
					}
                    super.visitFieldInsn(opcode, owner, name, descriptor);
                }
                else
                   super.visitFieldInsn(opcode, owner, name, descriptor);
            }
            @Override
            public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
                if(api_status == 1) {
                    String combined_name = owner + "/" + name + desc;
                    // Only track specific API
                    if (listAPI.contains(combined_name)) {
                        String methodParameter = combined_name.substring(combined_name.indexOf("(") + 1, combined_name.indexOf(")"));
                        String returnVal = combined_name.substring(combined_name.lastIndexOf(")") + 1);
                        super.visitLdcInsn(lineNumber);
                        super.visitLdcInsn(name);
                        super.visitLdcInsn(cn);
                        String proxyMethodSignature = "(L" + owner + ";" + methodParameter + "ILjava/lang/String;Ljava/lang/String;)" + returnVal;
                        // The proxy method also invokes the actual method, so no need to specifically call the actual method
                        super.visitMethodInsn(Opcodes.INVOKESTATIC, "edu/utexas/ece/tsvd4j/agent/Proxy", name, proxyMethodSignature, false);
                    } else {
                        super.visitMethodInsn(opcode, owner, name, desc, itf);
                    }
                }
                else
                   super.visitMethodInsn(opcode, owner, name, desc, itf);
            }
        };
    }
}
