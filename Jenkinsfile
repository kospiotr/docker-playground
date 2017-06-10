pipeline {
  agent {
    docker {
      image 'maven:3.3.9-jdk-8'
      args '-v /home/jenkins/.m2:/root/.m2'
    }
    
  }
  stages {
    stage('Initialize') {
      steps {
        sh '''echo PATH = ${PATH}
echo M2_PATH = ${M2_PATH}
mvn clean
sleep 10m'''
      }
    }
    stage('Build') {
      steps {
        sh 'mvn install -Dmaven.test.failure.ignore=true '
      }
    }
    stage('Report') {
      steps {
        junit 'target/surefire-reports/**/*.xml'
        archiveArtifacts 'target/*.jar,target/*.hpi'
      }
    }
  }
}