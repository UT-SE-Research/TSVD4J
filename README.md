# TSVD4J

TSVD4J detects thread-safety violations for Java projects, which is based off of the [TSVD approach](https://github.com/microsoft/tsvd) with some extentions. 

If you use this tool, please cite: Shanto Rahman, Chengpeng Li, and August Shi. "TSVD4J: Thread-Safety Violation Detection 
for Java."45th International Conference on Software Engineering (ICSE-Demo). IEEE, 2023.

Our video demonstration of TSVD4J is available [here](https://www.youtube.com/watch?v=-wSMzlj5cMY).

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



