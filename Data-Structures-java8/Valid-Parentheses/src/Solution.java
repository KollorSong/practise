import java.util.Stack;


public class Solution {

    //判断字符串是否合法
    public boolean isValid(String string){
        Stack<Character> stack = new Stack<>();
        for (int i =0;i<string.length();i++){
            char c = string.charAt(i);
            if (c == '(' || c == '[' || c == '{'){
                stack.push(c);
            }else {
                if (stack.isEmpty()){
                    return false;
                }
                char topChar = stack.pop();
                if (c == ')' && topChar != '(') return false;
                if (c == '}' && topChar != '{') return false;
                if (c == ']' && topChar != '[') return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println((new Solution()).isValid("(){}[]"));
    }



    
}
