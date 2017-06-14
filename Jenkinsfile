pipeline {
  agent any
  stages {
    stage('Initialize') {
      steps {
        sh '''echo `printenv`
export CURRENT_VERSION=`./gradlew printVersion | grep -Po "version: \\K(.*)"`
export NEW_VERSION=`echo "${CURRENT_VERSION}-${BUILD_ID}" | sed "s/-SNAPSHOT//g"`
echo "CURRENT_VERSION=${CURRENT_VERSION}"
echo "NEW_VERSION=${NEW_VERSION}"
sed -i "s/^\\(version\\s*=\\s*\\).*$/\\1${NEW_VERSION}1/" gradle.properties'''
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
      steps {
        sh '''export NEW_VERSION=`awk -F= \'$1=="test"{print $2}\' gradle.properties`'''
        sh 'git tag -a build-${NEW_VERSION} -m "Tagging build ${NEW_VERSION}"'
        sh 'git push --tags'
      }
    }
  }
}
