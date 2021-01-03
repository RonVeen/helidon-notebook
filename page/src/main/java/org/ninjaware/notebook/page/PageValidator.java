package org.ninjaware.notebook.page;

import org.ninjaware.notebook.model.enums.PageStyle;
import org.ninjaware.notebook.model.enums.SortOrder;
import org.ninjaware.notebook.model.enums.SortStyle;

import javax.enterprise.context.Dependent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;

@Dependent
public class PageValidator {

    List<BiFunction<PageDTO, List<String>, Boolean>> newValidations = List.of(
            this::titleRequired, this::validNotebookStyle, this::validSortStyle, this::validSortOrder);

    List<BiFunction<PageDTO, List<String>, Boolean>> updateValidations = List.of(
            this::titleRequired, this::validNotebookStyle, this::validSortStyle, this::validSortOrder);

    public List<String> validateCreate(PageDTO dto) {
        List<String> errors = new ArrayList<>();
        newValidations.forEach(f -> f.apply(dto, errors));
        return errors;
    }

    public List<String> validateUpdate(PageDTO dto) {
        List<String> errors = new ArrayList<>();
        for (BiFunction<PageDTO, List<String>, Boolean> f : updateValidations) {
            f.apply(dto, errors);
        }
        return errors;
    }

    boolean titleRequired(PageDTO dto, List<String> errors) {
        if (Objects.isNull(dto.title) || dto.title.isEmpty()) {
            errors.add("Missing title");
            return false;
        }
        return true;
    }

    boolean validNotebookStyle(PageDTO dto, List<String> errors) {
        if (!PageStyle.isValid(dto.pageStyle)) {
            errors.add(String.format("Invalid NotebookStyle: %s", dto.pageStyle));
            return false;
        }
        return true;
    }

    boolean validSortStyle(PageDTO dto, List<String> errors) {
        if (!SortStyle.isValid(dto.sortStyle)) {
            errors.add(String.format("Invalid SortStyle: %s", dto.sortStyle));
            return false;
        }
        return true;
    }

    boolean validSortOrder(PageDTO dto, List<String> errors) {
        if (!SortOrder.isValid(dto.sortOrder)) {
            errors.add(String.format("Invalid SortOrder: %s", dto.sortStyle));
            return false;
        }
        return true;
    }
}
