#!/usr/bin/env bash


#declare -A account=(["001"]="0001",["002"]="0002")
declare -A myMap=(["my00"]="00" ["my01"]="01")
myMap["my02"]="02"
myMap["my03"]="03"
for key in ${!myMap[*]}
do
    echo "key:"$key
    echo "value:"${myMap[$key]}
done
