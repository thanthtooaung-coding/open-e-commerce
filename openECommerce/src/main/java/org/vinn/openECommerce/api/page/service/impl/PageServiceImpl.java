package org.vinn.openECommerce.api.page.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.vinn.openECommerce.api.auditLog.systemAdmin.dto.SystemAdminAuditLogDTO;
import org.vinn.openECommerce.api.auditLog.systemAdmin.service.SystemAdminAuditLogService;
import org.vinn.openECommerce.api.auditLog.systemAdmin.util.SystemAdminAuditLogEntityType;
import org.vinn.openECommerce.api.page.dto.PageDTO;
import org.vinn.openECommerce.api.page.model.Page;
import org.vinn.openECommerce.api.page.repository.PageRepository;
import org.vinn.openECommerce.api.page.service.PageService;
import org.vinn.openECommerce.api.page.util.UrlGenerator;
import org.vinn.openECommerce.api.pagePayment.dto.AddPagePaymentRequest;
import org.vinn.openECommerce.api.pagePayment.dto.PagePaymentDTO;
import org.vinn.openECommerce.api.pagePayment.service.PagePaymentService;
import org.vinn.openECommerce.exception.DuplicateEntityException;

@Slf4j
@Service
public class PageServiceImpl implements PageService {

    private final PageRepository pageRepository;
    private final ModelMapper modelMapper;
    private final UrlGenerator urlGenerator;
    private final PagePaymentService pagePaymentService;
    private final SystemAdminAuditLogService systemAdminAuditLogService;

    public PageServiceImpl(PageRepository pageRepository, ModelMapper modelMapper, UrlGenerator urlGenerator, PagePaymentService pagePaymentService, SystemAdminAuditLogService systemAdminAuditLogService) {
        this.pageRepository = pageRepository;
        this.modelMapper = modelMapper;
        this.urlGenerator = urlGenerator;
        this.pagePaymentService = pagePaymentService;
        this.systemAdminAuditLogService = systemAdminAuditLogService;
    }

    @Override
    public PageDTO createPage(PageDTO pageDto) {
        validatePageNameUniqueness(pageDto.getName());

        Page pageEntity = modelMapper.map(pageDto, Page.class);
        Page savedPage = pageRepository.save(pageEntity);

        String generatedUrl = urlGenerator.generateUrl(pageDto.getShortName(), savedPage.getId());
        savedPage.setUrl(generatedUrl);

        Page updatedPage = pageRepository.save(savedPage);

        AddPagePaymentRequest pagePaymentDto = new AddPagePaymentRequest();
        pagePaymentDto.setPageOwner(updatedPage.getPageOwner());
        PagePaymentDTO pagePaymentDTO = pagePaymentService.createPagePayment(pagePaymentDto);
        if (pagePaymentDTO != null) {
            SystemAdminAuditLogDTO systemAdminAuditLogDTO = new SystemAdminAuditLogDTO("Page Created", SystemAdminAuditLogEntityType.PAGE, updatedPage.getId().toString());
            systemAdminAuditLogService.createSystemAdminAuditLog(systemAdminAuditLogDTO);
            log.atDebug().log("Created new payment for page {}", pageDto.getName());
        }

        return modelMapper.map(updatedPage, PageDTO.class);
    }

    @Override
    public PageDTO updatePage(PageDTO pageDto) {
        Page existingPage = pageRepository.findById(pageDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Page with ID " + pageDto.getId() + " not found"));

        if (!existingPage.getName().equals(pageDto.getName()) && isPageNameExists(pageDto.getName())) {
            throw new DuplicateEntityException("A page with the name '" + pageDto.getName() + "' already exists.");
        }

        modelMapper.map(pageDto, existingPage);

        if (!existingPage.getShortName().equals(pageDto.getShortName())) {
            String updatedUrl = urlGenerator.generateUrl(pageDto.getShortName(), existingPage.getId());
            existingPage.setUrl(updatedUrl);
        }

        Page updatedPage = pageRepository.save(existingPage);

        log.atInfo().log("Page with ID {} updated successfully", updatedPage.getId());

        return modelMapper.map(updatedPage, PageDTO.class);
    }

    @Override
    public PageDTO getPageByUrl(String url) {
        Page page = pageRepository.findByUrl(url)
                .orElseThrow(() -> new IllegalArgumentException("Page with URL '" + url + "' not found"));
        return modelMapper.map(page, PageDTO.class);
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
