package com.test.jenkin;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JenkinFileConfiguration {

    // http://localhost:9090/jenkin
    @GetMapping(value = "/jenkin", produces = MediaType.TEXT_PLAIN_VALUE)
    public String jenkinConfig() {
        return """
                pipeline {
                    agent any

                    tools {
                        maven 'maven 3.9.11'   // 👈 name from Jenkins tool config
                    }

                    stages {

                        stage('Git Pull') {
                            steps {
                                git branch: 'main', url: 'https://github.com/iamRakesh60/docker_test_app_3.git'
                            }
                        }

                        stage('Package Build') {
                            steps {
                                sh 'mvn clean package -DskipTests'
                            }
                        }

                        stage('Make Docker File') {
                            steps {
                                sh 'docker build -t iamrakesh60/docker_test:latest .'
                            }
                        }

                        stage('Docker Run') {
                            steps {
                                sh '''
                                    docker stop docker_test_container || true
                                    docker rm docker_test_container || true
                                    docker run -d --name docker_test_container -p 9090:9090 iamrakesh60/docker_test:latest
                                '''
                            }
                        }

                        // stage('Push to Docker Hub') {
                        //     steps {
                        //         withCredentials([usernamePassword(credentialsId: 'dockerhub-cred', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        //             sh '''
                        //                 echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                        //                 docker push iamrakesh60/docker_test:latest
                        //             '''
                        //         }
                        //     }
                        // }
                    }
                }
                """;
    }
}


/*


 pipeline {
    agent any

    tools {
        maven 'maven 3.9.11'   // 👈 name from Jenkins tool config
    }

    stages {
        stage('Git Pull') {
            steps {
                git branch: 'main', url: 'https://github.com/iamRakesh60/docker_test_app_3.git'
            }
        }

        stage('Package Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Make Docker File') {
            steps {
                sh 'docker build -t iamrakesh60/docker_test:latest .'
            }
        }

        stage('Docker Run') {
            steps {
                sh '''
                docker stop docker_test_container || true
                docker rm docker_test_container || true
                docker run -d --name docker_test_container -p 9090:9090 iamrakesh60/docker_test:latest
                '''
            }
        }

        // stage('Push to Docker Hub') {
        //     steps {
        //         withCredentials([usernamePassword(credentialsId: 'dockerhub-cred', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
        //             sh '''
        //             echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
        //             docker push iamrakesh60/docker_test:latest
        //             '''
        //         }
        //     }
        // }
    }
}



*/