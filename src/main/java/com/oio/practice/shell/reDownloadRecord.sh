#!/bin/sh
# #
# 重新下载之前失败的录音
# 如果参数中有日期，使用参数中的日期，如果没有，就自动下载昨天下载失败的录音。
# 日期格式为: 20201222
#
# 使用date -d 选项：
#　　 date  +"%Y%m%d" -d  "+n days"         今天的后n天日期
#     date  +"%Y%m%d" -d  "-n days"          今天的前n天日期
# [root@localhost riqi]# date +"%Y-%m-%d %H:%M:%S" -d "-2day"
# 2012-07-16 05:18:19
# [root@localhost riqi]# date +"%Y-%m-%d %H:%M:%S" -d "-2month"
# 2012-05-18 05:18:24
# #

# 接收传进来的日期，格式为: 20201222
record_date=$1

# 有参数使用参数，没有参数默认使用昨天的日期
if [[ $# -eq 0 ]]
then
    # 获取前一天的日期
    record_date=$(date +"%Y%m%d" -d "-2days")
fi

log_name=download-${record_date}.log

failure_files=$(cat ${log_name} | grep 'failure' | awk '{ind = index($0,".tar"); print substr($0,ind-12, 16);}')

printf "start again download failure record in $log_name"

# 遍历查询到下载失败的文件
for file in ${failure_files}
do
#    printf "${file} \n"
    sh download_single.sh ${file}
done



