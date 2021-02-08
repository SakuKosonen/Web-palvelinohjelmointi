/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gifbin;

/**
 *
 * @author Saku
 */
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class GifController {

    @Autowired
    private GifRepository gifRepository;

    @GetMapping("/gifs/{id}")
    public String jotain(@PathVariable Long id, Model model) {
        model.addAttribute("count", gifRepository.count());
        model.addAttribute("current", id);
        model.addAttribute("next", id + 1);
        model.addAttribute("previous", id - 1);
        return "gifs";
    }

    @GetMapping("/gifs")
    public String alku() {
        return "redirect:/gifs/1";
    }

    @GetMapping(path = "/gifs/{id}/content", produces = "image/gif")
    @ResponseBody
    public byte[] get(@PathVariable Long id) {
        return gifRepository.getOne(id).getContent();
    }

    @PostMapping("/gifs")
    public String save(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.getContentType().equals("image/gif")) {
            Gif fo = new Gif();
            fo.setContent(file.getBytes());

            gifRepository.save(fo);
        }
        return "redirect:/gifs";
    }
}
