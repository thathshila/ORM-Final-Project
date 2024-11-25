package lk.ijse.dao.Custom;

import lk.ijse.dao.CrudDao;
import lk.ijse.entity.Payment;

import java.io.IOException;

public interface PaymentDao extends CrudDao<Payment> {
    String getCurrentId() throws IOException;

}
