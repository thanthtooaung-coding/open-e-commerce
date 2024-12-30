package org.vinn.openECommerce.api.page.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vinn.openECommerce.api.page.dto.PageDTO;
import org.vinn.openECommerce.api.page.service.PageService;

import java.net.URI;

/**
 * Controller for managing Page resources.
 * Exposes endpoints for creating and updating pages with RESTful conventions.
 */
@RestController
@RequestMapping("${api.base.path}/pages")
@RequiredArgsConstructor
public class PageController {

    private final PageService pageService;

    /**
     * Creates a new Page resource.
     *
     * @param pageDto the data for the new Page.
     * @return ResponseEntity containing the created Page resource.
     */
    @PostMapping
    public ResponseEntity<PageDTO> createPage(@Valid @RequestBody PageDTO pageDto) {
        PageDTO createdPage = pageService.createPage(pageDto);
        URI location = URI.create(String.format("/api/v1/pages/%s", createdPage.getId()));
        return ResponseEntity.created(location).body(createdPage);
    }

    /**
     * Updates an existing Page resource.
     *
     * @param pageDto the updated data for the Page.
     * @return ResponseEntity containing the updated Page resource.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PageDTO> updatePage(@PathVariable Long id, @Valid @RequestBody PageDTO pageDto) {
        if (!id.equals(pageDto.getId())) {
            throw new IllegalArgumentException("Path variable ID does not match body ID.");
        }
        PageDTO updatedPage = pageService.updatePage(pageDto);
        return ResponseEntity.ok(updatedPage);
    }

    @GetMapping("/{url}")
    public ResponseEntity<PageDTO> getPage(@PathVariable String url) {
        PageDTO pageDto = pageService.getPageByUrl(url);
        return ResponseEntity.ok(pageDto);
    }

}
