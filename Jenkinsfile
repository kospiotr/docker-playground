#!/usr/bin/env groovy

properties([
    buildDiscarder(logRotator(numToKeepStr: '5', artifactNumToKeepStr: '5')),
    pipelineTriggers([cron('@daily')]),
])

node {
    stage('Init') {
        jdk = tool name: 'JDK18'
        gradle = tool name: 'GRADLE'

        sh 'printenv'
        sh 'pwd'
        env.JAVA_HOME = "${jdk}"

        echo "jdk installation path is: ${jdk}"

        // next 2 are equivalents
        sh "${jdk}/bin/java -version"

        // note that simple quote strings are not evaluated by Groovy
        // substitution is done by shell script using environment

        deleteDir()
    }

    stage('Environment') {
        sh '$JAVA_HOME/bin/java -version'
        sh "${gradle}/bin/gradle --version"
    }

}