pipeline{
	agent any
	tools{
    	maven 'maven'
	}
	stages{
    	stage('Build Maven'){
        	steps{
            	checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/ZuneNebula/jenkins-test']])
            	sh 'mvn clean install -DskipTests'
        	}
    	}
    	stage('lint'){
        	steps{
            	sh 'mvn pmd:check -X'
        	}
    	}
    	stage('test'){
        	steps{
            	sh 'mvn clean test --also-make'
        	}
       	
    	}
	}
}
