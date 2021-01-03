
package org.ninjaware.notebook.page;

import org.ninjaware.notebook.model.core.Page;
import org.ninjaware.notebook.model.system.ErrorResponse;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

/**
 * A simple JAX-RS resource to greet you. Examples:
 *
 */
@Path("/page")
@RequestScoped
public class PageResource {

    private final PageService pageService;
    private final PageValidator validator;

    private final PageMapper mapper = new PageMapper();


    @Inject
    public PageResource(PageService pageService, PageValidator validator) {
        this.pageService = pageService;
        this.validator = validator;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllPages() {
        List<Page> pages = pageService.getAllPages();
        if (pages.isEmpty()) {
            return Response.noContent().build();
        }
        List<PageDTO> dtos = pages.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return Response.ok(dtos).build();
    }


    @GET
    @Path("{externalId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPage(@PathParam("externalId") String externalId) {
        Page page = pageService.findPage(externalId);
        if (isNull(page)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(mapper.toDto(page)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPage(PageDTO input) {
        List<String> errors = validator.validateCreate(input);
        if (!errors.isEmpty()) {
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new ErrorResponse(Response.Status.PRECONDITION_FAILED.getStatusCode(), errors))
                    .build();
        }

        Page page = mapper.toDomain(input);
        page = pageService.createPage(page);

        return Response.status(Response.Status.CREATED).entity(mapper.toDto(page)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePage(PageDTO input) {
        List<String> errors = validator.validateUpdate(input);
        if (!errors.isEmpty()) {
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new ErrorResponse(Response.Status.PRECONDITION_FAILED.getStatusCode(), errors))
                    .build();
        }

        Page page = mapper.toDomain(input);
        Page updated = pageService.updatePage(page);
        if (isNull(updated)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(mapper.toDto(updated)).build();
    }


    @DELETE
    @Path("{externalId}")
    public Response deletePage(@PathParam("externalId")String externalId) {
        Page deletedPage = pageService.deletePage(externalId);
        if (isNull(deletedPage)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.NO_CONTENT).entity(mapper.toDto(deletedPage)).build();
    }

}
