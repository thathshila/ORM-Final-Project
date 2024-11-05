package lk.ijse.EntityTm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentTm {
    private String stu_id;
    private String stu_name;
    private String stu_address;
    private String stu_phone;
    private Date date;
    private String user_id;
}
