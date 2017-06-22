#!/usr/bin/env groovy

pipeline {
    agent any
    stages {
        stage('Initialize') {
        steps {
            sh 'echo "test"'
            sh '''echo `printenv`
CURRENT_VERSION=`awk -F= '$1=="version"{print $2}' gradle.properties`
if [ "${BRANCH_NAME}" = "master" ]; then ID="${BUILD_ID}"; else ID="${BRANCH_NAME}"; fi
NEW_VERSION=`echo "${CURRENT_VERSION}-${ID}" | sed "s/-SNAPSHOT//g"`
sed -i "s/^\\(version\\s*=\\s*\\).*$/\\1${NEW_VERSION}/" gradle.properties'''
            }
        }
        stage('Build') {
            steps {
                sh 'gradle clean cleanTest build buildDocker'
                junit 'build/test-results/**/*.xml'
                archiveArtifacts 'build/libs/*.jar'
            }
        }
        stage('Tag') {
            when {
                expression { env.BRANCH_NAME == 'master' }
            }
            steps {
                sh '''export NEW_VERSION=`awk -F= '$1=="version"{print $2}' gradle.properties`
                git add .
                git commit -m "Changing version to ${NEW_VERSION}"
                git tag -a build-${NEW_VERSION} -m "Tagging build ${NEW_VERSION}"
                git push --tags'''
            }
        }
        stage('Deploy') {
            //when {
            //    expression { env.BRANCH_NAME.startsWith('PR-' }
            //}
            steps {
                sh '''echo "init"
GROUP_ID=`awk -F= '$1=="groupId"{print $2}' gradle.properties`
ARTIFACT_ID=`awk -F= '$1=="artifactId"{print $2}' gradle.properties`
CURRENT_VERSION=`awk -F= '$1=="version"{print $2}' gradle.properties`
export COMPOSE_PROJECT_NAME=` echo "${ARTIFACT_ID}_${BRANCH_NAME}" | awk '{print tolower($0)}'`
docker ps --filter "label=pl.xperios.project=${COMPOSE_PROJECT_NAME}" -q | awk 'docker stop $0'
TAKEN_PORTS=`docker ps --format='{{.Ports}}' | grep -Po "(?<=:)\\d+(?=->)" | sort`
MIN_PORT="9000"
MAX_PORT="9999"
export APP_IMAGE=`echo "${GROUP_ID}/${ARTIFACT_ID}:${CURRENT_VERSION}" | awk '{print tolower($0)}'`
export PORT=`seq ${MIN_PORT} ${MAX_PORT} | grep -v "$TAKEN_PORTS" | head -n 1`
if [ -z "${PORT}" ]; then export PORT="${MIN_PORT}"; fi
docker-compose -f app-rpi-dev.yml up -d --remove-orphans
echo "Application succesfully deployed and available under: http://inst${PORT}.dev.xperios.pl"
'''
            }
        }
    }
}

