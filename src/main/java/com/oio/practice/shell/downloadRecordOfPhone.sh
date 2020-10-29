#!/usr/bin/env bash

# # # # # # # # #
#
# @Version 1.0.0
# @Author lqc
#
# 从sftp下载录音到本地，如果文件不为空并解压文件。
#
# # # # # # # # #

# 录音最终存放的目录地址前缀
FINAL_RECORDING_DIR=/data/hwh-record
# 日志文件名
WORK_HOME=/data/scripts/download_recording
RECORDING_DIR=${WORK_HOME}/recording
USERNAME=
PASSWORD=
SFTP_SERVERS=(1 2)

DIR_PATTERN="+%Y%m%d"
FILE_PATTERN="+%Y%m%d%H%M"
CUR_TIMESTAMP=`date +%s`
# 取10分钟之前生成的那个录音文件
BACK_SEC=600

# 下载录音并解压
down_recording() {
    file_timestamp=$(expr ${CUR_TIMESTAMP} - ${BACK_SEC})
    echo ${file_timestamp}

    # 当天录音文件所在的文件夹名：20201028
    dir_name=$(date -d @${file_timestamp} ${DIR_PATTERN})
    # 10分钟之前生成的压缩文件名 202010281502.tar
    file_name=$(date -d @${file_timestamp} ${FILE_PATTERN})".tar"
    printf "start download ${file_name} \n"
    for server in ${SFTP_SERVERS[@]}
    do
        serverArr=(`echo ${server} | tr ':' " "`)
        serverAddr=${serverArr[0]}
        serverPort=${serverArr[1]}
        /usr/bin/expect ${WORK_HOME}/expect_download.sh ${USERNAME} ${PASSWORD} ${serverAddr} ${serverPort} /${USERNAME}/${dir_name}/${file_name} ${RECORDING_DIR}
        cd ${RECORDING_DIR}
        if [[ -e ${file_name} ]]; then
            printf "${file_name} download from server ${serverAddr} successful! \n"
        else
            printf "${file_name} download from server ${serverAddr} failure! \n"
            exit
        fi
        # 不为空的话就解压文件
        if [[ ! -s ${file_name} ]]; then
            printf "%s is empty! \n" ${file_name}
        else
            printf "%s is not empty! \n" ${file_name}
            if [[ ! -d ${FINAL_RECORDING_DIR}/${dir_name} ]]
            then
                sudo mkdir -p ${FINAL_RECORDING_DIR}/${dir_name}
                printf "${FINAL_RECORDING_DIR}/${dir_name} was created! \n"
            fi
            sudo tar -xvf ${file_name} -C ${FINAL_RECORDING_DIR}/${dir_name}
        fi
        rm -rf ${file_name}
    done
}

down_recording

