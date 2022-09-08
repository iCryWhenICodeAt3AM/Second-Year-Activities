
import java.util.*;

public class newCalculatorTestV1 {

    public static Stack<Double> numberStack = new Stack<Double>();//variable for numbers stack
    public static Stack<Character> operatorStack = new Stack<Character>();//variable for operators stack
    public static String builder = "";
    public static boolean key = true;

    public static void performOperation(char o){
        double x, y, result = 0;
        
        y = numberStack.pop();
        x = numberStack.pop();
        if (o == '*'){
            result = x*y;
        } else if (o == '/'){
            result = x/y;
        } else if (o == '+'){
            result = x+y;
        } else if (o == '-'){
            result = x-y;
        } else if (o == '^'){
            result = Math.pow(x, y);
        }
        numberStack.push(result);
    }

    public static int precedenceChecker(char i){
        if ( i == '*' || i == '/' || i == '^') {
            return 2;
        } else if ( i == '+' || i == '-' ){
            return 1;
        } else {
            return 0;
        }
    }
    public static boolean numberCheck(char i){ //to check if it is a digit
        if (i=='-'&&key==true){
            builder += i;
        }
        return i == '0' || i == '1' || i == '2' || i == '3' || i == '4' || 
                i == '5' || i == '6' || i == '7' || i == '8' || i == '9'|| i == '.';
    }
    public static boolean operatorCheck(char i){ //boolean method for checking if character is an operator || returns True or False
        return i == '*' || i == '/' || i == '+' || i == '-' || i == '^';
    }

    public static void splitInput(String i){ //void method for the input splitting and doing the appropriate operation at the same time
        char[] split = i.toCharArray();
        for (char c : split) {
            if (numberCheck(c)){
                builder += c;
                System.out.println(builder);
                key = false;
            } else if (operatorCheck(c) && key == false){
                numberStack.push(Double.parseDouble(builder));
                builder = "";
                key = true;
                if (operatorStack.isEmpty() || precedenceChecker(c) > precedenceChecker(operatorStack.peek())){
                    operatorStack.push(c);
                    System.out.println(operatorStack);
                    System.out.println(numberStack);
                } else {
                    System.out.println(c);
                    while(!operatorStack.isEmpty() && precedenceChecker(c) <= precedenceChecker(operatorStack.peek())){
                        System.out.println(operatorStack);
                        performOperation(operatorStack.pop());
                    }
                    operatorStack.push(c);
                }
                 
                System.out.println(operatorStack);
            } else if (c == '(') {
                operatorStack.push(c);
            } else if (c == ')'){
                numberStack.push(Double.parseDouble(builder));
                builder = "";
                while (operatorCheck(operatorStack.peek())) {
                    performOperation(operatorStack.pop());
                }
                if(operatorStack.peek().equals('(')){
                    operatorStack.pop();
                }
            } 
        }
        if (!builder.isEmpty()) {
            numberStack.push(Double.parseDouble(builder));
        }
        System.out.println(numberStack);
        System.out.println(operatorStack);
        while (!operatorStack.isEmpty()) {
            //possible error producer
            if (operatorStack.peek()=='('){
                operatorStack.pop();
            }
            performOperation(operatorStack.pop());
        }
        System.out.println("Result: " + numberStack);
        
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter expression here: ");
        String input = sc.nextLine();
        splitInput(input);
        sc.close();
    }
}