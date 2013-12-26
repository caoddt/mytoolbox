function compute(oper, num1, num2)
{
    print num1 oper num2
    if(oper =="+")
      {result = num1+num2}
    if(oper =="*")
      {result = num1*num2}
    if(oper =="-")
        {result = num1-num2}
    if(oper =="/")
          {result = num1/num2}
    return result

}

BEGIN {
         oper="([\\+\\-\\*\\/\\(\\)]{1})"
	 numb="([0-9]+)"
         FPAT=numb"|"oper
	 priority["+"]=1 
         priority["-"]=1
         priority["*"]=2
         priority["/"]=2
         priority["("]=3
         priority[")"]=0
}

{
         
         for (i = 1; i <= NF; i++) {
           if($i ~ numb){
            numstack[++n_top]=$i 
           }
           if($i ~ oper){
            # combine
            while(o_top>0 && priority[operstack[o_top]]>=priority[$i]&&operstack[o_top]!="("){
               opp=  operstack[o_top--]
               num2= numstack[n_top--]
               num1=  numstack[n_top--] 
               result = compute( opp, num1, num2)
               numstack[++n_top]=result
               
            }
            
            if(operstack[o_top]=="(" && $i==")"){
              tmp = operstack[o_top--]
              print "remove " tmp

            }


            if($i!=")"){
              operstack[++o_top]=$i

            }
           }
           numstr="numstack[" 
           for(k=n_top;k>0;k-- ){
              numstr= numstr "," numstack[k]
            }
           #print numstr "]"
            opstr="operstack["
           for(k=o_top;k>0;k-- ){
              opstr= opstr "," operstack[k]
            }
           #print opstr "]"
 
         } 
         while(o_top>0 && operstack[o_top]!="("){
               opp=  operstack[o_top--]
               num2= numstack[n_top--]
               num1=  numstack[n_top--]
               result = compute( opp, num1, num2)
 
                  numstack[++n_top]=result

            }
         print "result is " numstack[n_top]
         
} 

