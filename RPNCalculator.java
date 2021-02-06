package Assignment1;

import java.util.Arrays;


interface RPNStack {
    boolean isEmpty();
    int length();
    void push(int i);
    int pop();
    void clear();

}

public class RPNCalculator implements RPNStack {

    int CLength;
    int[] Stack;

    public RPNCalculator()
    {
        this.CLength = 0;
        Stack = new int[10];


    }

    @Override
    public boolean isEmpty() {
        return this.length() ==0;
    }

    @Override
    public int length() {
        return CLength;
    }

    @Override
    public void push(int i) {
        if(this.length() == Stack.length){
            Stack = Arrays.copyOf(Stack,Stack.length+10);
        }
        Stack[CLength] = i;
        CLength++;
    }

    @Override
    public int pop() {
        CLength--;
        if(CLength == Stack.length / 4){
            Stack = Arrays.copyOf(Stack,Stack.length/2);
        }
        return Stack[CLength];
    }

    @Override
    public void clear() {
        CLength = 0;
        Stack = new int[10];
    }

    int evaluate(String expr) throws RPNException {
        String[] Elements = expr.split(" ");
        String tokens = "+/-*";
        int result=0;
        for (String s: Elements)
        {
            if(tokens.contains(s))
            {
                int x = pop();
                String token = s;
                int y = pop();
                switch(s){
                    case "+": push(y + x);break;
                    case "-": push(y - x);break;
                    case "*": push(y * x);break;
                    case "/": push(y / x);break;
                }

            }
            else if(isNumeric(s))
            {
                push(Integer.parseInt(s));
            }
            else if(s == " "){
                continue;
            }else{
            throw new RPNException("Invalid Token entered into string");
        }
        }

        result = pop();
        return result;
    }


    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


    public class RPNException extends Exception {
        public RPNException(String errorMessage) {
            super(errorMessage);
        }
    }
}



