pipeline {
   agent {
           docker {
               image 'maven:3.8.5-openjdk-17'
               args '--network host -v /var/run/docker.sock:/var/run/docker.sock --group-add 103'
           }
       }

    environment {
        TC_CLOUD_TOKEN = credentials('tc-cloud-token-secret-id')
        POSTGRES_CONTAINER = 'postgres:15.2'

    }



    stages{
        stage('Build Maven'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/develop']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/KarolinaNPRCZ/DM-Customer']]])
                sh 'mvn install -DskipTests'
            }
        }

        stage('TCC SetUp') {
            steps {
                sh "curl -fsSL https://get.testcontainers.cloud/bash | sh "
            }
         }
        stage('Integration test') {
            steps {
             script {


                                docker.image(POSTGRES_CONTAINER).withRun('-p 5432:5432') { postgresContainer ->
                                    sh 'mvn test -pl integration'
                                }
                            }

            }
            post {
              always {
                junit 'target/surefire-reports/*.xml'
              }
            }
        }
    }
}


