package org.ninjaware.notebook.page;

import org.ninjaware.notebook.model.core.Page;
import org.ninjaware.notebook.model.system.EntityStatus;
import org.ninjaware.notebook.model.system.KeyGenerator;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Dependent
public class PageService {

    PagePersistence pagePersistence;

    @Context
    SecurityContext securityContext;

    @Inject
    public PageService(PagePersistence pagePersistence) {
        this.pagePersistence = pagePersistence;
    }


    public List<Page> getAllPages() {
        return pagePersistence.findAll();
    }

    public Page findPage(String externalId) {
        Optional<Page> page = pagePersistence.findByExternalId(externalId);
        return page.orElse(null);
    }

    public Page createPage(Page page) {
        page.setExternalId(KeyGenerator.getInstance().next());
        page.setUid(KeyGenerator.getInstance().next());
        page.setCreatedAt(LocalDateTime.now());
        page.setCreatedBy(securityContext.getUserPrincipal().getName());
        page.setState(EntityStatus.Active);
        pagePersistence.create(page);
        return page;
    }


    public Page updatePage(Page page) {
        Optional<Page> original = pagePersistence.findByExternalId(page.getExternalId());
        if (original.isEmpty()) {
            return null;
        }
        Page updated = original.get();
        updated.setSortStyle(page.getSortStyle());
        updated.setSortOrder(page.getSortOrder());
        updated.setPageStyle(page.getPageStyle());
        updated.setDescription(page.getDescription());
        updated.setTitle(page.getTitle());
        updated.setUpdatedAt(LocalDateTime.now());
        updated.setUpdatedBy(securityContext.getUserPrincipal().getName());
        updated = pagePersistence.update(updated);
        return updated;
    }

    public Page deletePage(String externalId) {
        Page deleted = pagePersistence.delete(externalId);
        if (isNull(deleted)) {
            return null;
        }
        return deleted;
    }

}
