#!/bin/bash
usage() {
    echo "Usage: sh 执行脚本.sh <profile>"
    echo "eg: sh package.sh test"
    exit 1
}

if [[ -n "$1" ]];then
  mvn clean package -P"$1" -Dmaven.test.skip=true -Dmaven.javadoc.skip=true -f pom.xml
else
  usage
fi