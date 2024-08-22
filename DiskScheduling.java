import java.util.Scanner;
import java.util.Arrays;

public class DiskScheduling {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice, continueOption;

        do {
            System.out.println("\n\n\t*********** MENU ***********");
            System.out.println("\t1: SSTF");
            System.out.println("\t2: SCAN");
            System.out.println("\t3: CLOOK");
            System.out.println("\t4: EXIT");
            System.out.print("\tEnter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    SSTF();
                    break;
                case 2:
                    SCAN();
                    break;
                case 3:
                    CLOOK();
                    break;
                case 4:
                    System.exit(0);
            }

            System.out.print("\n\n\tDo you want to continue? IF YES PRESS 1\n\tIF NO PRESS 0: ");
            continueOption = scanner.nextInt();

        } while (continueOption == 1);

        scanner.close();
    }

    public static void SSTF() {
        Scanner scanner = new Scanner(System.in);
        int[] RQ;
        int n, TotalHeadMovement = 0, initial, count = 0;

        System.out.print("Enter the number of Requests: ");
        n = scanner.nextInt();
        RQ = new int[n];

        System.out.print("Enter the Requests sequence: ");
        for (int i = 0; i < n; i++) {
            RQ[i] = scanner.nextInt();
        }

        System.out.print("Enter initial head position: ");
        initial = scanner.nextInt();

        while (count != n) {
            int min = Integer.MAX_VALUE, d, index = -1;
            for (int i = 0; i < n; i++) {
                d = Math.abs(RQ[i] - initial);
                if (min > d) {
                    min = d;
                    index = i;
                }
            }
            TotalHeadMovement += min;
            initial = RQ[index];
            RQ[index] = Integer.MAX_VALUE;
            count++;
        }

        System.out.println("Total head movement is " + TotalHeadMovement);
    }

    public static void SCAN() {
        Scanner scanner = new Scanner(System.in);
        int[] RQ;
        int n, TotalHeadMovement = 0, initial, size, move;

        System.out.print("Enter the number of Requests: ");
        n = scanner.nextInt();
        RQ = new int[n];

        System.out.print("Enter the Requests sequence: ");
        for (int i = 0; i < n; i++) {
            RQ[i] = scanner.nextInt();
        }

        System.out.print("Enter initial head position: ");
        initial = scanner.nextInt();

        System.out.print("Enter total disk size: ");
        size = scanner.nextInt();

        System.out.print("Enter the head movement direction for high 1 and for low 0: ");
        move = scanner.nextInt();

        Arrays.sort(RQ);
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (initial < RQ[i]) {
                index = i;
                break;
            }
        }

        if (move == 1) {
            for (int i = index; i < n; i++) {
                TotalHeadMovement += Math.abs(RQ[i] - initial);
                initial = RQ[i];
            }
            TotalHeadMovement += Math.abs(size - RQ[n - 1] - 1);
            initial = size - 1;
            for (int i = index - 1; i >= 0; i--) {
                TotalHeadMovement += Math.abs(RQ[i] - initial);
                initial = RQ[i];
            }
        } else {
            for (int i = index - 1; i >= 0; i--) {
                TotalHeadMovement += Math.abs(RQ[i] - initial);
                initial = RQ[i];
            }
            TotalHeadMovement += Math.abs(RQ[0] - 0);
            initial = 0;
            for (int i = index; i < n; i++) {
                TotalHeadMovement += Math.abs(RQ[i] - initial);
                initial = RQ[i];
            }
        }

        System.out.println("Total head movement is " + TotalHeadMovement);
    }

    public static void CLOOK() {
        Scanner scanner = new Scanner(System.in);
        int[] RQ;
        int n, TotalHeadMovement = 0, initial, move;

        System.out.print("Enter the number of Requests: ");
        n = scanner.nextInt();
        RQ = new int[n];

        System.out.print("Enter the Requests sequence: ");
        for (int i = 0; i < n; i++) {
            RQ[i] = scanner.nextInt();
        }

        System.out.print("Enter initial head position: ");
        initial = scanner.nextInt();

        System.out.print("Enter the head movement direction for high 1 and for low 0: ");
        move = scanner.nextInt();

        Arrays.sort(RQ);
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (initial < RQ[i]) {
                index = i;
                break;
            }
        }

        if (move == 1) {
            for (int i = index; i < n; i++) {
                TotalHeadMovement += Math.abs(RQ[i] - initial);
                initial = RQ[i];
            }
            for (int i = 0; i < index; i++) {
                TotalHeadMovement += Math.abs(RQ[i] - initial);
                initial = RQ[i];
            }
        } else {
            for (int i = index - 1; i >= 0; i--) {
                TotalHeadMovement += Math.abs(RQ[i] - initial);
                initial = RQ[i];
            }
            for (int i = n - 1; i >= index; i--) {
                TotalHeadMovement += Math.abs(RQ[i] - initial);
                initial = RQ[i];
            }
        }

        System.out.println("Total head movement is " + TotalHeadMovement);
    }
}
