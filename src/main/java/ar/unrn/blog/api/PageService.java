package ar.unrn.blog.api;

import ar.unrn.blog.domain.dto.PageDTO;

public interface PageService {

    PageDTO findPage(String id);

    void createPage(PageDTO newPage);
}
