import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;


public class StringMatching {

	public static int maxDupSubString(String s){
		int max=0;
		
		Map <Integer, Integer> lastJ= new Hashtable();
		Map <Integer, Integer> lastI= new Hashtable();
		for (int i =0; i<s.length(); i++){
			for (int j=i+1; j<s.length();j++){

				if(s.charAt(i)==s.charAt(j)){

					Integer last = lastJ.get(j-1);
					
					if(last!=null){
						
						lastI.put(j, last +1);
					}else{
						
						lastI.put(j, 1);
					}	
					
					if(lastI.get(j)> max){
						max=lastI.get(j);
					}

					System.out.print("("+j+"->"+lastI.get(j)+"), ");
				}
				
			}
			Map<Integer, Integer> temp;
			temp=lastJ;
			lastJ.clear();
			lastJ=lastI;
			lastI=temp;
			System.out.println(i+" end");
		}
		
		System.out.println("Max length is " + max);
		return 0;
	}
	
	public static int maxCommonSubString(String s, String t){
		int matrix[][]=new int[s.length()][t.length()];
		int max=0;
		String left;
		String right;
		for(int i=0; i<s.length();i++){
			for (int j=0; j<t.length(); j++){
				left = s.substring(i);
				right= t.substring(j);
				int k=0;
				while(left.charAt(k)==right.charAt(k)){
					if(k==left.length()-1 || k== right.length()-1){
						break;
					}
					k++;
				}
				matrix[i][j]=k;
				System.out.print(k+",");
				if (k>max){
					max=k;
				}
			}
			System.out.println();
		}
		System.out.println("Max length is " + max);
		
		return 0 ;
	}
	
	public static int maxCommonSubStrEnhanced(String s, String t){
		int matrix[][]=new int[s.length()][t.length()];
		int max=0;
		for(int i=0; i<s.length();i++){
			for (int j=0; j<t.length(); j++){
				if (s.charAt(i)==t.charAt(j)){
					if(i==0||j==0){
						matrix[i][j]=1;
					}
					else{
						matrix[i][j]=matrix[i-1][j-1]+1;
					}
					if (matrix[i][j]>max){
						max=matrix[i][j];
					}
				}else{
					matrix[i][j]=0;
				}
				
				System.out.print(matrix[i][j]+",");

			}
			System.out.println();
		}
		System.out.println("Max length is " + max);
		
		return 0 ;
	}
	
	public static int strSearchKMP(String subS, String longS){
		int[] next = new int[subS.length()];
		for (int i = 0;i<subS.length();i++){
			String t = subS.substring(0,i+1);
			next[i]=t.length();
			int j = t.length()-1;
			while(j>0){
				String prefix =t.substring(0, j);
				String suffix = t.substring(t.length() - j);
				if (prefix.equals(suffix)){
					next[i] = t.length()-j;
					break;
				}
				j--;
			}
		}
		for (int i=0; i<next.length;i++){
			
			System.out.print(next[i]+", ");
		}
		System.out.println("");
		int index=0;
		
		while(index<longS.length()-subS.length()+1){
			int i;
			for( i =0;i<subS.length();i++){
				if(subS.charAt(i)!=longS.charAt(index+i)){
					break;
				}
			}
			
			if (i ==subS.length()){
				return index;
			}else if(i==0){
				index++;
			}else{
				index+=next[i-1];
			}
		}

		return -1;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int index =strSearchKMP("a", "baa");
		System.out.println("a in baa: "+ index);
		
	    index =strSearchKMP("ababa", "System.aabababout.println");
		System.out.println("ababa in System.aabababout.println: "+ index);
		
		maxCommonSubString("abcdefg","acdefa");
		maxCommonSubStrEnhanced("abcdefg","acdefa");
		
		maxDupSubString("System.aabababout.println");
	}

}
