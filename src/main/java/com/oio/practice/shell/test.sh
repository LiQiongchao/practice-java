#!/usr/bin/env bash


# 字符串要加引号
#if test 1 -eq 1 -o $1 = start
if test -z "$1" || "$1" = "start"
then
    echo 'start'
else
    echo 'else'
fi

exit

# 测试判断
temp=
if test -n "$temp" -a "$temp" != "start" -a "$temp" != "stop" -a "$temp" != "restart"
then
    echo 'please input keyword ->> start, stop, restart'
fi


exit

# 测试运算
pid=
tempNum=0
until test -n "${pid}"
do
  sleep 1
  tempNum=$(($tempNum + 1))
  echo  "sleep $tempNum"
  if test $tempNum -gt 6
  then
    echo 'May be launch fail.'
    break
  fi
done


exit

pid=1
#until test -n "$pid"
until test $pid -gt 0
do
    echo "pid $pid"
done


exit

#if [[ $1 != '' || $1 != 'start' || $1 != 'stop' || $1 != 'restart' ]]
echo "$1"
if test -n "$1" -a "$1" != "start" -a "$1" != "stop" -a "$1" != "restart"
then
    echo 'please input keyword ->> start, stop, restart'
fi

exit

echo 'aa'


# 函数测试
#echo 'function pre'
#hello
#echo 'function behind'
#hello() {
#    echo 'hello function'
#}



#num=0
#until test $num -eq 5
#do
# num=$(($num + 1))
# echo "$num"
#done



#abc="a"
#until test -z $abc
#do
# echo "$abc"
# abc=""
#done











