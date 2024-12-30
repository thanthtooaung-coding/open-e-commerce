package org.vinn.openECommerce.api.user.pageOwner.service;

import org.vinn.openECommerce.api.user.common.service.BaseUserService;
import org.vinn.openECommerce.api.user.pageOwner.dto.AddPageOwnerRequest;
import org.vinn.openECommerce.api.user.pageOwner.dto.PageOwnerDTO;
import org.vinn.openECommerce.api.user.pageOwner.dto.UpdatePageOwnerRequest;
import org.vinn.openECommerce.api.user.pageOwner.model.PageOwner;

public interface PageOwnerService extends BaseUserService<PageOwner, PageOwnerDTO, AddPageOwnerRequest, UpdatePageOwnerRequest> {
}
