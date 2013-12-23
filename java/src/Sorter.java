
public class Sorter {
	public static int quickSort(int low, int high, int[] data){
		if(low>=high){
			return low;			
		}
		
		int temp=0;
		int i,j;

		i=low+1;
		j=high;
		while(i<=j){
			while(data[low]>data[i]){
				i++;
				if (i>j)
					break;
			}
			
			while(data[low]<=data[j]){
				j--;
				if(i>j)
					break;
			}
			
			if(i<j){
				temp=data[j];
				data[j]=data[i];
				data[i]=temp;
			}else if(i>j&&j>low)
			{
				temp=data[low];
				data[low]=data[j];
				data[j]=temp;
			}
			for(int k=0; k<data.length;k++)
				System.out.print(data[k]+",");
			System.out.println();
		}

		quickSort(low,j-1,data);
		quickSort(j+1,high,data);
		return j;
		
	}									
	
	
	public static int[] biInsertSort(int[] data){
		int temp=0;
		int left=0;
		int right=0;
		for(int i=1; i<data.length;i++){
			
				left=0;
				right=i-1;
				while(left<right){
					int mid=(left+right)/2;
					if(data[mid]>data[i]){
						right=mid-1;
					}else{
						left=mid +1;
					}
				}
				
				temp =data[i];
				if(data[left]>temp){
					for(int j=i;j>left;j--){
						data[j]=data[j-1];
					}
					data[left]=temp;
				}else{
					for(int j=i;j>left+1;j--){
						data[j]=data[j-1];
					}
					data[left+1]=temp;
				}
				
							
				for(int k=0; k<data.length;k++)
					System.out.print(data[k]+",");
				System.out.println();
		}
		
		return data;
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] a= {5,4,3,2,1,2,7,6,4,3,2,1,2,7,6,4,3,2,1,2,7,6,4,3,2,1,2,7,6,4,3,2,1,2,7,6,4,3,2,1,2,7,6};
		//biInsertSort(a);
		quickSort(0,a.length-1,a);
	}

}
