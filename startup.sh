#!/bin/sh
# Initiated by Jared on July 25, 2018.
# To start the build in tomcat container 9.0

if [ $# != 2 ]; then
  echo "Please specify WORK_DIR SOURCE_DIR firstly"
  echo "Usage: startup.sh [work_dir] [source_dir]"
  exit
fi

echo "Stopping already started containers"
docker stop wfware

WORK_DIR=$1
SOURCE_DIR=$2
OS_NAME=`uname -s`
echo "Working directory is " ${WORK_DIR}
echo "Source directory is " ${SOURCE_DIR}

if [ -d ${WORK_DIR}/workflow/webapps/ROOT/ ]; then
  rm -rf ${WORK_DIR}/workflow/webapps/ROOT/  ${WORK_DIR}/workflow/webapps/ROOT.war
  if [ $? != 0 ]; then
    echo "Failed to delete deployed war"
    exit 1
  fi
else
  mkdir -p ${WORK_DIR}/workflow/webapps/
  if [ $? != 0 ]; then
    echo "Failed to create directory" ${WORK_DIR}/workflow/webapps/
    exit 1
  fi
fi

cp -f ${SOURCE_DIR}/workflow/target/*.war ${WORK_DIR}/workflow/webapps/ROOT.war
if [ $? != 0 ]; then
  echo "Failed to copy war of examination into destination directory"
  exit 1
fi
echo "Deploying wfware successfully"

# before launching tomcat, clear logs generated last time
echo "backup logs generated in ${WORK_DIR}/workflow/logs/"
if [ -e ${WORK_DIR}/logs/workflow/wfware.log ]; then
  mv ${WORK_DIR}/logs/workflow/wfware.log ${WORK_DIR}/logs/workflow/wfware-`date +%F-%H%M`.log
  if [ $? != 0 ]; then
    echo "failed to backup logs"
    return
  fi
fi
if [ $OS_NAME = "Darwin" ]; then
  mkdir -p ${WORK_DIR}/logs/workflow
  chmod -R 777 ${WORK_DIR}/logs
fi

docker run --rm --name wfware -p 8080:8080 -v ${WORK_DIR}/workflow/webapps/:/usr/local/tomcat/webapps \
    -v ${WORK_DIR}/logs/workflow/:/usr/local/tomcat/logs/ -d tomcat:9.0

