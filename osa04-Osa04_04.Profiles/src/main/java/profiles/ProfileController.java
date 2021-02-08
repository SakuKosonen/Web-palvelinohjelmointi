package profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProfileController {

    @Autowired
    Environment env;

    @ResponseBody
    @GetMapping("/profile")
    public String getProfile() {
        if (!env.getActiveProfiles()[0].isEmpty()) {
            return env.getActiveProfiles()[0];
        } else {
            return env.getDefaultProfiles()[0];
        }
    }

    @GetMapping("/")
    public String home(Model model) {
        if (!env.getActiveProfiles()[0].isEmpty()) {
            model.addAttribute("text", env.getActiveProfiles()[0]);
        } else {
            model.addAttribute("text", env.getDefaultProfiles()[0]);
        }
        return "index";
    }

}
