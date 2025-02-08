package ru.hofftech.logisticservice.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@Sql(scripts = "/test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/cleanup-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class BoxControllerTest {

    @Container
    public static PostgreSQLContainer<?> postgresqlContainer = new PostgreSQLContainer<>("postgres:17")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    static void beforeAll() {
        postgresqlContainer.start();
        System.setProperty("spring.datasource.url", postgresqlContainer.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgresqlContainer.getUsername());
        System.setProperty("spring.datasource.password", postgresqlContainer.getPassword());
    }

    @Test
    void testFindAllBoxes() throws Exception {
        mockMvc.perform(get("/api/v1/boxes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testFindBoxByName() throws Exception {
        String boxName = "TestBox";

        mockMvc.perform(get("/api/v1/boxes/{name}", boxName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testCreateBox() throws Exception {
        String boxJson = """
                {
                    "name": "TestBox",
                    "form": "xx\\nxx",
                    "symbol": "x"
                }
                """;

        mockMvc.perform(post("/api/v1/boxes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(boxJson))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testUpdateBox() throws Exception {
        String boxJson = """
                {
                    "oldName": "TestBox",
                    "name": "TestBox1",
                    "form": "xx\\nxx",
                    "symbol": "x"
                }
                """;

        mockMvc.perform(put("/api/v1/boxes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(boxJson))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testDeleteBox() throws Exception {
        String boxName = "TestBox";

        mockMvc.perform(delete("/api/v1/boxes/{name}", boxName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testLoadBoxes() throws Exception {
        String loadParamJson = """
                {
                    "clientName": "TestClient",
                    "trucks": "6x6\\n6x6",
                    "parcelsText": "TestBox",
                    "date": "2025-01-11",
                    "type": "ONE_TO_ONE"
                }
                """;

        mockMvc.perform(post("/api/v1/boxes/action/load")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loadParamJson))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testUnloadBoxes() throws Exception {
        String unloadParamJson = """
                {
                    "clientName": "Petrov",
                    "date": "2025-02-06",
                    "inFilename": "trucks.json",
                    "outFilename": "output.csv",
                    "withCount": true
                }
                """;

        mockMvc.perform(post("/api/v1/boxes/action/unload")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(unloadParamJson))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testImportBoxes() throws Exception {
        String importParamJson = """
                {"filename":"boxes.txt"}
                """;

        mockMvc.perform(post("/api/v1/boxes/action/import")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(importParamJson))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}