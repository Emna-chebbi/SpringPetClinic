pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "emna19/springpetclinic"
        DOCKER_TAG = "latest"
        DOCKER_CREDS = credentials('22fac1e2-e9b5-415f-b8ce-38633a4140eb')
        KUBECONFIG = "/var/lib/jenkins/.kube/config"
    }

    stages {

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

        stage('Quality Gate') {
            steps {
                timeout(time: 2, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
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

        stage('Deploy to Kubernetes') {
            steps {
                echo 'Deploying to Kubernetes...'
                sh 'echo Using kubeconfig: $KUBECONFIG'
                sh 'kubectl get pods'
                sh 'kubectl apply -f k8s/deployment.yaml'
                sh 'kubectl apply -f k8s/service.yaml'
                sh 'kubectl get pods'
            }
        }
    }

    post {
        success {
            echo 'Pipeline executed successfully!'
            emailext(
                subject: "$JOB_NAME - Build #$BUILD_NUMBER - SUCCESS",
                body: """
✅ Pipeline executed successfully!

Job Name: $JOB_NAME
Build Number: $BUILD_NUMBER

Build URL:
$BUILD_URL
""",
                to: "emna.chebby19@gmail.com"
            )
        }
        failure {
            emailext(
            subject: "$JOB_NAME - Build #$BUILD_NUMBER - FAILED",
            body: """
Module: DevOps
Student: Emna Chebbi

Job Name: $JOB_NAME
Build Number: $BUILD_NUMBER

Build URL:
$BUILD_URL

Cause of Failure:
Please check the console output.

==================== LOGS ====================
$BUILD_LOG
================================================
""",
            to: "emna.chebby19@gmail.com"
        )
    }
}
}

