pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "emna19/springpetclinic"
        DOCKER_TAG = "latest"
        DOCKER_CREDS = credentials('22fac1e2-e9b5-415f-b8ce-38633a4140eb')
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Emna-chebbi/SpringPetClinic.git'
            }
        }

        stage('Build JAR') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh '''
                    mvn sonar:sonar \
                    -Dsonar.projectKey=springpetclinic \
                    -Dsonar.projectName=springpetclinic \
                    -Dsonar.java.binaries=target
                    '''
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $DOCKER_IMAGE:$DOCKER_TAG .'
            }
        }

        stage('Login to Docker Hub') {
            steps {
                sh 'echo $DOCKER_CREDS_PSW | docker login -u $DOCKER_CREDS_USR --password-stdin'
            }
        }

        stage('Push Docker Image') {
            steps {
                sh 'docker push $DOCKER_IMAGE:$DOCKER_TAG'
            }
        }
    }

    post {
        success {
            echo 'Pipeline executed successfully with SonarQube!'
        }
        failure {
            echo 'Pipeline failed'
        }
    }
}
