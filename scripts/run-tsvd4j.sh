#!/usr/bin/env bash

if [[ $1 == "" ]]; then
    echo "arg1 - full path to the test file (eg. data_list/build_success_.csv)"
    exit
fi

currentDir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

input=$1
inputProj=$currentDir"/Projects"
logs=$currentDir"/Logs"
results=$currentDir"/Result/"
resultFile=$currentDir"/Result/ResultFile"

mkdir -p ${inputProj}
mkdir -p ${logs}
mkdir -p ${results}
while IFS= read -r line
do
    if [[ ${line} =~ ^\# ]]; then
        continue
    fi
    
    slug=$(echo $line | cut -d',' -f1)
    initial_sha=$(echo $line | cut -d',' -f2)
    name=$(echo $slug | sed 's/\//-/g')
    conflicting_pair_in_result="$resultFile-ConflictingPair-$name"
    if [[ ! -d ${inputProj}/${slug} ]]; then
        git clone "https://github.com/$slug" $inputProj/$slug
    fi

    (
        cd $inputProj/$slug
        echo -n  "${slug}" >> $resultFile  #$currentDir/data_list/buildSucessProjectIdoftTSVDTime.csv
        if [ ! -z ${initial_sha} ]; then # Checking if initial sha is empty or not
            git checkout ${initial_sha}
            echo -n ",${initial_sha}" >> $resultFile #$currentDir/data_list/buildSucessProjectIdoftTSVDTime.csv
            conflicting_pair_in_result="$resultFile-ConflictingPair-${name}_${initial_sha}"
        else
            conflicting_pair_in_result="$resultFile-ConflictingPair-${name}_${initial_sha}"
            echo -n ",master" >> $resultFile
        fi
        touch  ${conflicting_pair_in_result}

        MVNOPTIONS="-Ddependency-check.skip=true -Denforcer.skip=true -Drat.skip=true -Dmdep.analyze.skip=true -Dmaven.javadoc.skip=true -Dgpg.skip -Dlicense.skip=true"
    
        cd $currentDir"/pom-modify"
        bash modify-project.sh $inputProj/$slug
        cd $inputProj/$slug
    
        if [[ $slug == "shanto-Rahman/dataRace-Example" ]]; then
            cd "dataRace_1"
        fi
        for i in {1..1}
        do	
        	start=$(date +%s.%N)
            timeout 6h mvn tsvd4j:tsvd4j |&tee ${logs}/${name}-${i}-log
        
            end=$(date +%s.%N)
        	take=$(echo "scale=2; ${end} - ${start}" | bc)
        	take=$(echo $take | awk '{printf("%.2f\n", $1) }')
            allResultFile=$(find -name "Conflicting-Pairs.txt")
            echo $allResultFile
        	bugs=$(find -name "Conflicting-Pairs.txt" | xargs wc -l | cut -f1 -d: | awk 'END {print $1}')
        	echo "BUGS******************************$bugs"
        	echo -n ",${bugs}" >> $resultFile 
        	echo ",${take}" >> $resultFile
                    	
            allResultFile=$(find -name "Conflicting-Pairs.txt")
            for  Item in ${allResultFile[*]} 
            do
                if [[ ! -z $(grep '[^[:space:]]' $Item) ]]; then
                    echo $Item
                    echo " ********$(cat $Item) "
                    uniqueSet=$(comm -13 <(sort -u  "$conflicting_pair_in_result" ) <(sort -u $Item))
                    echo $uniqueSet
                    len=${#uniqueSet[@]}
                    for (( j=0; j<$len; j++ )); 
                    do  
                        echo "${uniqueSet[$j]}"  >> "$conflicting_pair_in_result"
                    done
                fi
            done
    
     done
    )
#  rm -rf $inputProj/$slug

done < "$input"
