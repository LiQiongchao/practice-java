#!/usr/bin/env bash

function func1(){
    count=0
    for cont in {1..3}; do
        count=`expr $count + 1`
    done
    # 函数中使用return返回时，返回值的数据类型必须是数字
    return $count
}
# 在$()的圆括号中可以执行linux命令,当然也包括执行函数
res1=$(func1)
# 变量res2将会接收函数的返回值，这里是3
res2=`echo $?`
if [[ $res2 == 3 ]]; then
    echo "func1() succeeded!"
else
    echo "Not a right number!"
fi