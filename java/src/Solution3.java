
public class Solution3 {

    static int canDiscardAll(String[] input) {
        
        String[] sortedInput=sort(input);

        

		
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
	
	static int removeSingle(String[]input){
		
		
		int[] count=new int[26];
		for(int i=0;i<input.length;i++){
			count[input[i].charAt(0)-'A']++;
		}
		
		for(int k=1; k<count.length;k++){
			for(int j=0; j<count.length;j++)
				System.out.print(count[j]+",");
			System.out.println();
			if(count[k]==1){
				
				if((k<count.length-2 )&&(count[k+1]==1)&&(count[k+2]==1)){
					count[k]--;
					count[k+1]--;
					count[k+2]--;
					k=k+2;
					continue;
				}
				if((1<k&&k<count.length-1 )&&(count[k+1]==1)&&(count[k-1]>2)){
					count[k]--;
					count[k+1]--;
					count[k-1]--;
					k=k+1;
					continue;
				}
				if((k<count.length-2 )&&(count[k+1]==1)&&(count[k+2]>=2)){
					count[k]--;
					count[k+1]--;
					count[k+2]--;
					k=k+1;
					continue;
				}
				if((2<k)&&(count[k-2]>2)&&(count[k-1]>2)){
					count[k]--;
					count[k-1]--;
					count[k-2]--;
					continue;
				}
				if((1<k&&k<count.length-1)&&(count[k-1]>2)&&(count[k+1]>=2)){
					count[k-1]--; 
					count[k]--; 
					count[k+1]--; 

					continue;
				}
				if(useA(count)==1){
					continue;
				}
				return 0;
			}
		}
		
		
		return 1;
		
	}
static int useA(int[]count){
		
		
		for(int k=1; k<count.length;k++){
			for(int j=0; j<count.length;j++)
				System.out.print(count[j]+",");
			System.out.println();
			if(count[k]==1){
			
				if((k<count.length-1 )&&(count[k+1]==1)&&(count[0]>0)){
					count[k]--;
					count[k+1]--;
					count[0]--;
					k=k+1;
					return 1;
				}
				
				if(count[0]>0){
					count[k]--;
					count[0]--;
					return 1;
				}

				return 0;
			}
		}
		
		
		return 1;
		
	} 
	static int removeDup(String[]input, int n){
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
		String[] input={"A","C","B","C","D","D","E"};
		System.out.println(removeSingle(input));
	}

}
