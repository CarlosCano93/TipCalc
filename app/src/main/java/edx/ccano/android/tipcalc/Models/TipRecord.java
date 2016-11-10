package edx.ccano.android.tipcalc.Models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Carlos on 12/06/2016.
 */
public class TipRecord {

    private Double bill;
    private int tipPercentage;
    private Date timestamp;

    public Double getBill() {
        return bill;
    }

    public void setBill(Double bill) {
        this.bill = bill;
    }

    public int getTipPercentage() {
        return tipPercentage;
    }

    public void setTipPercentage(int tipPercentage) {
        this.tipPercentage = tipPercentage;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getTip (){
        return bill*(tipPercentage/100d);
    }

    public String getDateFormated() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( ("MMM dd, yyyy HH:mm"));
        return simpleDateFormat.format(timestamp);
    }
}
