#!/bin/sh

MY_SERVICE_NAME=shr.manager
# 可执行文件路径
MY_BIN_DIR=$(dirname $(readlink -f $0))
# 应用根路径
MY_ROOT=$(dirname ${MY_BIN_DIR})

MY_SERVICE_FILE="/etc/systemd/system/${MY_SERVICE_NAME}.service"
MY_SERVICE_ACTIVE=$(systemctl show "${MY_SERVICE_NAME}.service" --property=ActiveState | awk -F'=' '{print $2}')
MY_SERVICE_RUNNING=$(systemctl show "${MY_SERVICE_NAME}.service" --property=SubState | awk -F'=' '{print $2}')
MY_SERVICE_FILE_EXIST=$(systemctl list-unit-files | grep "${MY_SERVICE_NAME}" | awk '{print $1}' )

function func_service_exist(){
    if [[  -n "${MY_SERVICE_FILE_EXIST}"  ]]; then
        return 1
    fi
    return 0
}

function func_stop_service(){

    if [[ "${MY_SERVICE_ACTIVE}" == "active" || "${MY_SERVICE_RUNNING}" == "running" ]]; then
        systemctl stop "${MY_SERVICE_NAME}.service"
        if [[ $? -eq 0 ]]; then
            echo "Service \`${MY_SERVICE_NAME}\` stopped successfully"
        fi
    fi
}

function func_install_service(){

    touch "${MY_BIN_DIR}/startup.sh" ${MY_SERVICE_FILE}

    echo ""> ${MY_SERVICE_FILE}
    echo "[Unit]" >> ${MY_SERVICE_FILE}
    echo "Description=SHR Manager Service" >> ${MY_SERVICE_FILE}
    echo "After=syslog.target" >> ${MY_SERVICE_FILE}
    echo "[Service]" >> ${MY_SERVICE_FILE}
    echo "ExecStart=${MY_BIN_DIR}/startup.sh" >> ${MY_SERVICE_FILE}
    echo "[Install]" >> ${MY_SERVICE_FILE}
    echo "WantedBy=multi-user.target" >> ${MY_SERVICE_FILE}

    echo "Service \`${MY_SERVICE_NAME}\` has been installed successfully"
    echo "Using \`systemctl start ${MY_SERVICE_NAME}.service\` to start it"
    echo "Using \`systemctl enable ${MY_SERVICE_NAME}.service\` to enable auto starting"
}

function func_uninstall_service(){
    func_stop_service
    systemctl disable "${MY_SERVICE_NAME}.service"
    if [[ -f "/etc/systemd/system/${MY_SERVICE_NAME}.service" ]]; then
        rm -f "/etc/systemd/system/${MY_SERVICE_NAME}.service"
    fi
    systemctl reset-failed
}

function func_usage(){
    echo "Usage: sudo ./services.sh [install|start|stop|uninstall]"
}

if [[ "$#" -eq "0" ]] ; then
    func_usage
    exit 1
fi

if [[ "$1" == "install" ]] ; then
    func_service_exist
    if [[ $? -eq 1 ]]; then
        echo "Service \`${MY_SERVICE_NAME}\` already exist"
        echo "Using \`./service.sh uninstall\` to uninstall it first"
        exit 1
    fi
    func_install_service
elif [[ "$1" == "uninstall" ]]; then
    func_service_exist
    if [[ $? -eq 1 ]]; then
        func_uninstall_service
        echo "Service \`${MY_SERVICE_NAME}\` uninstalled successfully"
    else
        echo "Service \`${MY_SERVICE_NAME}\` does not exists"
    fi
elif [[ "$1" == "start" ]]; then
    func_service_exist
    if [[ $? -eq 0 ]]; then
        echo "Service \`${MY_SERVICE_NAME}\` does not exist"
    else
        func_stop_service
        systemctl start "${MY_SERVICE_NAME}.service"
        if [[ $? -eq 0 ]] ; then
            echo "Service \`${MY_SERVICE_NAME}\` started successfully"
            echo "Using \`systemctl status ${MY_SERVICE_NAME}.service\` to view its status"
        fi
    fi
elif [[ "$1" == "stop" ]]; then
    func_service_exist
    if [[ $? -eq 0 ]]; then
        echo "Service \`${MY_SERVICE_NAME}\` does not exist"
    elif [[ "${MY_SERVICE_RUNNING}" != "running" ]]; then
        echo "Service \`${MY_SERVICE_NAME}\` is not running right now"
    else
        func_stop_service
    fi
elif [[ "$1" == "enable" ]]; then
    func_service_exist
    if [[ $? -eq 0 ]]; then
        echo "Service \`${MY_SERVICE_NAME}\` does not exist"
    else
        systemctl enable "${MY_SERVICE_NAME}.service"
    fi
elif [[ "$1" == "disable" ]]; then
    func_service_exist
    if [[ $? -eq 0 ]]; then
        echo "Service \`${MY_SERVICE_NAME}\` does not exist"
    else
        systemctl disable "${MY_SERVICE_NAME}.service"
    fi
else
    func_usage
    exit 1
fi






