pipeline {

  tools {
        // Specify the name of the Maven installation defined in Jenkins
        maven 'Maven'
   }

  environment {
    dockerimagename = "abdessamadzebbara/spring-boot-k8s"
    dockerImage = ""
  }

  agent any

  stages {

    stage('Build App') {
        steps {
            // Build your Spring Boot application
            sh 'mvn clean package' // Adjust your build command
        }
    }
    
    stage('Build image') {
      steps{
        script {
          dockerImage = docker.build dockerimagename
        }
      }
    }

    stage('Pushing Image') {
      environment {
               registryCredential = 'dockerhub-credentials'
           }
      steps{
        script {
          docker.withRegistry( 'https://registry.hub.docker.com', registryCredential ) {
            dockerImage.push("latest")
          }
        }
      }
    }

    stage('Deploy to Kubernetes') {
      steps{
          withKubeConfig([credentialsId: 'mykubeconfig', serverUrl: 'https://192.168.49.2:8443']) {
            sh 'kubectl apply -f deployment-k8s.yaml'
        }
      }
    }

  }

  post {
      success {
          echo 'Deployment successful!'
      }
      failure {
          echo 'Deployment failed.'
      }
  }

}
