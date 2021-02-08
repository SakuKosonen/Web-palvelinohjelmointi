package calculations;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class CalculationService {

    @Autowired
    private CalculationRepository calculationRepository;

    @Transactional
    public Calculation process(Calculation calculation) {
        calculation.setStatus("PROCESSING");        
        return calculationRepository.save(calculation);
    }

    @Async  
    public void calculate(Calculation calculation) {

        
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(CalculationService.class.getName()).log(Level.SEVERE, null, ex);
        }

        calculation.setStatus("PROCESSED");
        calculation.setCalculationResult(calculation.getContent() + ";" + UUID.randomUUID().toString());
        calculationRepository.save(calculation);
    }

}
