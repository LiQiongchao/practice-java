#!/usr/bin/env bash

MAIN_CLASS=com.hwh.tool.redis.CacheCron
PROC_NAME=${MAIN_CLASS}
TAG="zzmhwhv2test"
VM_FLAG="-Dtag=$TAG"
LOADER_PATH="./bin ./lib/*.jar ./conf"

pid=$(ps -ef | grep -i ${MAIN_CLASS} | grep ${TAG} | awk -F " " 'NR==1 {print $2}')

# 直到pid为空
until [[ -z ${pid} ]]
do
    # $pid == ${pid}
    if [[ -n ${pid} ]]
    then
        kill ${pid}
        sleep 2
        echo "killed pid ${pid}"
        pid=$(ps -ef | grep -i ${MAIN_CLASS} | grep ${TAG} | awk -F " " 'NR==1 {print $2}')
    fi
done

set_classpath(){
    set ${LOADER_PATH}
    while [ $# -gt 0 ]; do
        classpath=${classpath}:$1
        shift
    done
    CLASSPATH=${classpath}:${CLASSPATH}
}
set_classpath
nohup java -Diname=${PROC_NAME} ${VM_FLAG} -cp ${CLASSPATH} ${MAIN_CLASS} $* > cachecron.nohup 2>&1 &

pid=$(ps -ef | grep -i ${MAIN_CLASS} | grep ${TAG} | awk -F " " 'NR==1 {print $2}')

if test -n ${pid}; then
    echo "started ${MAIN_CLASS} pid ${pid}"
    else
    echo "start ${MAIN_CLASS} failed"
fi

