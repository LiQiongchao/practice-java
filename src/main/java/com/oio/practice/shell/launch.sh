#!/usr/bin/env bash


if test $# -lt 1; then
    echo "please input argument: start or stop or restart"
    break
fi

if [[ $1 = 'start' ]]; then
    echo "start"
elif test $1 = stop; then
    echo "stop"
elif test $1 = 'restart'; then
    echo "restart"
else
    echo "please input argument: start or stop or restart"
fi


