pipeline {
  agent any
  stages {
    stage('Initialize') {
      steps {
        sh ""
        sh '''echo `printenv`
pwd
echo PATH = ${PATH}
echo BUILD_ID = ${BUILD_ID}
which gradle
export CURRENT_VERSION=`./gradlew printVersion | grep -Po "version: \\K(.*)"`
export NEW_VERSION=`echo "${CURRENT_VERSION}-${BUILD_ID}" | sed "s/-SNAPSHOT//g"`
echo "CURRENT_VERSION=${CURRENT_VERSION}"
echo "NEW_VERSION=${NEW_VERSION}"
sed -i "s/^\\(version\\s*=\\s*\\).*$/\\1${NEW_VERSION}1/" gradle.properties'''
      }
    }
    stage('Build') {
      steps {
        sh 'gradle clean buildDocker install'
        junit 'build/test-results/**/*.xml'
        archiveArtifacts 'build/libs/*.jar'
      }
    }
  }
}
