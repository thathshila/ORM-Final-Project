package lk.ijse.dto;

import lk.ijse.entity.Student_Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDto {
    private String pay_id;
    private String pay_date;
    private double pay_amount;
    private String status;
    private double balance_amount;
    private Student_Course student_course;
}
