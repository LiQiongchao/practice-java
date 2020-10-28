#!/usr/bin/env bash

# # # # # # # # #
#
# @Version 1.0.0
# @Author lqc
#
# 从sftp下载录音到本地，如果文件不为空并解压文件。
#
# # # # # # # # #

# 日志文件名
WORK_HOME=/data/scripts/download_recording
RECORDING_DIR=${WORK_HOME}/recording
READ_LOG_FILE=record_files.log
USERNAME=
PASSWORD=
SFTP_SERVERS=

DIR_PATTERN="+%Y%m%d"
FILE_PATTERN="+%Y%m%d%H%M"
CUR_TIMESTAMP=`date +%s`
# 取10分钟之前生成的那个录音文件
BACK_SEC=600
# sftp ZW27@120.197.234.174:/ZW27/20201028

file_timestamp=$(expr ${CUR_TIMESTAMP} - ${BACK_SEC})
echo ${file_timestamp}

# 当天录音文件所在的文件夹名：20201028
dir_name=$(date -d @${file_timestamp} ${DIR_PATTERN})
# 10分钟之前生成的压缩文件名 202010281502.tar
file_name=$(date -d @${file_timestamp} ${FILE_PATTERN})".tar"

for server in ${SFTP_SERVERS}
do
    serverArr=(`echo ${server} | tr ':' " "`)
    serverAddr=${serverArr[0]}
    serverPort=${serverArr[1]}
    spawn sftp -P ${serverPort} ${USERNAME}@${serverAddr}:/${USERNAME}/${dir_name}/${file_name} ${RECORDING_DIR}
    expect "${USERNAME}@${serverAddr}'s password:"
    send ${PASSWORD}
done
