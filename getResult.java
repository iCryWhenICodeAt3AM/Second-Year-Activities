import java.util.*;
public class getResult {
    private static Stack<Double> numberStack = new Stack<Double>();//variable for numbers stack
    private static Stack<Character> operatorStack = new Stack<Character>();//variable for operators stack
    private static String builder = "";
    private static boolean key = true;

    public static void performOperation(char o){
        double x, y, result = 0;
        //new added
        boolean isNegative=false;
        
        y = numberStack.pop();
        
        if(numberStack.peek()<1){
            isNegative = true;
        }
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
            if (isNegative==true) {
                result *= -1;
            }
        }
        numberStack.push(result);
    }

    public static int precedenceChecker(char i){
        if ( i == '*' || i == '/') {
            return 2;
        } else if ( i == '+' || i == '-' ){
            return 1;
        }  else if ( i == '^' ){
            return 3;
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

    public void splitInput(String i){ //void method for the input splitting and doing the appropriate operation at the same time
        char[] split = i.toCharArray();
        for (char c : split) {
            //System.out.println("c="+c);
            if (numberCheck(c)){
                builder += c;
                //System.out.println(builder);
                key = false;
                //System.out.println("key is "+key);
            } else if (operatorCheck(c) && key == false){
                //System.out.println("c is operator");
                //System.out.println("builder is = " + builder);
                if (!builder.isEmpty()) {
                    numberStack.push(Double.parseDouble(builder));
                    builder = "";
                    //System.out.println("number stack after builder pass: "+numberStack);
                    //System.out.println("operator stack after builder pass: "+operatorStack);
                }   
                key = true;
                //System.out.println("c is operator "+c);
                if (operatorStack.isEmpty()) {
                    operatorStack.push(c);
                } else if (precedenceChecker(c) > precedenceChecker(operatorStack.peek())){
                    //System.out.println("c in precedence is "+precedenceChecker(c));
                    //System.out.println("top element in precedence is "+precedenceChecker(operatorStack.peek()));
                    //System.out.println("c is " + c + " and " + " top of operator is " + operatorStack.peek() + ". Therefore, c is greater than or equal to top element");
                    operatorStack.push(c);
                    //System.out.println(operatorStack);
                    //System.out.println(numberStack);
                } else {
                    //System.out.println(c);
                    while(!operatorStack.isEmpty() && precedenceChecker(c) <= precedenceChecker(operatorStack.peek()) || precedenceChecker(c)==3){
                        //System.out.println("c is " + c + " and " + " top of operator is " + operatorStack.peek());
                        //System.out.println(operatorStack);
                        performOperation(operatorStack.pop());
                    }
                    operatorStack.push(c);
                }
                 
            } else if (c == '(') {
                operatorStack.push(c);
            } else if (c == ')'){
                if(!builder.equals("")){
                    numberStack.push(Double.parseDouble(builder));
                    builder = "";
                }
                while (operatorCheck(operatorStack.peek())&&!operatorStack.peek().equals('(')) {
                    performOperation(operatorStack.pop());
                    //System.out.println("OHh"+operatorStack);
                    //System.out.println(numberStack);
                }
                if(operatorStack.peek()=='('){
                    //System.out.println("limme pop \'realquick\'");
                    operatorStack.pop();
                    //System.out.println("OHH"+operatorStack);
                    //System.out.println(numberStack);
                    //sample additional code
                    while (!operatorStack.empty()&&operatorStack.peek()=='*') {
                        performOperation(operatorStack.pop());
                    }
                }
            } 
        }
        //System.out.println("yugs");
        if (!builder.isEmpty()) {
            numberStack.push(Double.parseDouble(builder));
        }
        //System.out.println(numberStack);
        //System.out.println(operatorStack);
        while (!operatorStack.isEmpty()) {
            //possible error producer
            //System.out.println("OS:"+operatorStack);
            //System.out.println("NS:"+numberStack);
            if (operatorCheck(operatorStack.peek())){
                performOperation(operatorStack.pop());
            } else if (operatorStack.peek()=='('){
                operatorStack.pop();
            } 
            
        }
        System.out.println("Result: " + numberStack.pop());
    }
}
