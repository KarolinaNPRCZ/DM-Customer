pipeline {
    agent any
    tools {
        maven 'Maven 3_9_3'
    }

    environment {
        TC_CLOUD_TOKEN = credentials('tc-cloud-token-secret-id')
    }

    stages {
        stage('Build Maven') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/feature/004/Jenkins']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/KarolinaNPRCZ/DM-Customer']]])
                sh 'mvn install -DskipTests'
            }
        }

        stage('TCC SetUp') {
            steps {
                sh "curl -fsSL https://get.testcontainers.cloud/bash | sh "
            }
        }



        stage('Build Docker Image') {
            steps {
                script {
                    docker.build('dm-customer_docker_image', '-f Dockerfile .')
                }
            }
        }

      stage('Run Docker') {
                 steps {
                     script {
                         try {

                             sh 'docker-compose down'
                         }
                         catch(Exception e) {

                             echo "Failed to stop containers: ${e.message}"
                         }
                         finally {

                             sh 'docker-compose up -d'
                         }



                     }
                 }
             }
    }
}
