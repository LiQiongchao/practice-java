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
    localVersion=$(svn info -R | grep "Revision\:" | sort -k 2 -nr | head -n 1 | awk -F ' ' 'NR==1 {print $2}')
    serverUrl=$(svn info |grep "^URL:" | awk '{print $2}')
    serverVersion=$(svn info ${serverUrl} |grep "Last Changed Rev:" |awk '{print $4}')
    if test ${serverVersion} -gt ${localVersion}
    then
        svn update
        mvn clean package -DskipTests
        cd ${moduleName}/target/
        cp -f ${jarName} ${appHome}/jar/
    fi

    cd ${appHome}/jar/
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
if test "$1" = "start" -o -z "$1"
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


