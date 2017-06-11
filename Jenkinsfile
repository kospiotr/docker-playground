pipeline {
  agent any
  stages {
    stage('Initialize') {
      steps {
        sh '''echo pwd
echo PATH = ${PATH}
echo M2_PATH = ${M2_PATH}'''
      }
    }
    stage('Build') {
      steps {
        sh './gradlew cleanTest build'
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
        sh './gradlew buildDocker'
      }
    }
  }
}