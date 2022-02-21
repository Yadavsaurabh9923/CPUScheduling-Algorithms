import java.util.Scanner;
import java.lang.Math;   

public class CPUScheduling {
    public static void main(String[] args) {

        // CLEAR CLASS
        Clear clr = new Clear();

        // Scanner Class
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Enter the Number of Processes: ");
        int noofProcesses = scan.nextInt();

        int processes[] = new int[noofProcesses];
        int arrivalTime[] = new int[noofProcesses];
        int burstTime[] = new int[noofProcesses];

        int input_choice=0;

        // TAKING USER CHOICE
        while(true){
            System.out.println("------------------------------------------------");
            System.out.println("1] Manually enter Arrival Time & Burst Time.");
            System.out.println("2] Randomly input Arrival Time & Burst Time.");
            System.out.println("------------------------------------------------");
            System.out.print("Enter your choice : ");
            input_choice = scan.nextInt();

            if((input_choice==1) || (input_choice==2)){
                break;
            }
            else{
                System.out.println("INVALID CHOICE ! \n\n");
            }
        }

        // TAKING ARRIVAL AND BURST VALUES
        if(input_choice==1){
            System.out.println("\n\nEnter the Arrival Time & Burst Time");
            System.out.println("--------------------------------------");

            for(int i=0;i<noofProcesses;i++){
                processes[i]=i+1;
                System.out.print("Arrival Time for Process "+(i+1)+": ");
                arrivalTime[i]=scan.nextInt();

                System.out.print("Burst Time for Process "+(i+1)+": ");
                burstTime[i]=scan.nextInt();

                System.out.print("\n");
            }
        }
        else{
            System.out.println("\n\nRandom Arrival Time & Burst Time");
            System.out.println("--------------------------------------");
            System.out.print("Enter the Lower Range for Arrival Time : ");
            int min1 = scan.nextInt();
            System.out.print("\nEnter the Upper Range for Arrival Time : ");
            int max1 = scan.nextInt();

            for(int i=0; i<noofProcesses; i++){
                processes[i]=i+1;
                arrivalTime[i]=(int)Math.floor(Math.random()*(max1-min1+1)+min1);
            }

            System.out.print("\n\n");

            System.out.print("Enter the Lower Range for Burst Time : ");
            int min2 = scan.nextInt();
            System.out.print("\nEnter the Upper Range for Burst Time : ");
            int max2 = scan.nextInt();

            for(int i=0; i<noofProcesses; i++){
                burstTime[i]=(int)Math.floor(Math.random()*(max2-min2+1)+min2);
            }
        }

        for(int i=0; i<noofProcesses; i++){
            System.out.println(arrivalTime[i]+" , "+burstTime[i]);
        }

        // ------------------------------------------------------------------------------------------------------------------

        int algo_choice = 0;
        boolean done = true;
        while(done){
            System.out.println("-----------------------------------------------------------------");
            System.out.println("1] First Come First Serve (FCFS)");
            System.out.println("2] Shortest Job First (SJF)");
            System.out.println("3] Shortest Time Remaining First (SRTF)");
            System.out.println("4] Round Robin(RR)");
            System.out.println("5] Priority Scheduling");
            System.out.println("6] EXIT");
            System.out.println("-----------------------------------------------------------------");
            System.out.print("Enter your Algorithm choice : ");
            algo_choice = scan.nextInt();

            switch(algo_choice){
                case 1: FCFS fcfs = new FCFS();  
                        // Clear CMD
                        clr.cls();
                        fcfs.Fcfs(processes,noofProcesses,arrivalTime,burstTime);
                        System.out.println("\n\n\n");
                        break;
                
                case 2: SJF sjf = new SJF();
                        // Clear CMD
                        clr.cls();
                        sjf.Sjf(processes,noofProcesses,arrivalTime,burstTime);
                        System.out.println("\n\n\n");
                        break;

                case 3: SRTF srtf = new SRTF();
                        // Clear CMD
                        clr.cls();
                        srtf.Srtf(processes,noofProcesses,arrivalTime,burstTime);
                        System.out.println("\n\n\n");
                        break;
                
                case 4: RoundRobin RR = new RoundRobin();
                        // Clear CMD
                        clr.cls();
                        RR.Rr(processes,noofProcesses,arrivalTime,burstTime);
                        System.out.println("\n\n\n");
                        break;

                case 5: Priority priority_algo = new Priority();
                        // Clear CMD
                        clr.cls();

                        int priority[] = new int[noofProcesses];
                        System.out.println("Enter Priority (Higher Priority Will be executed first)");
                        for(int i=0;i<noofProcesses; i++){
                            System.out.print("Enter the priority for Process "+processes[i]+" : ");
                            priority[i]=scan.nextInt();
                        }

                        priority_algo.Priority(processes,noofProcesses,arrivalTime,burstTime,priority);
                        System.out.println("\n\n\n");
                        break;

                case 6: done = false;
                        break;

                default:System.out.println("Invalid Choice !");
            }
        }

    }    
}
