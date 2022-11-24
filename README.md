# TSVD4J

TSVD4J implements the TSVD approach for Java projects. See
[here](https://github.com/microsoft/tsvd) for an implementation for C#
projects.

### Automatically setting up the pom.xml for TSVD4J

```shell
mvn clean install
```

```shell
cd script/
```

Run the following command to automatically setup the pom.xml.


```shell
bash pom-modify/modify-project.sh path_to_maven_project
```

For example, 

```shell
bash pom-modify/modify-project.sh Project/dataRace-Example/
```


### Manually setting up the pom.xml for TSVD4J

Copy the following plugin into the Maven project's pom.xml.
You do not need to perform this step if you have already completed the instructions
in [Automatically setting up the pom.xml for TSVD4J](#automatically-setting-up-the-pomxml-for-TSVD4J).

```xml
<build>
    ...
    <plugins>
        ...
        <plugin>
            <groupId>edu.utexas.ece</groupId>
            <artifactId>tsvd4j-maven-plugin</artifactId>
            <version>0.1-SNAPSHOT</version>
        </plugin>
    </plugins>
</build>
```

### Running TSVD4J

Once TSVD4J is added to a Maven project, one can then run TSVD4J on the project with the following command.

```shell
mvn tsvd4j:tsvd4j
```

If you need only to keep track of the thread safety violation(TSV) occured by Java field, run the following command.

```shell
mvn tsvd4j:tsvd4j -Dfield
```

If you need only to keep track of thread safety violation(TSV) occured by the Java API, run the following command.

```shell
mvn tsvd4j:tsvd4j -Dapi
```

### For Cleaning the output directory of .tsvd4j/, run the following command. It will remove the .tsvd4j/ directory

```shell
mvn tsvd4j:clean
```



