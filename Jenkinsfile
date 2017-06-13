pipeline {
  agent any
  stages {
    stage('Initialize') {
      steps {
        sh '''pwd
echo PATH = ${PATH}
which gradle
export CURRENT_VERSION=`./gradlew printVersion | grep -Po "version: \\K(.*)"`
export NEW_VERSION=`echo "${CURRENT_VERSION}" | sed 's/-SNAPSHOT/-${env.BUILD_ID}/g'`

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
