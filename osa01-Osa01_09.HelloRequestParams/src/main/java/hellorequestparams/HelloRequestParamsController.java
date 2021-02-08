package hellorequestparams;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloRequestParamsController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam String param) {
        return "Hello " + param;
    }

    @GetMapping("/params")
    @ResponseBody
    public String params(@RequestParam Map<String, String> parametrit) {
        ArrayList<String> lista = new ArrayList();
        Set<String> avaimet = parametrit.keySet();
        for (String avain : avaimet) {
            String jotain = avain + "=" + parametrit.get(avain);
            lista.add(jotain);
        }

        String palautettava = "{";
        for (int i = 0; i < lista.size(); i++) {
            if (i == lista.size() - 1) {
                palautettava = palautettava + lista.get(i);
            } else {
                palautettava = palautettava + lista.get(i) + ", ";
            }
        }

        palautettava = palautettava + "}";

        return palautettava;
    }

}
