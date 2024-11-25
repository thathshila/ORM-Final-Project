package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment {
    @Id
    private String pay_id;
    private String pay_date;
    private double pay_amount;
    private String status;
    private double upfront_amount;
    private double balance_amount;

    @ManyToOne
    @JoinColumn(name = "student_course_id")
    private Student_Course student_course;
}
