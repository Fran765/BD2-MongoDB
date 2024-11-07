package ar.unrn.blog.services;

import ar.unrn.blog.api.PageService;
import ar.unrn.blog.domain.dto.PageDTO;
import ar.unrn.blog.repositories.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class PageServiceImpl implements PageService {

    @Autowired
    private PageRepository pageRepository;

    @Override
    public PageDTO findPage(String id) {
        return PageDTO.fromDocument(this.pageRepository.getPageById(id));
    }

    @Override
    public void createPage(PageDTO newPage) {
        this.pageRepository.createPage(
                newPage.getTitle(),
                newPage.getText(),
                newPage.getAuthor(),
                new Date()
        );
    }
}
