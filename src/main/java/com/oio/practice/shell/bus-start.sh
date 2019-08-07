#!/usr/bin/env bash

if test -n "$1" -a "$1" != "start" -a "$1" != "stop" -a "$1" != "restart"
then
    echo 'please input keyword ->> start, stop, restart'
fi

version=1.1
profile=dev
appHome=~/hwh-bus
moduleName=customer
modulePort=9001
jarName=bus-${moduleName}-${version}.jar

packageJar() {
    cd ${appHome}/resource/dev-v${version}
    svn update
    mvn clean install -DskipTests
    echo 'package success!'

    cd ${moduleName}/target/
    cp -f ${jarName} ${appHome}/jar/
    if test -e ${jarName}
    then
        echo "${jarName} is prepared."
        break
    fi
}

stopModule() {
   # find app pid
    pid=$(lsof -i:${modulePort} | awk -F " " 'NR==2 {print $2}')
    if test -n ${pid}
    then
        echo "${moduleName} old pid: ${pid}"
        # kill app pid
        kill ${pid}
        sleep 1
        pid=$(lsof -i:${modulePort} | awk -F " " 'NR==2 {print $2}')
        until test -z ${pid}
        do
         pid=$(lsof -i:${modulePort} | awk -F " " 'NR==2 {print $2}')
         sleep 1
        done
    fi
    echo 'killed old pid.'
}

startModule() {
    # exec app command
    cd ${appHome}/jar/
    nohup java -jar ${jarName} --spring.profiles.active=${profile} > /dev/null 2>&1 &
    sleep 1
    pid=$(lsof -i:${modulePort} | awk -F " " 'NR==2 {print $2}')
    tempNum=0
    until test -n "${pid}"
    do
      sleep 1
      pid=$(lsof -i:${modulePort} | awk -F " " 'NR==2 {print $2}')
      tempNum=$((${tempNum} + 1))
      if test ${tempNum} -gt 5
      then
        echo 'launch failed.'
        break
       fi
    done
    echo "start module ${moduleName} pid is ${pid}"
}

# update jar
if test $1 = start -o -z "$1"
then
    packageJar
    startModule
elif test $1 = stop; then
    stopModule
elif test $1 = restart; then
    packageJar
    stopModule
    startModule
else
    echo 'invalid param'
fi


