package org.ninjaware.notebook.page;

import org.ninjaware.notebook.model.core.Page;

import java.util.List;
import java.util.Optional;

public interface PagePersistence {
    List<Page> findAll();
    void create(Page page);
    Optional<Page> findByExternalId(String externalId);
    Page update(Page page);
    Page delete(String externalID);
}
