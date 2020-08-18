@ECHO OFF

:-----------------------------------------------------------------------------------------------------------------------
: SHR Manager Application Startup Scripts
:-----------------------------------------------------------------------------------------------------------------------

:: startup.bat 脚本所在路径
SET MY_BIN_DIR=%~dp0

:: 应用根目录
SET MY_ROOT=%MY_BIN_DIR%..

echo "MY_ROOT: %MY_ROOT%"

:: 服务包所在目录
SET MY_SERVICES_DIR=%MY_ROOT%\services

:: 依赖包所在目录
SET MY_LIBS_DIR=%MY_ROOT%\libs

:: 主启动类
SET MAIN_CLASS=com.kq.mvn.acccount.AccountApplication

:: 虚拟机参数，读取.vmoptions文件
SET VM_OPTIONS=

:READ_VMOPTIONS
FOR /F "delims=" %%a in (%MY_BIN_DIR%/.vmoptions) do ( CALL "%MY_BIN_DIR%/append.bat" "%%~a" )

:: 进入bin目录
cd %MY_BIN_DIR%

if "%MY_HOME%" == "" (

    if exist %MY_ROOT%\conf (
        set MY_HOME=%MY_ROOT%
        set SPRING_CONFIG_LOCATION=%MY_ROOT%\conf\application.yml
    ) else (
        echo "Cannot find MY_HOME"
    )

) else (
    set SPRING_CONFIG_LOCATION=%MY_ROOT%\conf\application.yml
)

echo "SPRING_CONFIG_LOCATION: %SPRING_CONFIG_LOCATION%"
echo "MY_HOME: %MY_HOME%"

java %VM_OPTIONS% -cp "%MY_SERVICES_DIR%\*;%MY_LIBS_DIR%\*" %MAIN_CLASS%

if not %errorlevel% equ  0  pause

