import java.util.*;
public class main {
    public static void main(String[] args) {
        getResult solve = new getResult();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter expression here: ");
        String input = sc.nextLine();
        solve.splitInput(input);
        sc.close();
    }
}