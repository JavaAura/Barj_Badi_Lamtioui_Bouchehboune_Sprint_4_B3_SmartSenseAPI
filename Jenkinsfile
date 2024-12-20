pipeline {
    agent any

    tools {
        maven "Maven"
    }
    environment{
        DOCKER_HUB_REPO = 'anwar721/smartsense'
    }

    parameters {
        string(name: 'BRANCH_NAME', defaultValue: 'main', description: 'Nom de la branche à construire')
    }

    stages {
        stage('clone repo'){
            steps{
                git branch: params.BRANCH_NAME, url: 'https://github.com/JavaAura/Barj_Badi_Lamtioui_Bouchehboune_Sprint_4_B3_SmartSenseAPI'
            }
        }
        stage('Build Artifact'){
            steps{
                bat '''
                    cd ./smartsense
                    mvn -B -DskipTests clean package 
                '''
                archiveArtifacts artifacts: 'smartsense/target/*.jar', fingerprint: true
                stash name: 'jar-artifact', includes: 'smartsense/target/*.jar'
            }
        }
        
        stage('Docker Build') {
            steps {
                unstash 'jar-artifact' 
                bat "docker build -t ${DOCKER_HUB_REPO}:latest ."
            }
        }

        stage('Docker Push') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'docker-token', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                        sh 'echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin'
                    }
                    
                    sh "docker push ${DOCKER_HUB_REPO}:latest"
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh '''
                        cd ./smartsense
                        mvn sonar:sonar \
                            -Dsonar.projectKey=smartsense \
                            -Dsonar.java.binaries=target/classes \
                            -Dsonar.coverage.exclusions=**/dto/**,**/model/**,**/config/**
                    '''
                }
            }
        }
    }
}
