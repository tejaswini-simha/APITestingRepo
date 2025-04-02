pipeline {
    agent any

    environment {
        // Set the location of the Allure results folder if necessary
        ALLURE_RESULTS = './allure-results'
    }

    stages {
        stage('Checkout Source') {
            steps {
                // Pull code from the Git repository configured in the Jenkins job
                checkout scm
            }
        }

        stage('Install Dependencies and Build the project') {
            steps {
				bat 'mvn clean install'
            }
        }

    }

        post {
            always {
                allure includeProperties:
                    false,
                    jdk: '',
                    results: [[path: './target/allure-results']]
            }
        }
}
