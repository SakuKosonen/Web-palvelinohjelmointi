package reloadheroes;

import java.util.UUID;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReloadController {
    
    @Autowired
    private ReloadStatusRepository reloadStatusRepository;

    @Autowired
    private HttpSession session;

    @RequestMapping("*")
    public String reload(Model model) {

        ReloadStatus rs = new ReloadStatus();
        int count = 0;

        if (session.getAttribute("count") != null) {
            count = (int) session.getAttribute("count");
        }

        count++;
        session.setAttribute("count", count);

        if (count == 1) {
            rs.setName(UUID.randomUUID().toString());
            rs.setReloads(1);
            session.setAttribute("status", rs);
            reloadStatusRepository.save(rs);
        } else {
            ReloadStatus rs2 = (ReloadStatus) session.getAttribute("status");
            ReloadStatus rs3 = this.reloadStatusRepository.findByName(rs2.getName());
            rs.setReloads(rs3.getReloads() + 1);
            rs.setName(rs3.getName());
            
                        
            session.setAttribute("status", rs);
            
            rs3.setReloads(rs.getReloads());
            reloadStatusRepository.save(rs3);
        }
        
        model.addAttribute("status", rs);
        
        
        
        Pageable pageable = PageRequest.of(0, 5, Sort.by("reloads").descending());
        model.addAttribute("scores", reloadStatusRepository.findAll(pageable));
        return "index";
    }

   
}
