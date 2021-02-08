package reservations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

   
    
    

    @GetMapping("*")
    public String handleDefault() {
        
        return "redirect:/reservations";

    }
}
