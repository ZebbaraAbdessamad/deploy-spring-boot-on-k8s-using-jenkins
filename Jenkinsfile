pipeline {

  environment {
    dockerimagename = "abdessamadzebbara/spring-boot-k8s"
    dockerImage = ""
  }

  agent any

  stages {

    stage('Checkout Source') {
      steps {
        git 'https://github.com/ZebbaraAbdessamad/deploy-spring-boot-on-k8s-using-jenkins.git'
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
               registryCredential = 'Docker-Hube-Credentials'
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