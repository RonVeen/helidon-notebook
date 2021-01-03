package org.ninjaware.notebook.page;

import org.ninjaware.notebook.model.core.Page;
import org.ninjaware.notebook.model.enums.PageStyle;
import org.ninjaware.notebook.model.enums.SortOrder;
import org.ninjaware.notebook.model.enums.SortStyle;
import org.ninjaware.notebook.model.system.EntityStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.nonNull;

public class PageMapper {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Page toDomain(PageDTO dto) {
        Page p = new Page();
        if (nonNull(dto.createdAt)) {
            p.setCreatedAt(LocalDateTime.parse(dto.createdAt, formatter));
        }
        p.setCreatedBy(dto.createdBy);
        p.setDescription(dto.description);
        p.setExternalId(dto.externalId);
        p.setPageStyle(PageStyle.toEnum(dto.pageStyle));
        p.setSortOrder(SortOrder.toEnum(dto.sortOrder));
        p.setSortStyle(SortStyle.toEnum(dto.sortStyle));
        if (nonNull(dto.state)) {
            p.setState(EntityStatus.valueOf(dto.state));
        }
        if (nonNull(dto.updatedAt)) {
            p.setUpdatedAt(LocalDateTime.parse(dto.updatedAt, formatter));
        }
        p.setUpdatedBy(dto.updatedBy);
        p.setTitle(dto.title);
        return p;
    }

    public PageDTO toDto(Page page) {
        PageDTO dto = new PageDTO();
        dto.createdAt = formatter.format(page.getCreatedAt());
        dto.createdBy = page.getCreatedBy();
        dto.description = page.getDescription();
        dto.externalId = page.getExternalId();
        dto.pageStyle = page.getPageStyle().name();
        dto.sortOrder = page.getSortOrder().name();
        dto.sortStyle = page.getSortStyle().name();
        if (nonNull(page.getUpdatedAt())) {
            dto.updatedAt = formatter.format(page.getUpdatedAt());
        }
        dto.updatedBy = page.getUpdatedBy();
        dto.state = page.getState().name();
        dto.title = page.getTitle();
        return dto;
    }
}
