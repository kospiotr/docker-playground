pipeline {
  agent any
  stages {
    stage('Initialize') {
      steps {
        sh '''pwd
echo PATH = ${PATH}
which gradle
export CURRENT_VERSION=`./gradlew printVersion | grep -Po "version: \K(.*)"`
echo "CURRENT_VERSION=${CURRENT_VERSION}"'''
      }
    }
    stage('Build') {
      steps {
        sh 'gradle clean buildDocker'
        junit 'build/test-results/**/*.xml'
        archiveArtifacts 'build/libs/*.jar'
      }
    }
  }
}
