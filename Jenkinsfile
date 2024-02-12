pipeline {
    agent any
    tools{
        maven 'Maven 3_9_3'
    }
    stages{
        stage('Build Maven'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/develop']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/KarolinaNPRCZ/DM-Customer']]])
                sh 'mvn install -DskipTests'
            }
        }
        stage('Start Ryuk') {
                    steps {
                        script {
                            sh 'docker run -d --network host --name ryuk-testcontainers testcontainers/ryuk:1.5.2'
                        }
                    }
                }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
              always {
                junit 'target/surefire-reports/*.xml'
              }
            }
        }
    }
}


