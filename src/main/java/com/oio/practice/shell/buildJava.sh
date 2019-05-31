#!/usr/bin/env bash

deployDir=/home/ac/zzmhwhv2test
iterDir=${deployDir}/hwh-backend/trunk
backDirName=backups

# *.java目录的文件
src=src-source.txt
todayDate=$(date +'%Y%m%d')

cd ${iterDir}
svn update

rm -rf bin/*

find common -name "*.java" > ${src}
find tool -name "*.java" >> ${src}
find crmserver -name "*.java" >> ${src}
find customserver -name "*.java" >> ${src}
find xhserver -name "*.java" >> ${src}

# can't run
javac -encoding UTF-8 -Djava.ext.dirs=./lib -d ./bin/ @${src}

binCount=$(ls bin/ | wc -w)
# don't restart if not compile classes
if [[ ${binCount} -eq 0 ]]
then
    echo "${iterDir} is empty"
    exit
fi

cd ${deployDir}
tar -cvf ${backDirName}/bin_${todayDate}.tar bin/
rm -rf bin/*

cp -rf ${iterDir}/bin/* ./bin/

# back and copy force config files and lib
tar -cvf ${backDirName}/lib_${todayDate}.tar lib/
rm -rf lib/*

cp -rf ${iterDir}/lib/* ./lib/

sh cacheinit.sh all

sh cachecronRestart.sh
sh custserverRestart.sh
sh xhserverRestart.sh
sh ordertimerRestart.sh
