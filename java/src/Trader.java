import java.util.ArrayList;


public class Trader {
	
	public static String trade(int[] prices){
		ArrayList tradePlan = new ArrayList();
		
		int i=0;
		int low=prices[0];
		int high=prices[0];
		int buyin=-1;
		int sellout=-1;
		while(i<prices.length){
			while(i<prices.length-1){
				if(prices[++i]<=low){
					low=prices[i];
				}else{		
					buyin= i-1;
					high=prices[i];
					break;
				}
			}
			
			while(i<prices.length-1){
				if(prices[++i]>high){
					sellout=i;
					high=prices[i];
				}else{		
					low=prices[i];
					break;
				}
			}
			
			if(buyin==-1||sellout==-1){
				break;
			}
			tradePlan.add(buyin);//add buy in point
			tradePlan.add(sellout);//add sell out point
			buyin=-1;
			sellout=-1;
		}
		if(tradePlan.size()==0){
			return "-";
		}
		return tradePlan.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] prices= {1,3,5,4,2,8,10};
		String plan = trade(prices);
		System.out.println(plan);
		int[] pricesB= {1,1,1,3,5,4,2,2,2,8,10};
		plan = trade(pricesB);
		System.out.println(plan);

	}

}
