#!/usr/bin/env bash

ITEM_PATH=D:/WorkSpaces/oioProjects
curRecordFile=D:/gitCodeStat-`date "+%Y%m"`".log"
TODAY_DATE=`date "+%Y-%m-%d %H:%M:%S"`
# 统计周期长度，默认为1天前的
STAT_DURATION_DAY=1

# 统计代码
function statCode() {
    isUpdate=$(git pull)
    echo "date project name commits addLines removeLines oppositeAddLines"
    # linux: Already up-to-date. win:Already up to date.
    #if test "Already up to date." = "${isUpdate}"
    if [[ "Already up to date." != "${isUpdate}" ]]
    then
        echo -e "stat git code => $(pwd)"
#            echo -e "\t\t$(git log --format='%aN' | sort -u | while read name; do echo -en "$name\t"; git log --since=1.day.ago --author="$name" --pretty=oneline | awk -vsum=0 '{ sum += 1 } END { printf "commit counts: %s\n\t\t", sum }' -; done)" >> $curRecordFile
#            echo -e "\t\t$(git log --format='%aN' | sort -u | while read name; do echo -en "$name\t"; git log --since=1.day.ago --author="$name" --pretty=tformat: --numstat | awk -vadd=0 -vsubs=0 -vloc=0 '{ add += $1; subs += $2; loc += $1 - $2 } END { printf "added lines: %s, removed lines: %s, total lines: %s\n\t\t", add, subs, loc }' -; done)" >> $curRecordFile
        git log --format='%aN' | sort -u | while read name; do echo -en "$TODAY_DATE $1 $name"; git log --since=${STAT_DURATION_DAY}.day.ago --author="$name" --pretty=oneline | awk -vsum=0 '{ sum += 1 } END { printf " %s", sum }' -; git log --since=${STAT_DURATION_DAY}.day.ago --author="$name" --pretty=tformat: --numstat | awk -vadd=0 -vsubs=0 -vloc=0 '{ add += $1; subs += $2; loc += $1 - $2 } END { printf " %s %s %s \n", add, subs, loc }' -; done  >> $curRecordFile
    else
        # 没有更新，不统计代码量
#            echo -e "\n" >> $curRecordFile
        echo -e "$isUpdate"
    fi
}

# 读取文件夹下的所有项目文件夹
function readDir() {
    echo "current record file name: $curRecordFile"
    cd $ITEM_PATH
    for subDir in `ls $ITEM_PATH` ;
    do
        echo "sub dir : $subDir"
        cd $subDir
        echo "current dir: $(pwd)"
        statCode $subDir
        cd ../
    done
    echo "" >> $curRecordFile
}

# 启动脚本
readDir
