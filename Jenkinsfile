pipeline {
  agent any
  stages {
    stage('Initialize') {
      steps {
        sh '''pwd
echo PATH = ${PATH}
which gradle'''
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