import java.util.Scanner; 
import java.io.*;
import java.util.*;

public class Priority {

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

    public static String findCompletionTime(int processes[],int noofProcesses,int arrivalTime[],int burstTime[],int priority[],int completionTime[]){
        int remainingarrivalTime[]=new int[noofProcesses];
        int remainingbursTime[]=new int[noofProcesses];
        int remainingPriority[] = new int[noofProcesses];

        // GANTT CHART
        String ganttchart = "0. ";
        
        for(int i=0;i<noofProcesses;i++){
            remainingarrivalTime[i]=arrivalTime[i];
            remainingbursTime[i]=burstTime[i];
            remainingPriority[i]=priority[i];
        }
        
        // INITILIZE COUNTER
        int counter=0;
        int completedProcesses=0;
        int maxPrior=Integer.MIN_VALUE;
        int index=0;
        

        while(completedProcesses<noofProcesses){
            boolean check=false;

            for(int i=0;i<noofProcesses;i++){
                if((remainingarrivalTime[i]<=counter) && (remainingPriority[i]>=maxPrior) && (remainingbursTime[i]>0)){
                    maxPrior=remainingPriority[i];
                    index=i;
                    check=true;
                }
            }

            if(check==false){
                counter++;
                continue;
            }
            
            remainingbursTime[index]--;    
            counter++;
            
            if(remainingbursTime[index]==0){
                completionTime[index]=counter;
                ganttchart=ganttchart.concat("P"+String.valueOf(processes[index])+" ."+String.valueOf(counter)+". ");
                remainingPriority[index]=0;
                maxPrior=Integer.MIN_VALUE;
                remainingarrivalTime[index]=Integer.MAX_VALUE;
                completedProcesses++;
            }

        }
        
        return ganttchart;
    }
    
    public static void findAverage(int processes[],int noofProcesses,int arrivalTime[],int burstTime[],int priority[]){
        int completionTime[] = new int[noofProcesses];
        int watingTime[] = new int[noofProcesses];
        int turnAroundTime[] = new int[noofProcesses];
        int  average_WT = 0, average_TT = 0;

        String ganttchart= findCompletionTime(processes,noofProcesses,arrivalTime,burstTime,priority,completionTime);

        findTurnaroundTime(processes,noofProcesses,arrivalTime,burstTime,turnAroundTime,completionTime);

        findWatingTime(processes,noofProcesses,arrivalTime,burstTime,watingTime,turnAroundTime);

        // DISPLAY TABLE
        System.out.println("-----------------------------");
        System.out.println("Total number of processes: "+noofProcesses);
        System.out.println("-----------------------------");
        System.out.println("GANTT CHART : ('-' = CPU Idle ) ");
        System.out.println(ganttchart);
        System.out.println("-----------------------------");
        System.out.println("Process SR. \t Arrival Time \t Burst Time \t Completion Time \t Turnaround Time \t Wating Time \t Priority");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        for(int i=0;i<noofProcesses;i++){
            average_TT=average_TT+turnAroundTime[i];
            average_WT=average_WT+watingTime[i];
            System.out.println("Process "+processes[i]+"\t\t"+arrivalTime[i]+"\t\t"+burstTime[i]+"\t\t"+completionTime[i]+"\t\t\t"+turnAroundTime[i]+"\t\t\t"+watingTime[i]+"\t\t"+priority[i]);
            
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        // DISPLAY AVERAGE TURNAROUND AND WATING TIME

        System.out.println("AVERAGE WATING TIME: "+(average_WT/(float)noofProcesses)+" units");
        System.out.println("AVERAGE TURNAROUND TIME: "+(average_TT/(float)noofProcesses)+" units"); 
    }

    public void Priority(int processes[],int noofProcesses,int arrivalTime[],int burstTime[],int priority[]){
        findAverage(processes,noofProcesses,arrivalTime,burstTime,priority);

    }
}