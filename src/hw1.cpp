#include <iostream>
#include <list>
#include <vector>
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <algorithm>

using namespace std;

class Process{
private:
    int pid;
    int time;
    int priority;
public:
    Process(){pid = 0; time = 0; priority = 0;}
    void set_pid(int a) { pid = a;}
    void set_time(int b) {time = b;}
    void set_priority(int c){priority = c;}
    int get_pid(){return pid;}
    int get_time(){return time;}
    int get_priority(){return priority;}
};

Process creat_new_process (Process parent_p);
int main()
{
    //int quantum, time, prior;
    //int pid = 0;
    
    int x;
    int quantum;
    int pid = 0;
    vector <int> data;
    vector <Process> process;

    //ifstream Infile;
    //Infile.open("input1.txt");
    
    
    //while(Infile >> x)
    
    while (cin>>x){
        data.push_back(x);
    }
    
    quantum = data[0];
    data.erase(data.begin());  //delete quantum from vector
    
    int num_of_process = (int)(data.size()/2);
    process.resize(num_of_process);
    
    
    int p_index = 0;
    for (int i = 0; i < data.size(); i++)
    {
        if(i % 2 == 0)
        {
            process[p_index].set_time(data[i]);
            process[p_index].set_pid(pid);
        }
        else
        {
            process[p_index].set_priority(data[i]);
            p_index += 1;
            pid+= 1;
        }
    }
    
    //creating the child process if time is greater than 3
    
    vector <Process> child_process;
    for (int i = 0; i < process.size(); i++)
    {
        if(process[i].get_time() > 3)
        {
            int old_time = process[i].get_time();
            process[i].set_time(3);
            int new_time = old_time - 3;
            int counter = 1;
            bool flag = true;
            while(flag)
            {
                if(new_time > 3)
                {
                    Process new_child = creat_new_process(process[i]);
                    new_child.set_time(3);
                    child_process.push_back(new_child);
                    new_time = new_time - 3;
                    counter += 1;
                }
                else
                {
                    Process new_child = creat_new_process(process[i]);
                    new_child.set_time(new_time);
                    child_process.push_back(new_child);
                    flag = false;
                }
            }
        }
    }

    //sort parent process base on priority
    sort(process.begin(), process.end(), [](Process a, Process b){
        return a.get_priority()  < b.get_priority();
    });
    
    //sort children process base on priority
    sort(child_process.begin(), child_process.end(), [](Process a, Process b){
        return a.get_priority()  < b.get_priority();
    });
    
    //copy
    vector <Process> child_temp;
    child_temp.resize(child_process.size());
    
    
    for (int i = 0; i < child_process.size(); i++)
    {
        child_temp[i].set_pid(child_process[i].get_pid());
        child_temp[i].set_time(child_process[i].get_time());
        child_temp[i].set_priority(child_process[i].get_priority());
    }
    
    //this is to merge both vector children and parents
    for (int i = 0; i < child_process.size(); i++)
    {
        for (int j = 0; j < process.size(); j++)
        {
            if(child_process[i].get_priority() < process[j].get_priority())
            {
                process.insert(process.begin()+j,child_process[i]);
                child_temp.erase(child_temp.begin());
                break;
            }
        }
    }
    
    if(child_temp.size() != 0)
    {
        for (int i = 0; i < child_temp.size(); i++)
            process.push_back(child_temp[i]);
    }
    
    
    pid_t act_pid;
    act_pid = fork();
    if(act_pid > 0)
    {
        cout << "Scheduling queue: " << endl;
        for (int i = 0; i < process.size(); i++)
        {
            if(i == process.size() - 1)
            {
                cout << "(" << process[i].get_pid() << "," << process[i].get_time() << "," << process[i].get_priority() << ")" << endl;
            }
            else
            {
                cout << "(" << process[i].get_pid() << ","<< process[i].get_time() << ","  << process[i].get_priority() << "),";
            }
        }
        cout << endl;
        wait(NULL);
    }
    else
    {
        for (int i = 0; i < process.size(); i++)
        {
            if(process[i].get_time() < 3 )
            {
                cout << "Process: " << process[i].get_pid() << ":" << "exec time = " << process[i].get_time() << " priority = " << process[i].get_priority() << endl;
                sleep(process[i].get_time());
                cout << "Process: " << process[i].get_pid() << " ends" << endl;
                

            }
            else if(process[i].get_time() == 3)
            {
                bool repeat = false;
                for (int j = i+1; j <process.size(); j++)
                {
                    if(process[j].get_pid() == process[i].get_pid())
                    {
                        repeat = true;
                        break;
                    }
                }
                if(repeat)
                {
                    cout << "Process: " << process[i].get_pid() << ":" << "exec time = " << process[i].get_time() << " priority = " << process[i].get_priority() << endl;
                    sleep(process[i].get_time());
                }
                else
                {
                    cout << "Process: " << process[i].get_pid() << ":" << "exec time = " << process[i].get_time() << " priority = " << process[i].get_priority() << endl;
                    sleep(process[i].get_time());
                    cout << "Process: " << process[i].get_pid() << " ends" << endl;

                }
            }
        }
    }
    
    
    return 0;
    
}

Process creat_new_process (Process parent_p)
{
    Process child;
    child.set_pid(parent_p.get_pid());
    child.set_priority(parent_p.get_priority());
    return child;
}



