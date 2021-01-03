
package org.ninjaware.notebook.page;

import io.helidon.microprofile.server.Server;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.ninjaware.notebook.model.enums.PageStyle;
import org.ninjaware.notebook.model.enums.SortOrder;
import org.ninjaware.notebook.model.enums.SortStyle;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.spi.CDI;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

class PageResourceTest {

    private static Server server;
    private static String serverUrl;

    @BeforeAll
    public static void startTheServer() throws Exception {
        server = Server.create().start();
        serverUrl = "http://localhost:" + server.port();
    }

    @Test
    void testPageApi() {
        Client client = ClientBuilder.newClient();

        Response r = client
                .target(serverUrl)
                .path("/doesnotexist")
                .request()
                .get();
        Assertions.assertEquals(404, r.getStatus(), "GET status code");

        // ----------------------------------------- //
        //  Create first 1st page                    //
        // ----------------------------------------- //
        r = client
            .target(serverUrl)
            .path("/page")
            .request()
            .post(Entity.entity(createDTO(1), MediaType.APPLICATION_JSON_TYPE));
        Assertions.assertEquals(201, r.getStatus(), "POST status code");
        Assertions.assertNotNull(r.getEntity());
        PageDTO dto = r.readEntity(PageDTO.class);
        Assertions.assertNotNull(dto.externalId);
        Assertions.assertNotNull(dto.createdAt);
        Assertions.assertNotNull(dto.createdBy);

        // ----------------------------------------- //
        //  Create first 2nd page                    //
        // ----------------------------------------- //
        r = client
            .target(serverUrl)
            .path("/page")
            .request()
            .post(Entity.entity(createDTO(2), MediaType.APPLICATION_JSON_TYPE));
        Assertions.assertEquals(201, r.getStatus(), "POST status code");

        List<PageDTO> dtos = client
                .target(serverUrl)
                .path("/page")
                .request()
                .get(Response.class)
                .readEntity(new GenericType<List<PageDTO>>(){});
        Assertions.assertEquals(2, dtos.size(), "Number of PageDTO objects");

        String externalId1 = dtos.get(0).externalId;
        String externalId2 = dtos.get(1).externalId;

        // ----------------------------------------- //
        //  Retrieve existing Page                   //
        // ----------------------------------------- //
        r = client
                .target(serverUrl)
                .path("/page/" + externalId2)
                .request()
                .get();
        Assertions.assertEquals(200, r.getStatus(), "GET status code");
        Assertions.assertNotNull(r.getEntity());
        dto = r.readEntity(PageDTO.class);
        Assertions.assertEquals(externalId2, dto.externalId);

        // ----------------------------------------- //
        //  Update 2nd page                          //
        // ----------------------------------------- //
        String updatedDescription = "Updated description:" + dto.externalId;
        dto.description = updatedDescription;

        r = client
                .target(serverUrl)
                .path("/page")
                .request()
                .put(Entity.entity(dto, MediaType.APPLICATION_JSON_TYPE));
        Assertions.assertEquals(200, r.getStatus(), "POST status code");
        Assertions.assertNotNull(r.getEntity());
        PageDTO updatedDto = r.readEntity(PageDTO.class);
        Assertions.assertEquals(externalId2, updatedDto.externalId);
        Assertions.assertEquals(updatedDescription, updatedDto.description);

        // ----------------------------------------- //
        //  Remove 1st page                          //
        // ----------------------------------------- //
        r = client
                .target(serverUrl)
                .path("/page/" + externalId1)
                .request()
                .delete();
        Assertions.assertEquals(204, r.getStatus(), "DELETE status code");

        // ----------------------------------------- //
        //  Remove 1st page                          //
        // ----------------------------------------- //
        r = client
                .target(serverUrl)
                .path("/page/" + externalId1)
                .request()
                .get();
        Assertions.assertEquals(404, r.getStatus(), "GET status code");
    }

    @AfterAll
    static void destroyClass() {
        CDI<Object> current = CDI.current();
        ((SeContainer) current).close();
    }


    private PageDTO createDTO(int sequence) {
        PageDTO dto = new PageDTO();
        dto.sortStyle = SortStyle.Alphabetic.name();
        dto.sortOrder = SortOrder.Ascending.name();
        dto.description = "dto description " + sequence;
        dto.title = "dto " + sequence;
        dto.pageStyle = PageStyle.Simple.name();
        return dto;
    }

}
