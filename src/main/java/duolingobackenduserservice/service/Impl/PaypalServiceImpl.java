package duolingobackenduserservice.service.Impl;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import duolingobackenduserservice.dto.BillDetail;
import duolingobackenduserservice.service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaypalServiceImpl implements PaypalService {
    @Autowired
    private APIContext apiContext;

    @Override
    public Payment createPayment(BillDetail billDetail) {
        Amount amount = new Amount();
        amount.setCurrency(billDetail.getCurrency());
        BigDecimal total = BigDecimal.valueOf(new BigDecimal(billDetail.getTotal()).setScale(2, RoundingMode.HALF_UP).doubleValue());
        amount.setTotal(String.format("%.2f", total));

        Transaction transaction = new Transaction();
        transaction.setDescription(billDetail.getDescription());
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(billDetail.getMethod());

        Payment payment = new Payment();
        payment.setIntent(billDetail.getIntent());
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(billDetail.getCancelUrl());
        redirectUrls.setReturnUrl(billDetail.getSuccessUrl());
        payment.setRedirectUrls(redirectUrls);

        try {
            return payment.create(apiContext);
        } catch (PayPalRESTException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecute);
    }
}
