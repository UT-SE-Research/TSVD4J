package edu.utexas.ece.tsvd4j.plugin;

import edu.utexas.ece.tsvd4j.utils.Level;
import edu.utexas.ece.tsvd4j.utils.Logger;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.model.Plugin;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.BuildPluginManager;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

@Execute(phase = LifecyclePhase.TEST_COMPILE)
public abstract class AbstractTSVD4JMojo extends AbstractMojo {

    /**
     * Same as the levels defined in java.util.logging.Level.
     */
    @Parameter(property = "tsvd4jLogging",
            defaultValue = "CONFIG")
    protected String loggingLevel;

    // Generic properties
    @Parameter(property = "project")
    protected MavenProject mavenProject;
    @Parameter(defaultValue = "${project.build.directory}")
    protected String projectBuildDir;
    @Parameter(defaultValue = "${basedir}")
    protected File baseDir;
    @Parameter(property = "goal", alias = "mojo")
    protected String goal;
    @Component
    protected MavenSession mavenSession;
    @Component
    protected BuildPluginManager pluginManager;

    protected Plugin surefire;
    protected String originalArgLine;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        Logger.getGlobal().setLoggingLevel(Level.parse(this.loggingLevel));

        this.surefire = this.lookupPlugin("org.apache.maven.plugins:maven-surefire-plugin");

        if (this.surefire == null) {
            Logger.getGlobal().log(Level.SEVERE, "Make sure surefire is in your pom.xml");
        }

        Properties localProperties = this.mavenProject.getProperties();
        this.originalArgLine = localProperties.getProperty("argLine", "");
    }

    private Plugin lookupPlugin(String paramString) {
        List<Plugin> localList = this.mavenProject.getBuildPlugins();
        Iterator<Plugin> localIterator = localList.iterator();
        while (localIterator.hasNext()) {
            Plugin localPlugin = localIterator.next();
            if (paramString.equalsIgnoreCase(localPlugin.getKey())) {
                return localPlugin;
            }
        }
        return null;
    }
}
