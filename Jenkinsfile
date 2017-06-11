pipeline {
  agent {
    docker {
      args '-v /home/jenkins/.m2:/root/.m2'
      image 'dordoka/rpi-java8'
    }
    
  }
  stages {
    stage('Initialize') {
      steps {
        sh '''echo PATH = ${PATH}
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
  }
}