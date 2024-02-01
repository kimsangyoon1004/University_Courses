#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <signal.h>

int main(int argc, char *argv[]){
	pid_t pid;
	int status;
	

	printf("Process start to fork\n");
	pid = fork();
	
	void signal(int signal){
		switch(signal){
			case 6: 
				printf("child process get SIGABRT signal\n"); //abort
				break;
			case 14: 
				printf("child process get SIGALRM signal\n"); //alarm
				break;
			case 7: 
				printf("child process get SIGBUS signal\n"); //bus
				break;
			case 8: 
				printf("child process get SIGFPE signal\n"); //floating
				break;
			case 1: 
				printf("child process get SIGHUP signal\n"); //hangup
				break;
			case 4: 
				printf("child process get SIGILL signal\n"); //illegal_instr
				break;
			case 2: 
				printf("child process get SIGINT signal\n"); //interrupt
				break;
			case 9: 
				printf("child process get SIGKILL signal\n"); //kill
				break;
			case 13: 
				printf("child process get SIGPIPE signal\n"); //pipe
				break;
			case 3: 
				printf("child process get SIGQUIT signal\n"); //quit
				break;
			case 11: 
				printf("child process get SIGSEVG signal\n"); //segment_fault
				break;
			case 15: 
				printf("child process get SIGTERM signal\n"); //terminate
				break;
			case 5:
				printf("child process get SIGTRAP signal\n"); 
				//trap	
		}
	}

	if(pid > 0){
		printf("I'm the Parent Process, my pid = %d\n", getpid());
		waitpid(pid, &status, WUNTRACED);			//report stopped child process && terminated process
		printf("Parent process receives SIGCHLD signal\n");
		if(WIFEXITED(status)){
			printf("Normal termination with EXIT STATUS = %d\n", WEXITSTATUS(status));
		}
		
		else if(WIFSIGNALED(status)){
			signal(WTERMSIG(status));
			
		}

		else if(WIFSTOPPED(status)){
			printf("child process get SIGSTOP signal\n");
		}
		

	}
	else{
		char *arg[argc];
		for(int i = 0; i < argc - 1; i++){
			arg[i] = argv[i + 1];			//argv[0] = program1 ,  argv[1] = normal , argc = 2;
		}
		arg[argc - 1] = NULL;
		sleep(1);		//make parent process start first
		printf("I'm the Child Process, my pid = %d\n", getpid());
		printf("Child process start to execute test program:\n");
		execve(arg[0], arg, NULL);

	}
	/* fork a child process */
	
	/* execute test program */ 
	
	/* wait for child process terminates */
	
	/* check child process'  termination status */
	return 0;
}