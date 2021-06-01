package anticorona;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Injection")
public class Injection {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long injectionId;
    private Long bookingId;
    private Long vaccineId;
    private Long userId;
    private String status;

    @PreUpdate
    public void onPreUpdate(){

        // 접종 완료 처리 //
        Completed completed = new Completed();
        BeanUtils.copyProperties(this, completed);
        completed.setStatus("INJECTION_COMPLETED");
        completed.publishAfterCommit();      
    }


    public Long getInjectionId() {
        return injectionId;
    }

    public void setInjectionId(Long injectionId) {
        this.injectionId = injectionId;
    }
    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }
    public Long getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(Long vaccineId) {
        this.vaccineId = vaccineId;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
