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

    stage('Deploying container to Kubernetes') {
      steps {
        script {
          kubernetesDeploy(configs: "deployment-k8s.yaml", kubeconfigId: "mykubeconfig")
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
