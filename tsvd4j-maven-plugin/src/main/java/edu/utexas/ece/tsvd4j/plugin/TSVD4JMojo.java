package edu.utexas.ece.tsvd4j.plugin;

import edu.utexas.ece.tsvd4j.utils.Level;
import edu.utexas.ece.tsvd4j.utils.Logger;

import java.nio.file.Paths;
import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.twdata.maven.mojoexecutor.MojoExecutor;

@Mojo(name = "tsvd4j", defaultPhase = LifecyclePhase.TEST, requiresDependencyResolution = ResolutionScope.TEST)
public class TSVD4JMojo extends AbstractTSVD4JMojo {

    private final static String VERSION = "0.1-SNAPSHOT";
    public static final String tsvd4jDir = ".tsvd4j";

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        super.execute();

        // Execute Surefire
        try {
            MojoExecutor.executeMojo(this.surefire, MojoExecutor.goal("test"), applyConfig((Xpp3Dom) this.surefire.getConfiguration()),
                MojoExecutor.executionEnvironment(this.mavenProject, this.mavenSession, this.pluginManager));
        } catch (MojoExecutionException mojoException) {
            Logger.getGlobal().log(Level.INFO, "Surefire failed when running tests");
        } catch (Throwable tr) {
            Logger.getGlobal().log(Level.SEVERE, "Some exception that is highly unexpected: ", tr);
            throw tr;
        }
    }

    protected Xpp3Dom applyConfig(Xpp3Dom configuration)  {
        new File(tsvd4jDir).mkdir();
        Xpp3Dom node = configuration;
        if (node == null) {
            node = new Xpp3Dom("configuration");
        }
        String localRepo = this.mavenSession.getSettings().getLocalRepository();
        String argLineToSet = "-javaagent:" + getPathToTSVD4JJar(localRepo);
        Xpp3Dom argLineNode = new Xpp3Dom("argLine");
        argLineNode.setValue(argLineToSet);
        node.addChild(argLineNode);

        Xpp3Dom propertiesNode = new Xpp3Dom("properties");
        Xpp3Dom propertyNode = new Xpp3Dom("property");
        Xpp3Dom nameNode = new Xpp3Dom("name");
        String nameToSet = "listener";
        nameNode.setValue(nameToSet);
        Xpp3Dom valueNode = new Xpp3Dom("value");
        String valueToSet = "edu.utexas.ece.tsvd4j.listener.TestListener";
        valueNode.setValue(valueToSet);
        propertyNode.addChild(nameNode);
        propertyNode.addChild(valueNode);
        propertiesNode.addChild(propertyNode);
        node.addChild(propertiesNode);

        Xpp3Dom additionalClasspathElementsNode = new Xpp3Dom("additionalClasspathElements");
        Xpp3Dom classpathElementNode = new Xpp3Dom("additionalClasspathElement");
        String classPathJarToSet = getPathToTSVD4JJar(localRepo);
        classpathElementNode.setValue(classPathJarToSet);
        additionalClasspathElementsNode.addChild(classpathElementNode);
        node.addChild(additionalClasspathElementsNode);

        this.mavenProject.getProperties().setProperty("argLine", argLineToSet);

        return node;
    }

    private String getPathToTSVD4JJar(String localRepo) {
        String result = Paths.get(localRepo, "edu", "utexas", "ece", "tsvd4j-core", VERSION, "tsvd4j-core-" + VERSION + ".jar").toString();
        Logger.getGlobal().log(Level.FINE, "The TSVD4J path is: " + result);
        return "\"" + result + "\"";
    }
}
