pipeline {
   agent {
           docker {
               image 'maven:3.8.5-openjdk-17'

               args '-v /var/run/docker.sock:/var/run/docker.sock'
           }
       }
    stages{
        stage('Build Maven'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/develop']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/KarolinaNPRCZ/DM-Customer']]])
                sh 'mvn install -DskipTests'
            }
        }
        stage('Integration test') {
            steps {
                sh 'mvn test -pl integration'
            }
            post {
              always {
                junit 'target/surefire-reports/*.xml'
              }
            }
        }
    }
}


