package Tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

public class Infiksnoe {
// вычисление инфиксного выражения
    public static void main(String[] args) throws IOException {

        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
//        String[] arr = buf.readLine().split(" ");

        String[] arr = infToPostfic(buf.readLine()).split(" ");
        int tmp;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            switch (arr[i]){
                case "*": stack.push(stack.pop() * stack.pop());
                break;

                case "+": stack.push(stack.pop() + stack.pop());
                break;

                case "-": stack.push(-(stack.pop() - stack.pop()));
                break;

                case "/": tmp = stack.pop(); stack.push(stack.pop() / tmp);
                break;

                default: stack.push(Integer.parseInt(arr[i]));
                break;
            }

        }
        System.out.println(stack.pop());

    }

    private static String infToPostfic(String str){
        Stack<Character> stack = new Stack<>();
        String[] arr = str.split(" ");
        HashMap<Character,Integer> prior = new HashMap<>();
        prior.put('*',3);
        prior.put('/',3);
        prior.put('+',2);
        prior.put('-',2);
        prior.put('(',1);
        StringBuilder postfiks = new StringBuilder();
        char tmp;
        for (int i = 0; i < arr.length; i++) {
            tmp = arr[i].charAt(0);
            if ( 48 <= tmp && tmp <= 57){
                postfiks.append(arr[i]).append(' ');
            }else if (tmp == '('){
                stack.push(tmp);
            }else if (tmp == ')'){
                tmp = stack.pop();
                while (tmp != '('){
                    postfiks.append(tmp).append(' ');
                    tmp = stack.pop();
                }
            }else {
                while (!stack.isEmpty() && (prior.get(stack.peek()) >= prior.get(tmp))){
                    postfiks.append(stack.pop()).append(' ');
                }
                stack.push(tmp);
            }

        }
        while (!stack.isEmpty()){
            postfiks.append(stack.pop()).append(' ');
        }
        return postfiks.toString();
        

    }
}


