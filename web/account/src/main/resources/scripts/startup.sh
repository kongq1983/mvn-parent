#!/bin/sh
#-----------------------------------------------------------------------------------------------------------------------
# SHR Manager Application Startup Script
#-----------------------------------------------------------------------------------------------------------------------

# 可执行文件路径 会得到当前执行命令目录
MY_BIN_DIR=$(dirname $(readlink -f $0))
# 应用根路径
MY_ROOT=$(dirname ${MY_BIN_DIR})
# 服务包所在目录
MY_SERVICES_DIR=${MY_ROOT}/services
# 依赖包所在目录
MY_LIBS_DIR=${MY_ROOT}/libs
# 配置文件
MY_CONF_DIR=${MY_ROOT}/conf
# 主启动类
MAIN_CLASS=com.kq.mvn.acccount.AccountApplication
# jvm参数配置文件
VM_OPTIONS_FILE=.vmoptions
# JAVA版本号
JAVA_VERSION=
# JAVA可执行文件路径
JAVA_EXECUTABLE=

# 进入bin目录
cd ${MY_BIN_DIR}

func_check_java(){
    if [[ -f "$1/java" ]]; then
        JAVA_VERSION=$($1/java -version 2>&1| head -1 | sed 's/\(.*\)"\(.*\)"\(.*\)/\2/g')
        JAVA_EXECUTABLE=$1/java
    elif [[ -f "$1/bin/java" ]]; then
        JAVA_VERSION=$($1/bin/java -version 2>&1| head -1 | sed 's/\(.*\)"\(.*\)"\(.*\)/\2/g')
        JAVA_EXECUTABLE=$1/bin/java
    else
        echo "Invalid JAVA_HOME:$1"
        return 1
    fi

    if [[ "${JAVA_VERSION:0:3}" != "1.8" ]]; then
        JAVA_EXECUTABLE=
        return 1
    fi

    return 0
}

# -d FILE存在且是一个目录则为真
if [[ -d "${MY_ROOT}/jre" ]]; then
    func_check_java "${MY_ROOT}/jre"
fi

# -z:是否是空字符串  -n:判断是否是非空字符串
if [[ -z "${JAVA_EXECUTABLE}" && -n "${MY_JRE_HOME}" ]]; then
    echo 2
    func_check_java ${MY_JRE_HOME}
fi

if [[ -z "${JAVA_EXECUTABLE}" && -n "${JAVA_HOME}" ]]; then
    func_check_java ${JAVA_HOME}
fi

if [[ -z "${JAVA_EXECUTABLE}" ]]; then
    WHERE_IS_JAVA=$(whereis java | awk -F':' '{print $2}')

    if [[ -n "${WHERE_IS_JAVA}" ]]; then
        for NAME in ${WHERE_IS_JAVA} ; do
            func_check_java $(dirname ${NAME})
            if [[ -n "${JAVA_EXECUTABLE}" ]]; then
                break
            fi
        done
    fi
fi

if [[ -z "${JAVA_EXECUTABLE}" ]]; then
    echo 'Please install `jdk 1.8` and set `JAVA_HOME` or `MY_JDK_HOME`'
    echo 'environment variables based on that installation'
    exit 1
fi

if [[ "${JAVA_VERSION:0:3}" != "1.8" ]]; then
    echo "Unsupported java version: ${JAVA_VERSION}"
    echo 'Please install `jdk 1.8` and set `JAVA_HOME` or `MY_JDK_HOME`'
    echo 'environment variables based on that installation'
    exit 1
fi


SPRING_CONFIG_LOCATION=${MY_CONF_DIR}/application.yml

echo "JAVA_EXECUTABLE: ${JAVA_EXECUTABLE}"
echo "JAVA_VERSION: ${JAVA_VERSION}"
echo "SPRING_CONFIG_LOCATION: ${SPRING_CONFIG_LOCATION}"

export SPRING_CONFIG_LOCATION

# 读取.vmoptions文件
VM_OPTIONS=$( sed -e 's/[\r\n]/ /g' ${MY_BIN_DIR}/.vmoptions)

ps -ef | grep ${MAIN_CLASS}  | grep -v grep
if [ $? -eq 0 ]
then
        echo "$MAIN_CLASS is running...."
        ps -ef | grep ${MAIN_CLASS}  | grep -v grep | awk '{print $2}'  | xargs kill -9;
        echo 'kill process result :'$?;
fi

nohup ${JAVA_EXECUTABLE} ${VM_OPTIONS} -cp ${MY_SERVICES_DIR}/'*':${MY_LIBS_DIR}/'*' ${MAIN_CLASS} > /dev/null 2>&1 &
# nohup ${JAVA_PATH}/java -jar ${LOCAL_JAR_PATH}/${APPNAME}.jar > /dev/null 2>&1 &
echo "PID=$!";


