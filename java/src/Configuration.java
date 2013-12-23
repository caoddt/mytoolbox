import java.util.HashMap;
import java.util.Stack;


public class Configuration {
	
	HashMap props = new HashMap();
	
	public String getValue(String key){
		String value =(String) props.get(key);
		if(value==null){
			return "N/A";
		}
		return value;
	}
	public void parse(String parent, String s){
		int braceNo=0;
		int splitter=0;
		int last=0;
		while(splitter<s.length()){
			char cur = s.charAt(splitter);
			if(cur=='{'){
				braceNo++;
			}
			if(cur=='}'){
				if(braceNo>0)
					braceNo--;
				else
					System.out.print("Invalid format!");
			}
			if(cur==';'&&braceNo==0){
				String subExp =s.substring(last,splitter);
				last=splitter+1;
				
				int index =subExp.indexOf('=');
				if(index==-1){
					System.out.println("Invalid format!");
					return;
				}
				
				String key =subExp.substring(0,index).trim();
				if(key.endsWith("+")){
					key=key.substring(0,key.length()-1);
				}
				if(parent!=null){
					key = parent+"." +key;
				}
				String value = subExp.substring(index+1).trim();
				if(value.startsWith("{")&&value.endsWith("}")){
					value = value.substring(1,value.length()-1).trim();
					parse(key,value);
				}else{
					System.out.println(key+":"+ value);
					props.put(key, value);
				}
			}

			splitter++;
		}
		
		
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Configuration conf = new Configuration();
		conf.parse(null, "A={A=1;B=2;C=3;E={A=100;};};");
		conf.parse(null, "A+={D=4;A=2;E={B=10; C= D;};};");
		System.out.println(conf.getValue("A.A"));
		System.out.println(conf.getValue("A.E.B"));
	}

}
