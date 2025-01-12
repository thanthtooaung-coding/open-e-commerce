package org.vinn.openECommerce.api.page.service;

import org.vinn.openECommerce.api.page.dto.PageDTO;

import java.util.List;

public interface PageService {
    PageDTO createPage(PageDTO pageDto);
    PageDTO updatePage(PageDTO pageDto);
    PageDTO getPageByUrl(String url);
    List<PageDTO> getPages();
}
