#!/bin/bash

ARTIFACT_ID="tsvd4j-maven-plugin"
ARTIFACT_VERSION="0.1-SNAPSHOT"

if [[ $1 == "" ]]; then
	echo "arg1 - the slug of the project where high-level pom exists"
	exit
fi


if [[ ! $2 == "" ]]; then
    ARTIFACT_ID=$2
fi

if [[ ! $3 == "" ]]; then
    ARTIFACT_VERSION=$3
fi

project_path=$1
currentDir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

#THE_PATH_TO_AGENT_JAR=$currentDir"/../../tsvd4j-core/target"
#ARG_LINE="-javaagent:$THE_PATH_TO_AGENT_JAR/tsvd4j-core-0.1-SNAPSHOT.jar"

crnt=`pwd`
working_dir=`dirname $0`

cd ${project_path}
project_path=`pwd`
cd - > /dev/null

cd ${working_dir}

javac PomFile.java
find ${project_path} -name pom.xml | grep -v "src/" | java PomFile ${ARTIFACT_ID} ${ARTIFACT_VERSION}
rm -f PomFile.class

cd ${crnt}
