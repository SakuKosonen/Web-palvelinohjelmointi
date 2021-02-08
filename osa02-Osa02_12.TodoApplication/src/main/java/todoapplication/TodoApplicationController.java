package todoapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodoApplicationController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("items", todoRepository.findAll());
        return "index";
    }
    
    @PostMapping("/")
    public String create(@RequestParam String name) {
        todoRepository.save(new Todo(name, 0));
        return "redirect:/";
    }
    
    @GetMapping("/{id}")
    public String single(Model model, @PathVariable Long id) {
        Todo jonne = todoRepository.getOne(id);
        jonne.check();
        todoRepository.save(jonne);     
       
        model.addAttribute("item", todoRepository.getOne(id));        
        return "todo";
    }
}
