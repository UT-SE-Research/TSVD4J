package edu.utexas.ece.tsvd4j.plugin;
import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import java.nio.file.Paths;

/**
 * Removes STARTS plugin artifacts.
 */

@Mojo(name = "clean", requiresDirectInvocation = true)
public class CleanMojo extends AbstractTSVD4JMojo {
    
    @Override
    public void execute() throws MojoExecutionException {
        String curDir = new File("").getAbsolutePath();
        String artifactDir = Paths.get(curDir, ".tsvd4j").toString(); //basedir.getAbsolutePath() + File.separator + ".tsvd4j";
        File directory = new File(artifactDir);
        if (directory.isDirectory()) {
              for (File childFile : directory.listFiles()) {
                childFile.delete();
            }
        }
        directory.delete();
    }
}
