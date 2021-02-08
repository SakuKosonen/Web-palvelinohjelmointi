/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filemanager;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {

    @Autowired
    private FileRepository fileObjectRepository;

    @PostMapping("/files")
    public String save(@RequestParam("file") MultipartFile file) throws IOException {
        File fo = new File();

        fo.setName(file.getOriginalFilename());
        fo.setContentType(file.getContentType());
        fo.setContentLength(file.getSize());
        fo.setContent(file.getBytes());

        fileObjectRepository.save(fo);

        

        return "redirect:/files";
    }

    @GetMapping("/files")
    public String list(Model model) {
        model.addAttribute("files", fileObjectRepository.findAll());
        return "files";
    }

    /* @GetMapping("/files/{id}")
    public String viewFile(@PathVariable Long id, Model model) {
        model.addAttribute("file", fileObjectRepository.getOne(id));
        return "file";
    }*/
    @GetMapping("/files/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> viewFile(@PathVariable Long id) {
        File fo = fileObjectRepository.getOne(id);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(fo.getContentType()));
        headers.setContentLength(fo.getContentLength());
        headers.add("Content-Disposition", "attachment; filename=" + fo.getName());

        return new ResponseEntity<>(fo.getContent(), headers, HttpStatus.CREATED);
    }
}
