package duolingobackenduserservice.service;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import duolingobackenduserservice.dto.BillDetail;

public interface PaypalService {
    public Payment createPayment(BillDetail billDetail);
    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;
}
