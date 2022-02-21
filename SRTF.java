import java.util.Scanner; 

public class SRTF {
    
    static void findWating(int noofProcesses,int burstTime[],int turnaroundTime[],int watingTime[]){
        for(int i=0;i<noofProcesses;i++){
            watingTime[i]=turnaroundTime[i]-burstTime[i];
        }
    }

    static void findTurnaround(int noofProcesses,int arrivalTime[],int completionTime[],int turnaroundTime[]){
        for(int i=0;i<noofProcesses;i++){
            turnaroundTime[i]=completionTime[i]-arrivalTime[i];
        }
    }

    static String findCompletionTime(int noofProcesses,int burstTime[],int arrivalTime[],int completionTime[],int processes[]){
        int tempburstTime[] = new int[noofProcesses];
        int temparrivalTime[] = new int[noofProcesses];
        
        // GANTT CHART
        String ganttchart = "0. ";

        for(int i=0;i<noofProcesses;i++){
            tempburstTime[i]=burstTime[i];
            temparrivalTime[i]=arrivalTime[i];
        }

        int completeProcesses=0,minimumBurst=Integer.MAX_VALUE;
        int smallest=0;
        

        int counter=0;
        boolean check=false;

        while(completeProcesses<noofProcesses){

            for(int i=0;i<noofProcesses;i++){
                if((temparrivalTime[i]<=counter) && (tempburstTime[i]>0) && (tempburstTime[i]<minimumBurst)){
                    minimumBurst = tempburstTime[i];
                    smallest = i;
                    check=true;
                }
            }

            if (check == false) {
                counter++;
                continue;
            }

            tempburstTime[smallest]--;

            minimumBurst=tempburstTime[smallest];
            if(minimumBurst==0){
                minimumBurst=Integer.MAX_VALUE;
            }

            if(tempburstTime[smallest]==0){
                ganttchart=ganttchart.concat("P"+String.valueOf(processes[smallest])+" ."+String.valueOf(counter)+". ");
                completeProcesses++;
                check=false;

                completionTime[smallest]=counter+1;
            }

            counter++;
            
        }
        
        return ganttchart;
    }

    static void findAverage(int processes[],int noofProcesses,int arrivalTime[],int burstTime[]){
        int completionTime[] = new int[noofProcesses];
        int watingTime[] = new int[noofProcesses];
        int turnaroundTime[] = new int[noofProcesses];
        int  average_WT = 0, average_TT = 0;

        String ganttchart= findCompletionTime(noofProcesses,burstTime,arrivalTime,completionTime,processes);

        // FIND TURNAROUND TIME
        findTurnaround(noofProcesses,arrivalTime,completionTime,turnaroundTime);

        // FIND WATING TIME
        findWating(noofProcesses,burstTime,turnaroundTime,watingTime);


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
            average_TT=average_TT+turnaroundTime[i];
            average_WT=average_WT+watingTime[i];
            System.out.println("Process "+processes[i]+"\t\t"+arrivalTime[i]+"\t\t"+burstTime[i]+"\t\t"+completionTime[i]+"\t\t\t"+turnaroundTime[i]+"\t\t\t"+watingTime[i]);
            
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        // DISPLAY AVERAGE TURNAROUND AND WATING TIME

        System.out.println("AVERAGE WATING TIME: "+(average_WT/(float)noofProcesses)+" units");
        System.out.println("AVERAGE TURNAROUND TIME: "+(average_TT/(float)noofProcesses)+" units");
    }

    public void Srtf(int processes[],int noofProcesses,int arrivalTime[],int burstTime[]){
        findAverage(processes,noofProcesses,arrivalTime,burstTime);

    }
}
