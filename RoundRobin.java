import java.util.Scanner; 
import java.io.*;
import java.util.*;


class Process{
    int processID;
    int arrivalTime;
    int burstTime;

    Process(int processID, int arrivalTime, int burstTime){
        this.processID = processID;
        this.arrivalTime = arrivalTime; 
        this.burstTime = burstTime;
    }
}


public class RoundRobin {

    public static void findWatingTime(int processes[],int noofProcesses,int arrivalTime[],int burstTime[],int watingTime[],int turnAroundTime[]){
        for(int i=0;i<noofProcesses;i++){
            watingTime[i]=turnAroundTime[i]-burstTime[i];
        }
    }

    public static void findTurnaroundTime(int processes[],int noofProcesses,int arrivalTime[],int burstTime[],int turnAroundTime[],int completionTime[]){
        for(int i=0;i<noofProcesses;i++){
            turnAroundTime[i]=completionTime[i]-arrivalTime[i];
        }
    }

    public static String findCompletionTime(int processes[],int noofProcesses,int arrivalTime[],int burstTime[],int completionTime[],int tq){
        int remainingbursTime[]=new int[noofProcesses];
        int tempArrivalTime[]=new int[noofProcesses];

        // GANTT CHART
        String ganttchart = "0. ";

        ArrayList<Integer> readyQueue= new ArrayList<Integer>(200);
        // ASSUME PROCESS ARE SORTED ACCORDING TO ARRIVAL TIME (IF NOT ADD A SORT FUNCTION)
        
        for(int i=0;i<noofProcesses;i++){
            remainingbursTime[i]=burstTime[i];
            tempArrivalTime[i]=arrivalTime[i];
        }
        
        int counter=0;
        
        // ADD PROCESS IN READY QUEUE IN THE STARTING
        for(int k=0;k<noofProcesses;k++){
            if(tempArrivalTime[k]<=counter){
                readyQueue.add(k);
            }
        }

        int completedProcess=0;
        int j=0,i=0;

        while(completedProcess<noofProcesses){

            boolean done=true;
            
            try {
                i=readyQueue.get(j);
            }
            catch(Exception e) {
                i=readyQueue.get(readyQueue.size()-1);
            }

            if(remainingbursTime[i]>0){
                done=false;

                if(remainingbursTime[i]>tq){
                    counter=counter+tq;
                    
                    // INSERT ARRIVED PROCESS IN READY_QUEUE
                    for(int k=1;k<noofProcesses;k++){
                        if(tempArrivalTime[k]<=counter){
                            readyQueue.add(k);
                            // ONCE THE PROCESS IS ARRIVED MARK IT WITH MAX INTEGER VALUE SO IT DOES GO IN THE READY QUEUE AGAIN
                            tempArrivalTime[k]=Integer.MAX_VALUE;
                        }
                    }

                    remainingbursTime[i]=remainingbursTime[i]-tq;
                    // ADD INCOMPLETE PROCESS IN READY_QUEUE
                    readyQueue.add(i);
                    j++;
                }
                else{
                    counter=counter+remainingbursTime[i];
                    // INSERT ARRIVED PROCESS IN READY_QUEUE
                    for(int k=1;k<noofProcesses;k++){
                        if(tempArrivalTime[k]<=counter){
                            readyQueue.add(k);
                            // ONCE THE PROCESS IS ARRIVED MARK IT WITH MAX INTEGER VALUE SO IT DOES GO IN THE READY QUEUE AGAIN
                            tempArrivalTime[k]=Integer.MAX_VALUE;
                        }
                    }
                    completionTime[i]=counter;
                    remainingbursTime[i]=0;
                    completedProcess++;
                    j++;
                }
            }
            else{
                done=false;
                counter++;
                // INSERT ARRIVED PROCESS IN READY_QUEUE
                for(int k=1;k<noofProcesses;k++){
                    if(tempArrivalTime[k]<=counter){
                        readyQueue.add(k);
                        // ONCE THE PROCESS IS ARRIVED MARK IT WITH MAX INTEGER VALUE SO IT DOES GO IN THE READY QUEUE AGAIN
                        tempArrivalTime[k]=Integer.MAX_VALUE;
                    }
                }
            }

            if(done==true){
                break;
            }
        }

        // DISPLAYING THE READY QUEUE
        System.out.print("\nReady Queue: ");
        System.out.print("[");
        for(int k=0;k<readyQueue.size();k++){
            System.out.print("Process"+(readyQueue.get(k)+1)+", ");
        }
        System.out.println("]");
        
        return ganttchart;
    }
    
    public static void findAverage(int processes[],int noofProcesses,int arrivalTime[],int burstTime[],int tq){
        int completionTime[] = new int[noofProcesses];
        int watingTime[] = new int[noofProcesses];
        int turnAroundTime[] = new int[noofProcesses];
        int  average_WT = 0, average_TT = 0;

        String ganttchart=findCompletionTime(processes,noofProcesses,arrivalTime,burstTime,completionTime,tq);

        findTurnaroundTime(processes,noofProcesses,arrivalTime,burstTime,turnAroundTime,completionTime);

        findWatingTime(processes,noofProcesses,arrivalTime,burstTime,watingTime,turnAroundTime);

        // DISPLAY TABLE
        System.out.println("-----------------------------");
        System.out.println("Total number of processes: "+noofProcesses);
        System.out.println("-----------------------------");
        System.out.println("GANTT CHART : ('-' = CPU Idle ) ");
        System.out.println(ganttchart);
        System.out.println("-----------------------------");
        System.out.println("Process SR. \t Arrival Time \t Burst Time \t Completion Time \t Turnaround Time \t Wating Time");
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        for(int i=0;i<noofProcesses;i++){
            average_TT=average_TT+turnAroundTime[i];
            average_WT=average_WT+watingTime[i];
            System.out.println("Process "+processes[i]+"\t\t"+arrivalTime[i]+"\t\t"+burstTime[i]+"\t\t"+completionTime[i]+"\t\t\t"+turnAroundTime[i]+"\t\t\t"+watingTime[i]);
            
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        // DISPLAY AVERAGE TURNAROUND AND WATING TIME

        System.out.println("AVERAGE WATING TIME: "+(average_WT/(float)noofProcesses)+" units");
        System.out.println("AVERAGE TURNAROUND TIME: "+(average_TT/(float)noofProcesses)+" units"); 
    }

    public void Rr(int processes[],int noofProcesses,int arrivalTime[],int burstTime[]){
        Scanner scan = new Scanner(System.in);

        // ENTER THE TIME QUANTUM
        System.out.print("Enter the Time Quantum: ");
        int tq = scan.nextInt();

        findAverage(processes,noofProcesses,arrivalTime,burstTime,tq);

    }
}
