package ar.unrn.blog.web;

import ar.unrn.blog.api.PageService;
import ar.unrn.blog.domain.dto.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/pages")
public class PageController {

    @Autowired
    private PageService pageService;

    @GetMapping("/{id}")
    List<PageDTO> findPageById(@PathVariable String id){
        return Collections.singletonList(this.pageService.findPage(id));
    }

    @PostMapping("/cerate")
    void createPage(@RequestBody PageDTO newPage){
        this.pageService.createPage(newPage);
    }
}
