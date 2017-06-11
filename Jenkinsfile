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
        sh 'gradle cleanTest build'
      }
    }
    stage('Report') {
      steps {
        junit 'build/test-results/**/*.xml'
        archiveArtifacts 'build/libs/*.jar'
      }
    }
    stage('Build docker') {
      steps {
        sh 'gradle buildDocker'
      }
    }
  }
}