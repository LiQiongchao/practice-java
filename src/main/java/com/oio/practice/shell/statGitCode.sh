#!/usr/bin/env bash

ITEM_PATH=D:/WorkSpaces/practise-projects
curRecordFile=D:/gitCodeStat-`date "+%Y%m"`".log"

# 读取文件夹下的所有项目文件夹
function readDir() {
    echo -e "\n`date "+%Y-%m-%d %H:%M:%S"`" >> $curRecordFile
    echo "current record file name: $curRecordFile"
    cd $ITEM_PATH
    for subDir in `ls $ITEM_PATH` ;
    do
        echo "sub dir : $subDir"
        cd $subDir
        echo "current dir: $(pwd)"
        isUpdate=$(git pull)
        #if test "Already up to date." = "${isUpdate}"
        if [[ "Already up to date." != "${isUpdate}" ]]
        then
            echo -e "stat git code ... \n"
            echo -e "\n\t$subDir" >> $curRecordFile
            echo -e "\t\tcommits:" >> $curRecordFile
            echo $(git log --format='%aN' | sort -u | while read name; do echo -en "$name\t"; git log --since=1.day.ago --author="$name" --pretty=oneline | awk -vsum=0 '{ sum += 1 } END { printf "commit counts: %s\n", sum }' -; done) >> $curRecordFile
            echo -e "\n\t\tupdates:" >> $curRecordFile
            echo $(git log --format='%aN' | sort -u | while read name; do echo -en "$name\t"; git log --since=1.day.ago --author="$name" --pretty=tformat: --numstat | awk -vadd=0 -vsubs=0 -vloc=0 '{ add += $1; subs += $2; loc += $1 - $2 } END { printf "added lines: %s, removed lines: %s, total lines: %s\n", add, subs, loc }' -; done) >> $curRecordFile
        else
            # 没有更新，不统计代码量
            echo -e "\n" >> $curRecordFile
            echo -e "Already up to date. \n"
        fi
        cd ../
    done
}

# 启动脚本
readDir
