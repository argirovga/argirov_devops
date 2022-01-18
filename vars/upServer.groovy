
def call (String link) {
    pipeline {
        agent any
        tools {
            jdk 'jdk_9'
            maven 'maven_3.8.4'
        }
        stages {
            stage('Clone project') {
                steps {
                    script {
                        git credentialsId: 'gitlab_repo', url: link
                        sh "ls -la"
                        sh "git branch -a"
                        sh "git checkout master"
                    }
                }
            }
            stage('Run') {
                steps {
                    script {
                        sh 'mvn -B -Dmaven.test.skip clean package'
                    }
                }
            }
        }
    }
}