pipeline {
    agent any
    stages {
        stage('Initialize') {
        steps {
            sh '''echo `printenv`
export CURRENT_VERSION=`./gradlew printVersion | grep -Po "version: \\K(.*)"`
export NEW_VERSION=`echo "${CURRENT_VERSION}-${BUILD_ID}" | sed "s/-SNAPSHOT//g"`
sed -i "s/^\\(version\\s*=\\s*\\).*$/\\1${NEW_VERSION}/" gradle.properties'''
            }
        }
        stage('Build') {
            steps {
                sh 'gradle clean buildDocker'
                junit 'build/test-results/**/*.xml'
                archiveArtifacts 'build/libs/*.jar'
            }
        }
        stage('Tag') {
            when {
                expression { params.BRANCH_NAME == 'master' }
            }
            steps {
                sh '''export NEW_VERSION=`awk -F= '$1=="version"{print $2}' gradle.properties`
                git add .
                git tag -a build-${NEW_VERSION} -m "Tagging build ${NEW_VERSION}"
                git push --tags'''
            }
        }
    }
}
