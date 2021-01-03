package org.ninjaware.notebook.page;


import org.ninjaware.notebook.model.core.Page;
import org.ninjaware.notebook.model.system.EntityStatus;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@ApplicationScoped
public class InMemoryPagePersistence implements PagePersistence {

    private static Map<String, Page> pages = new HashMap<>();

    public List<Page> findAll() {
        return pages.values().stream().filter(p -> p.getState() == EntityStatus.Active).collect(Collectors.toList());
    }

    @Override
    public void create(Page page) {
        pages.putIfAbsent(page.getExternalId(), page);
    }

    @Override
    public Optional<Page> findByExternalId(String externalId) {
        Page page = pages.get(externalId);
        if (nonNull(page) && page.getState() == EntityStatus.Active) {
            return Optional.of(page);
        }
        return Optional.empty();
    }

    @Override
    public Page update(Page page) {
        Page original = pages.get(page.getExternalId());
        if (nonNull(original) && original.getState() == EntityStatus.Active) {
            pages.put(page.getExternalId(), page);
            return page;
        }
        return null;
    }

    @Override
    public Page delete(String externalId) {
        Page original = pages.get(externalId);
        if (nonNull(original) && original.getState() == EntityStatus.Active) {
            pages.remove(externalId);
            return original;
        }
        return null;
    }

}
