#!/usr/bin/env bash

set USERNAME [lindex $argv 0]
set PASSWORD [lindex $argv 1]
set serverAddr [lindex $argv 2]
set serverPort [lindex $argv 3]
set FILE [lindex $argv 4]
set RECORDING_DIR [lindex $argv 5]

spawn sftp -P ${serverPort} ${USERNAME}@${serverAddr}:${FILE} ${RECORDING_DIR}
expect {
    "(yes/no)?" {send "yes\r"; expect_continue}
    "password:" {send "${PASSWORD}\r"}
}
expect eof
