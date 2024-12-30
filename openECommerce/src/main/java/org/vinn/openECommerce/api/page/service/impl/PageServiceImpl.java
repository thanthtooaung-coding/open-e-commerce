package org.vinn.openECommerce.api.page.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.vinn.openECommerce.api.page.dto.PageDTO;
import org.vinn.openECommerce.api.page.model.Page;
import org.vinn.openECommerce.api.page.repository.PageRepository;
import org.vinn.openECommerce.api.page.service.PageService;
import org.vinn.openECommerce.api.page.util.UrlGenerator;
import org.vinn.openECommerce.exception.DuplicateEntityException;

@Service
public class PageServiceImpl implements PageService {

    private final PageRepository pageRepository;
    private final ModelMapper modelMapper;
    private final UrlGenerator urlGenerator;

    public PageServiceImpl(PageRepository pageRepository, ModelMapper modelMapper, UrlGenerator urlGenerator) {
        this.pageRepository = pageRepository;
        this.modelMapper = modelMapper;
        this.urlGenerator = urlGenerator;
    }

    @Override
    public PageDTO createPage(PageDTO pageDto) {
        validatePageNameUniqueness(pageDto.getName());

        Page pageEntity = modelMapper.map(pageDto, Page.class);
        Page savedPage = pageRepository.save(pageEntity);

        String generatedUrl = urlGenerator.generateUrl(pageDto.getShortName(), savedPage.getId());
        savedPage.setUrl(generatedUrl);

        Page updatedPage = pageRepository.save(savedPage);

        // Need to implement payment methods in here #Vinn

        return modelMapper.map(updatedPage, PageDTO.class);
    }

    private void validatePageNameUniqueness(String name) {
        if (isPageNameExists(name)) {
            throw new DuplicateEntityException("A page with the name '" + name + "' already exists.");
        }
    }

    private boolean isPageNameExists(String name) {
        return pageRepository.findByName(name).isPresent();
    }
}
