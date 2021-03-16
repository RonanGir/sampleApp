package sf.demo.sampleApp.util;

public class PaymentUtil {

    public static Boolean priceIsPaid(Integer price) {
        Boolean paid = false;
        if (price > 0) {
            paid = true;
        }
        return paid;
    }

    public static Boolean paidForCleaning(Integer price) {
        Boolean paid = false;
        if (price > 100) {
            paid = true;
        }
        return paid;

    }
}
