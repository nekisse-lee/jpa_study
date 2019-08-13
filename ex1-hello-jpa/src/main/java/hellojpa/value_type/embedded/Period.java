package hellojpa.value_type.embedded;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class Period {
    private LocalDateTime startDate;
    private LocalDateTime emdDate;

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEmdDate() {
        return emdDate;
    }

    public void setEmdDate(LocalDateTime emdDate) {
        this.emdDate = emdDate;
    }
}
