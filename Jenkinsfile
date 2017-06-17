#!/usr/bin/env groovy

pipeline {
    agent any
    stages {
        stage('Initialize') {
        steps {
            sh '''echo `printenv`
CURRENT_VERSION=`awk -F= '$1=="version"{print $2}' gradle.properties`
if [ "${BRANCH_NAME}" = "master" ]; then ID="${BUILD_ID}"; else ID="${BRANCH_NAME}"; fi
NEW_VERSION=`echo "${CURRENT_VERSION}-${ID}" | sed "s/-SNAPSHOT//g"`
sed -i "s/^\\(version\\s*=\\s*\\).*$/\\1${NEW_VERSION}/" gradle.properties'''
            }
        }
        stage('Build') {
            steps {
                sh 'gradle cleanTest build buildDocker'
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
export GROUP_ID=`awk -F= '$1=="groupId"{print $2}' gradle.properties`
export ARTIFACT_ID=`awk -F= '$1=="artifactId"{print $2}' gradle.properties`
export CURRENT_VERSION=`awk -F= '$1=="version"{print $2}' gradle.properties`
export TAG=`echo "${GROUP_ID}/${ARTIFACT_ID}:${CURRENT_VERSION}" | awk '{print tolower($0)}'`
echo "deploying docker tag: ${TAG}'''
            }
        }
    }
}
