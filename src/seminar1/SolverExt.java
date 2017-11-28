package Tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * ( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) ) = 101
 * ( 1 + ( 5 * ( 4 * 5 ) ) )
 * ( 1 + ( 5 * 20 ) ) = 101
 * ( 1 + 100 ) = 101
 *
 * 1 + ( 2 + 3 ) * 4 * 5 = 101
 * 1 + 5 * 4 * 5 = 101
 * 1 + 5 * 20 = 101
 * 1 + 100 = 101
 * 20 / 4 = 5
 * (101 - 1) / 5 = 20
 *
 * Считаем, что операции деления на ноль отсутствуют
 */
public class SolverExt {
    //Криво, но работает
    // пусть будет так.
    private static final String QUIT = "q";

    private static final char LEFT_PAREN   = '(';
    private static final char RIGHT_PAREN  = ')';
    private static final char PLUS         = '+';
    private static final char MINUS        = '-';
    private static final char TIMES        = '*';
    private static final char DIVISION     = '/';

    private static double evaluate(String values) {
        String[] arr = infToPostfic(values).split(" ");
        int tmp;
        LinkedStack<Integer> stack = new LinkedStack<>();
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

        return stack.pop();
    }

    public static void main(String[] args) {
        try (BufferedReader lineReader = new BufferedReader(new InputStreamReader(System.in))) {
            String sequence;
            while (!QUIT.equals(sequence = lineReader.readLine())) {
                System.out.println(evaluate(sequence));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String infToPostfic(String str){
        ArrayStack<Character> stack = new ArrayStack<>();
        String[] arr = str.split(" ");
        EasyMap<Character,Integer> prior = new EasyMap<>();
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





    static class EasyMap<K,V>{

        private K[] arrKey = null;
        private V[] arrValue = null;
        private int size = 0;

        @SuppressWarnings("unchecked")
        EasyMap(){
            arrKey = (K[]) new Object[10];
            arrValue = (V[]) new Object[10];
        }

        void put(K key, V value){
            arrKey[size] = key;
            arrValue[size++] = value;
        }

        V get(K key){
            return arrValue[indexOf(key)];
        }

        private int indexOf(K key){
            for (int i = 0; i < arrValue.length; i++) {
                if (key == arrKey[i]){
                    return  i;
                }
            }
            return 0;
        }
    }
}
