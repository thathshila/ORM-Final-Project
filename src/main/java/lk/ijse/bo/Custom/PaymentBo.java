package lk.ijse.bo.Custom;

import lk.ijse.bo.SuperBo;
import lk.ijse.dto.PaymentDto;
import lk.ijse.entity.Payment;

import java.io.IOException;
import java.util.List;

public interface PaymentBo extends SuperBo {
    boolean savePayment(PaymentDto paymentDto) throws IOException;

    List<Payment> getPaymentList() throws IOException;
}

