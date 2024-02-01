#include <linux/module.h>
#include <linux/sched.h>
#include <linux/pid.h>
#include <linux/kthread.h>
#include <linux/kernel.h>
#include <linux/err.h>
#include <linux/slab.h>
#include <linux/printk.h>
#include <linux/jiffies.h>
#include <linux/kmod.h>
#include <linux/fs.h>
MODULE_LICENSE("GPL");


#define WTERMSIG(status) ((status)&0x7f)
#define WIFEXITED(status) (WTERMSIG(status) == 0)
#define WIFSIGNALED(status) (((signed char)(((status)&0x7f) + 1) >> 1) > 0)
#define WIFSTOPPED(status) (((status)&0xff) == 0x7f)

static struct task_struct *task;
struct wait_opts {		// Copied from kernel/exit.c
        enum pid_type wo_type;
        int wo_flags;
        struct pid *wo_pid;

        struct waitid_info *wo_info;
        int wo_stat;
        struct rusage *wo_rusage;

        wait_queue_entry_t child_wait;
        int notask_error;
};
extern long do_wait(struct wait_opts *wo); 

extern struct filename *getname_kernel(const char *filename);

extern pid_t kernel_clone(struct kernel_clone_args *kargs);

extern int do_execve(struct filename *filename,
	const char __user *const __user *__argv,
	const char __user *const __user *__envp);



int my_exec(void){  // get the test file 
	int result;
	const char test_path[]= "/tmp/test";
	struct filename * my_filename = getname_kernel(test_path);


	printk("[program2] : child process");
	result = do_execve(my_filename, NULL, NULL);
	if(!result){
		return 0;
	}
	else{
		do_exit(result);
	}
}

void signal(int signal){
 	switch(signal){	
			case 6: 
				printk("[program2] : get SIGABRT signal\n"); //abort
				printk("[program2] : child process gets abort error\n");
				printk("[program2] : The return signal is %d\n", signal);
				break;
			case 14: 
				printk("[program2] : get SIGALRM signal\n"); //alarm
				printk("[program2] : child process gets alarm error\n");
				printk("[program2] : The return signal is %d\n", signal);
				break;
			case 7: 
				printk("[program2] : get SIGBUS signal\n"); //bus
				printk("[program2] : child process gets bus error\n");
				printk("[program2] : The return signal is %d\n", signal);
				break;
			case 8: 
				printk("[program2] : get SIGFPE signal\n"); //floating
				printk("[program2] : child process gets floating error\n");
				printk("[program2] : The return signal is %d\n", signal);
				break;
			case 1: 
				printk("[program2] : get SIGHUP signal\n"); //hangup
				printk("[program2] : child process gets hangup error\n");
				printk("[program2] : The return signal is %d\n", signal);
				break;
			case 4: 
				printk("[program2] : get SIGILL signal\n"); //illegal_instr
				printk("[program2] : child process gets illegal_instr error\n");
				printk("[program2] : The return signal is %d\n", signal);
				break;
			case 2: 
				printk("[program2] : get SIGINT signal\n"); //interrupt
				printk("[program2] : child process gets interrupt error\n");
				printk("[program2] : The return signal is %d\n", signal);
				break;
			case 9: 
				printk("[program2] : get SIGKILL signal\n"); //kill
				printk("[program2] : child process gets kill error\n");
				printk("[program2] : The return signal is %d\n", signal);
				break;
			case 13: 
				printk("[program2] : get SIGPIPE signal\n"); //pipe
				printk("[program2] : child process gets pipe error\n");
				printk("[program2] : The return signal is %d\n", signal);
				break;
			case 3: 
				printk("[program2] : get SIGQUIT signal\n"); //quit
				printk("[program2] : child process gets quit error\n");
				printk("[program2] : The return signal is %d\n", signal);
				break;
			case 11: 
				printk("[program2] : get SIGSEVG signal\n"); //segment_fault
				printk("[program2] : child process gets segment_fault error\n");
				printk("[program2] : The return signal is %d\n", signal);
				break;
			case 15: 
				printk("[program2] : get SIGTERM signal\n"); //terminate
				printk("[program2] : child process terminated\n");
				printk("[program2] : The return signal is %d\n", signal);
				break;
			case 5:
				printk("[program2] : get SIGTRAP signal\n"); //trap	
				printk("[program2] : child process gets trap error\n");
				printk("[program2] : The return signal is %d\n", signal);
		}
}

int status;
void my_wait(pid_t pid){
  	int a;
  	struct wait_opts wo;
  	struct pid *wo_pid = NULL;
  	enum pid_type type;
  	type = PIDTYPE_PID;
  	wo_pid = find_get_pid(pid);

  	wo.wo_type = type;
  	wo.wo_pid = wo_pid;
  	wo.wo_flags = WEXITED|WUNTRACED;
  	wo.wo_info = NULL;
  
  	wo.wo_stat = (int __user)status;
 	wo.wo_rusage = NULL;

  	a = do_wait(&wo);
  
 
  
	if (WIFEXITED(wo.wo_stat)) {
		printk("[program2] : child process exit normally\n");
	} else if (WIFSIGNALED(wo.wo_stat)) {
		signal(WTERMSIG(wo.wo_stat));
	} else if (WIFSTOPPED(wo.wo_stat)) {
		printk("[program2] : get SIGSTOP signal\n");
		printk("[program2] : child process gets stop error\n");
		printk("[program2] : The return signal is 19\n");
	} 
  	put_pid(wo_pid);
  	return;
}

int my_fork(void *argc){		
	struct kernel_clone_args kargs = {
	.flags = CLONE_FS,
	.exit_signal = SIGCHLD,
	.stack = (unsigned long)my_exec,
	.child_tid = NULL,
	.parent_tid = NULL,
	.stack_size = 0,
	.tls = 0,
};
	//set default sigaction for current process
	pid_t pid;
	int i;
	struct k_sigaction *k_action = &current->sighand->action[0];
	for(i=0;i<_NSIG;i++){
		k_action->sa.sa_handler = SIG_DFL;
		k_action->sa.sa_flags = 0;
		k_action->sa.sa_restorer = NULL;
		sigemptyset(&k_action->sa.sa_mask);
		k_action++;
	}

	/* fork a process using kernel_clone or kernel_thread */
	
	/* wait until child process terminates */
	
	pid = kernel_clone(&kargs);		
	printk("[program2] : The child process has pid = %d\n",pid);
	printk("[program2] : This is the parent process, pid = %d\n",current->pid);
	my_wait(pid);
	return 0;
	}

	
static int __init program2_init(void){
	printk("[program2] : Module_init\n");
	printk("[program2] : Module_init create kthread start\n");
	task = kthread_create(&my_fork,NULL,"MyThread");  /* create a kernel thread to run my_fork */

	/* wake up new thread if ok */
	if(!IS_ERR(task)){
		printk("[program2] : Module_init kthread starts\n");
		wake_up_process(task);
	}
	return 0;
}

static void __exit program2_exit(void){
	printk("[program2] : Module_exit\n");
	
}

module_init(program2_init);
module_exit(program2_exit);
