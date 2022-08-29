#!/usr/bin/env bash
# 
# 一个模板
# 脚本运行格式由 script [OPTION] [ARG...] 构成
#
# mark_zhou

# u: 不允许未绑定的变量；o pipefail: 在管道命令中也执行 -e；E: 补充 e 不能响应 trap 命令
set -Eeuo pipefail

# 捕捉退出、异常信号量，并执行 cleanup 函数清理
trap cleanup SIGINT SIGTERM ERR EXIT

# 脚本所在路径，所有相对路径操作一定要从此处开始
SCRIPT_DIR=$(cd "$(dirname "${BASH_SOURCE[0]}")" &>/dev/null && pwd -P)


#######################################
# Help
# Outputs:
#   Writes usage to stdout
#######################################
function usage() {
  cat <<EOF
Usage: $(basename "${BASH_SOURCE[0]}") [-h] [-v] [-f] [-p param_value] arg1 [arg2...]

Script description here.

Available options:

-h, --help      Print this help and exit
-v, --verbose   Print script debug info
-f, --flag      Some flag description
-p, --param     Some param description
EOF
  exit
}


#######################################
# 用于清理脚本副作用
# 脚本正常、异常结束时都会调用这个
# Arguments:
#   None
#######################################
function cleanup() {
  trap - SIGINT SIGTERM ERR EXIT
  # script cleanup here
}


#######################################
# 注册颜色变量，方便打印带颜色的内容
# 只会在 TERM 终端执行，非终端执行会抹除所有颜色标记
# Globals:
#   几个颜色标记：RED GREEN ORANGE BLUE PURPLE CYAN YELLOW
#######################################
function setup_colors() {
  if [[ -t 2 ]] && [[ -z "${NO_COLOR-}" ]] && [[ "${TERM-}" != "dumb" ]]; then
    NOFORMAT='\033[0m' RED='\033[0;31m' GREEN='\033[0;32m' ORANGE='\033[0;33m' BLUE='\033[0;34m' PURPLE='\033[0;35m' CYAN='\033[0;36m' YELLOW='\033[1;33m'
  else
    NOFORMAT='' RED='' GREEN='' ORANGE='' BLUE='' PURPLE='' CYAN='' YELLOW=''
  fi
}


#######################################
# 打印
# Arguments:
#   要打印的内容
#######################################
function msg() {
  echo >&2 -e "${1-}"
}


#######################################
# 打印错误信息到 STDERR
# Arguments:
#   要打印的内容
#######################################
if [[ -t 2 ]] && [[ "${TERM-}" != "dumb" ]]; then
  ERR_DATE="[$(date +'%Y-%m-%dT%H:%M:%S%z')]: "
else
  ERR_DATE=""
fi
function err() {
  echo "${ERR_DATE}$*" >&2
}


#######################################
# 出现问题，主动调用此函数杀掉脚本
# Arguments:
#   要打印的内容
#######################################
function die() {
  local msg=$1
  local code=${2-1} # default exit status 1
  msg "$msg"
  exit "$code"
}


#######################################
# 用于解析 CLI 参数
# Arguments:
#   $@
#######################################
function parse_params() {
  # default values of variables set from params
  flag=0
  param=''

  while :; do
    case "${1-}" in
      
      # 解析 OPTION
      -f | --flag) 
        flag=1
        shift
        ;; # example flag
      -p | --param) # example named parameter
        param="${2-}"
        shift
        ;;
      # 通用参数，一定放在最后
      -h | --help) usage ;;
      -v | --verbose) set -x ;;
      --no-color) NO_COLOR=1 ;;
      -?*) die "Unknown option: $1" ;;
      # 解析 args
      *)
        msg "args ${1}"
        break ;;
    esac
    shift
  done

  args=("$@")

  # 校验必备参数
  #[[ -z "${param-}" ]] && die "Missing required parameter: param"
  [[ ${#args[@]} -eq 0 ]] && die "Missing script arguments"

  return 0
}

# 初始化
parse_params "$@"
setup_colors

# script logic here

msg "${RED}Read parameters:${NOFORMAT}"
msg "- flag: ${flag}"
msg "- param: ${param}"
msg "- arguments: ${args[*]-}"
