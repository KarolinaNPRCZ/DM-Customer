pipeline {
   agent any
   tools {
           maven 'Maven 3_9_3'
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

       stage('Run test') {
           steps {
                        sh 'mvn test'

           }
           post {
               always {
                   junit 'target/surefire-reports/*.xml'
               }
           }
       }
        stage('Build Docker Image') {
           steps {

             script{
                 docker.build('dm-customer_docker_image', '-f Dockerfile .')

             }
           }
        }
   }
}
