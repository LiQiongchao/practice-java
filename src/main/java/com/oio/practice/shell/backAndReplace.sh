#!/usr/bin/sh

term_home=/data/apps/hwh-backend-v2.0
time=`date +"%Y%m%d%H%M%S"`
back_dir_name=backups

cd $term_home
# 删除上次的解压备份
rm -rf bin_bak/
mv bin/ bin_bak
unzip bin.zip
sh custserverRestart.sh

# 备份这次的代码
mv bin.zip backups/bin_${time}.zip
# 删除30天之前的备份
find ${back_dir_name} -name "bin*" -type f -ctime +30 | xargs rm -rf
