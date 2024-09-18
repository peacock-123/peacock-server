pipeline {
    agent any

    environment {
        MANIFEST_REPOSITORY_PATH = 'github.com/peacock-123/infra.git'
        SPRING_PROFILES_ACTIVE = 'prod'
    }

    stages {
        stage('Clone Repository') {
            steps {
                checkout scm
                sh 'git submodule update --init'
            }
        }

        stage('Initialize') {
            steps {
                script {
                    env.GITHUB_SHA = sh(returnStdout: true, script: 'git rev-parse HEAD | cut -c 1-10').trim()
                    env.DOCKER_IMAGE_NAME = "ghrc.io/peacock-123/peacock-server"
                }
            }
        }

        stage('Build') {
            steps {
                sh './gradlew clean :peacock-api:build'
            }
        }

        stage('Build Docker Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'ghcr', usernameVariable: 'GH_USERNAME', passwordVariable: 'GH_TOKEN')]) {
                    sh './gradlew :peacock-api:jib'
                }
            }
        }

        stage('Edit manifest image version') {
            steps {
                withCredentials([string(credentialsId: 'github', variable: 'TOKEN')]) {
                    sh("""
                        rm -rf manifest
                        git clone https://dygma0:$TOKEN@$MANIFEST_REPOSITORY_PATH manifest
                        cd manifest
                        sed -i '0,/tag:/{s/tag:.*/tag: ${env.GITHUB_SHA}/}' peacock-api/values.yaml
                        git config --global user.email "404err@naver.com"
                        git config --global user.name "dygma0"
                        git add .
                        git status
                        git commit -m "Update image version to ${env.GITHUB_SHA}\n\nCo-authored-by: Jake Son <sonjeabin@gmail.com>"
                        git push origin HEAD:main
                    """)
                }
            }
        }
    }

    post {
        always {
            script {
                sh 'docker logout'
            }
        }
    }
}
