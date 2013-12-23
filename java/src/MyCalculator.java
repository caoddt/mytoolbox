import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public class MyCalculator {
	
	public static int getBitValue(int number, int bitIndex){
		int bitvalue=1<<bitIndex;
		return (number&bitvalue)>>bitIndex;
		
	}
	Map<Character, Integer> operators = new HashMap();
	public MyCalculator(){
		operators.put('(', 3);
		operators.put(')', 0);
		operators.put('*', 2);
		operators.put('/', 2);
		operators.put('+', 1);
		operators.put('-', 1);
		
	}
	protected int comparePriority(char oper1, char oper2){
		return operators.get(oper1) - operators.get(oper2);
	}
	
	protected boolean isOperator(char c){
		return operators.containsKey(c);
	}
	protected boolean isNumber(char c){
		return Character.isDigit(c);
	}
	
	
	public int calculate(String exp) throws Exception{
		char[] charArray = exp.toCharArray();
		Stack<Character> operStack = new Stack();
		Stack<Integer> numberStack = new Stack();
		int left=0;
		int right=0;
		for (int i = 0; i< charArray.length; i++){
			if (isOperator(charArray[i])){
				
					if(!operStack.isEmpty()&& (operStack.peek()!='(') 
							&&(comparePriority(charArray[i], operStack.peek())<=0) ){ 
						// calculate operStack.peek
						while(!operStack.isEmpty()&&operStack.peek()!='('){
							int temp =operate(operStack.pop(), numberStack.pop(), numberStack.pop());
							numberStack.push(temp);
						}						
					}
					
					if(charArray[i] == ')'){
						char o = operStack.pop();
						if (o!='('){
							throw new Exception("Miss (");	
						}

					}else{
						operStack.push(charArray[i]);
					}

			}else{
				left=right=i;
				while(++right<charArray.length&&!isOperator(charArray[right]));
				String number=exp.substring(left,right);
				if (number.trim().length()>0){
					numberStack.push(Integer.valueOf(number.trim()));
					i=right-1;
				}
			}
		}
		while(!operStack.isEmpty()){
			int temp =operate(operStack.pop(), numberStack.pop(), numberStack.pop());
			numberStack.push(temp);
		}
		return numberStack.pop();
	}
	
	protected int operate(char oper, int num2, int num1) throws Exception{
		if (oper=='+'){
			return num1+num2;
		}
		if (oper=='-'){
			return num1-num2;
		}
		if (oper=='*'){
			return num1*num2;
		}
		if (oper=='/'){
			return num1/num2;
		}
		throw (new Exception("illegal operatior"));
	}
	
	public static void main(String[] args){
		MyCalculator cal= new MyCalculator();
		try {
			String exp;
			int result;
			 exp = " 21 + 11 * 4 ";
			 result = cal.calculate(exp);
			System.out.println(exp +" = "+result);
			
			 exp = "1* 4+2";
			 result = cal.calculate(exp);
			System.out.println(exp +" = "+result);
			
			 exp = "2+1*4+3";
			 result = cal.calculate(exp);
			System.out.println(exp +" = "+result);
			
			 exp = "(2+1) *4";
			 result = cal.calculate(exp);
			System.out.println(exp +" = "+result);
			
			 exp = "2+1* (4/2)";
			 result = cal.calculate(exp);
			System.out.println(exp +" = "+result);
			
			 exp = "(2+1*(4-2*1))";
			 result = cal.calculate(exp);
			System.out.println(exp +" = "+result);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(getBitValue(5,0));
	}
}
