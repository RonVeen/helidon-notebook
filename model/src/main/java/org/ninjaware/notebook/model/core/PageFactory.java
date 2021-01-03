package org.ninjaware.notebook.model.core;


import org.ninjaware.notebook.model.system.EntityStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class PageFactory {

    private static PageFactory factory = new PageFactory();

    private PageFactory() {
    }

    public static PageFactory getInstance() {
        return factory;
    }

    public Page newPage() {
        Page page = new Page();
        page.setCreatedAt(LocalDateTime.now());
        page.setExternalId(UUID.randomUUID().toString());
        page.setState(EntityStatus.Active);
        page.setUid(UUID.randomUUID().toString());
        page.setUpdatedAt(page.getCreatedAt());
        return page;
    }

    public Page newPage(String user) {
        Page page = newPage();
        page.setCreatedBy(user);
        page.setUpdatedBy(user);
        return page;
    }
}
