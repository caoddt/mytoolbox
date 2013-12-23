
public class Solution2 {

    static int canDiscardAll(String[] input) {
        
        String[] sortedInput=sort(input);
        while(checkDup(sortedInput, 7)==1||checkDup(sortedInput, 6)==1||checkDup(sortedInput, 5)==1||checkDup(sortedInput, 4)==1||checkDup(sortedInput, 3)==1||checkDup(sortedInput, 2)==1||removeSeq(sortedInput)==1){
     		for(int k=0; k<sortedInput.length;k++)
    			System.out.print(sortedInput[k]+",");
    		System.out.println();
        }
        

		
 		for(int f=0;f<sortedInput.length;f++){
			if(sortedInput[f]!=null){
				return 0;
			}
		}
 		
         return 1;

    }
    
	static String[] sort(String[] input){
		
		String temp;
		int left=0;
		int right=0;
		for(int i=1; i<input.length;i++){
			
				left=0;
				right=i-1;
				while(left<right){
					int mid=(left+right)/2;
					if(input[mid].compareTo(input[i])>0){
						right=mid-1;
					}else{
						left=mid +1;
					}
				}
				
				temp =input[i];
				if(input[left].compareTo(temp)>0){
					for(int j=i;j>left;j--){
						input[j]=input[j-1];
					}
					input[left]=temp;
				}else{
					for(int j=i;j>left+1;j--){
						input[j]=input[j-1];
					}
					input[left+1]=temp;
				}
				
							
				for(int k=0; k<input.length;k++)
					System.out.print(input[k]+",");
				System.out.println();
		}
		
		return input;
		
	}

	static int removeSeq(String[] input){

		int i =0;
		char first;
		char second;
		char third;
		while(i<input.length){
			if(input[i]==null){
				i++;
				continue;
			}
			first =input[i].charAt(0);
			int j=i+1;
			while(j<input.length){
				if(input[j]==null){
					j++;
					continue;
				}
				second=input[j].charAt(0);
				if((second-first)==0){
					j++;
					continue;
				}
				if((second-first)>1){
					break;
				}
				if((second-first)==1){
					int k=j+1;
					while(k<input.length){
						if(input[k]==null){
							k++;
							continue;
						}
						third=input[k].charAt(0);
						if((third-second)==0){
							k++;
							continue;
						}
						if((third-second)>1){
							break;
						}
						if((third-second)==1){
							input[i]=null;
							input[j]=null;
							input[k]=null;
							return 1;
						}
						k++;
					}
				}
				j++;
			}
			i++;
		}

		return 0;
	    
	}
	
	static int checkDup(String[]input, int n){
		int i =0;
		char[] cArray=new char[n];
		int[] indexArray=new int[n];
		int c=0;
		
		while(i<input.length){
			if(input[i]==null){
				i++;
				continue;
			}
			cArray[c] =input[i].charAt(0);
			indexArray[c]=i;
			c++;
			i++;
			if(c==n){
				break;
			}
			
		}
		
		
		if(c<n){
			return 0;
		}
		
		for(int j=1;j<n;j++){
			if(cArray[j]!=cArray[0]){
				return 0;
			}
		}
		
		for(int j=0;j<n;j++){
			input[indexArray[j]]=null;
		}
		return 1;
		
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] input={"A","A","B","C","B","C","B","C","C","D","E","F"};
		System.out.println(canDiscardAll(input));;
	}

}
