package todoapplication;

import java.util.UUID;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;


    @Entity
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public class Todo extends AbstractPersistable<Long> {

        private String name;
        private int checked;
        
        
        public void check() {
            checked++;
        }
        
    }