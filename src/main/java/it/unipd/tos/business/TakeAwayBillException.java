////////////////////////////////////////////////////////////////////
// [Gabriele] [Garbin] [1162293]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business.exception;

public class TakeAwayBillException extends Throwable {
    String error;
    
    public TakeAwayBillException(String error){
        this.error=error;
    }
    public String getError() {
        return error;
    }
    /**
     * 
     * */
    private static final long serialVersionUID = -2265154439188606779L;
}
