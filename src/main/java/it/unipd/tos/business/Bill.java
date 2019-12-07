////////////////////////////////////////////////////////////////////
// [Gabriele] [Garbin] [1162293]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import java.util.List;

import it.unipd.tos.business.TakeAwayBill;
import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;

public class Bill implements TakeAwayBill{
    public double getOrderPrice(List<MenuItem> itemsOrdered) throws TakeAwayBillException{
        if(itemsOrdered==null){
            throw new TakeAwayBillException("Empty Menu");
        }if(itemsOrdered.size() > 30){
            throw new TakeAwayBillException("Too many items");
        }double totaleCibo=0D;
        double totaleBevande=0D;
        int countPanini=0;
        double paninoMin=Double.MAX_VALUE;
        for (int i=0; i<itemsOrdered.size(); i++) {
            MenuItem temp=itemsOrdered.get(i);
            if(temp.getType().name()=="Bevande"){
                totaleBevande+=temp.getPrice();
            }else{
                if(temp.getType().name()=="Panini") {
                    countPanini++;
                    if(temp.getPrice()<paninoMin){
                        paninoMin=temp.getPrice();
                    }
                }
                totaleCibo+=temp.getPrice();
            }
        }
        if(countPanini>=5){
            totaleCibo=totaleCibo-(paninoMin/2);
        }double totale=totaleCibo+totaleBevande;
        if(totaleCibo>=50){
            totale=totale-(totale * 0.1D);
        }if(totale<=10){
            totale+=0.5D;
        }return totale;
    }
}