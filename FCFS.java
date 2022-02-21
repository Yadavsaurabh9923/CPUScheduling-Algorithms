import java.util.Scanner;  

public class FCFS{

    // SORT PROCESS AND ARRIVAL TIME FUNCTION
    static void sortProcess(int processes[],int noofProcesses, int arrivalTime[],int burstTime[]){
        // USING BUBBLE SORT
        int swap = 0;

        for(int i=0;i<noofProcesses-1;i++){
            for(int j=0;j<noofProcesses-i-1;j++){
                if(arrivalTime[j]>arrivalTime[j+1]){
                    swap=arrivalTime[j];
                    arrivalTime[j]=arrivalTime[j+1];
                    arrivalTime[j+1]=swap;

                    swap=processes[j];
                    processes[j]=processes[j+1];
                    processes[j+1]=swap;

                    swap=burstTime[j];
                    burstTime[j]=burstTime[j+1];
                    burstTime[j+1]=swap;
                }
                else if(arrivalTime[j]==arrivalTime[j+1]){
                    if(processes[j]>processes[j+1]){

                        swap=arrivalTime[j];
                        arrivalTime[j]=arrivalTime[j+1];
                        arrivalTime[j+1]=swap;
                        
                        swap=processes[j];
                        processes[j]=processes[j+1];
                        processes[j+1]=swap;
                        
                        swap=burstTime[j];
                        burstTime[j]=burstTime[j+1];
                        burstTime[j+1]=swap;
                    }
                }
            }
        }
    }
    
    // CALCULATE COMPLETION TIME FUNCTION
    static String findCompletionTime(int noofProcesses,int burstTime[], int arrivalTime[],int completionTime[],int processes[]){
        int completedProcess=0;
        int counter=0;
        int i=0;

        // GANTT CHART
        String ganttchart = "0. ";

        while(completedProcess<noofProcesses){
            if(arrivalTime[i]<=counter){
                counter=counter+burstTime[i];
                ganttchart=ganttchart.concat("P"+String.valueOf(processes[i])+" ."+String.valueOf(counter)+". ");
                completionTime[i]=counter;
                completedProcess++;
                i++;
            }
            else{
                counter++;
            }
        }
        
        return ganttchart;
    }

    // CALCULATE TURNAROUND TIME AND AVERAGE TURNAROUND TIME
    static void findTurnaround(int noofProcesses,int arrivalTime[],int completionTime[],int turnaroundTime[]){
        for(int i=0;i<noofProcesses;i++){
            turnaroundTime[i]=completionTime[i]-arrivalTime[i];
        }
    }

    // CALCULATE WATING TIME AND AVERAGE WATING TIME
    static void findWating(int noofProcesses,int burstTime[],int turnaroundTime[],int watingTime[]){
        for(int i=0;i<noofProcesses;i++){
            watingTime[i]=turnaroundTime[i]-burstTime[i];
        }
    }

    // MAIN CALCULATION FUNCTION
    static void findAverage(int processes[],int noofProcesses,int burstTime[], int arrivalTime[]){
        
        // COMPLETION-TIME ARRAY
        int completionTime[]=new int[noofProcesses];
        // GANTT CHART STRING
        
        // TURNAROUND-TIME ARRAY
        int turnaroundTime[]=new int[noofProcesses];
        float average_TT=0;

        // WATING-TIME ARRAY
        int watingTime[]=new int[noofProcesses];
        float average_WT=0;

        // FIND COMPLETION TIME
        String ganttchart= findCompletionTime(noofProcesses,burstTime,arrivalTime,completionTime,processes);

        // FIND TURNAROUND TIME
        findTurnaround(noofProcesses,arrivalTime,completionTime,turnaroundTime);

        // FIND WATING TIME
        findWating(noofProcesses,burstTime,turnaroundTime,watingTime);

        // DISPLAY TABLE
        System.out.println("-----------------------------");
        System.out.println("First Come First Serve (FCFS)");
        System.out.println("-----------------------------");
        System.out.println("Total number of processes: "+noofProcesses);
        System.out.println("-----------------------------");
        System.out.println("GANTT CHART : ('-' = CPU Idle ) ");
        System.out.println(ganttchart);
        System.out.println("-----------------------------");
        System.out.println("Process SR. \t Arrival Time \t Burst Time \t Completion Time \t Turnaround Time \t Wating Time");
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        for(int i=0;i<noofProcesses;i++){
            average_TT=average_TT+turnaroundTime[i];
            average_WT=average_WT+watingTime[i];
            System.out.println("Process "+processes[i]+"\t\t"+arrivalTime[i]+"\t\t"+burstTime[i]+"\t\t"+completionTime[i]+"\t\t\t"+turnaroundTime[i]+"\t\t\t"+watingTime[i]);
            
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        // DISPLAY AVERAGE TURNAROUND AND WATING TIME

        System.out.println("AVERAGE WATING TIME: "+(average_WT/(float)noofProcesses)+" units");
        System.out.println("AVERAGE TURNAROUND TIME: "+(average_TT/(float)noofProcesses)+" units");
    }
    public void Fcfs(int processes[],int noofProcesses,int arrivalTime[],int burstTime[]){

        // SORT PROCESSES ACCORDING TO ARRIVAL TIME
        sortProcess(processes,noofProcesses,arrivalTime,burstTime);


        // CALCULATE FCFS
        findAverage(processes,noofProcesses,burstTime,arrivalTime);
    }
}