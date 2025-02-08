package ru.hofftech.logisticbilling.controller;

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
import org.testcontainers.kafka.KafkaContainer;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@Sql(scripts = "/test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/cleanup-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class BillingControllerTest {

    @Container
    public static PostgreSQLContainer<?> postgresqlContainer = new PostgreSQLContainer<>("postgres:17")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @Container
    public static KafkaContainer kafkaContainer = new KafkaContainer("apache/kafka-native:3.8.0");

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    static void beforeAll() {
        kafkaContainer.start();
        postgresqlContainer.start();
        System.setProperty("spring.datasource.url", postgresqlContainer.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgresqlContainer.getUsername());
        System.setProperty("spring.datasource.password", postgresqlContainer.getPassword());
        System.setProperty("spring.kafka.bootstrap-servers", kafkaContainer.getBootstrapServers());
    }

    @Test
    void testFindAllOrders() throws Exception {
        String resultForCheck = """
                {
                    "content": [
                        {
                            "id": 1,
                            "type": "LOAD",
                            "clientName": "Petrov",
                            "date": "2025-01-11",
                            "truckCount": 5,
                            "boxCount": 20,
                            "segmentCount": 101,
                            "amount": 120
                        },
                        {
                            "id": 2,
                            "type": "UNLOAD",
                            "clientName": "Petrov",
                            "date": "2025-01-11",
                            "truckCount": 5,
                            "boxCount": 20,
                            "segmentCount": 101,
                            "amount": 120
                        },
                        {
                            "id": 3,
                            "type": "LOAD",
                            "clientName": "Petrov",
                            "date": "2025-01-12",
                            "truckCount": 6,
                            "boxCount": 17,
                            "segmentCount": 133,
                            "amount": 120
                        },
                        {
                            "id": 4,
                            "type": "LOAD",
                            "clientName": "Ivanov",
                            "date": "2025-01-11",
                            "truckCount": 4,
                            "boxCount": 10,
                            "segmentCount": 95,
                            "amount": 120
                        },
                        {
                            "id": 5,
                            "type": "UNLOAD",
                            "clientName": "Ivanov",
                            "date": "2025-01-12",
                            "truckCount": 4,
                            "boxCount": 10,
                            "segmentCount": 95,
                            "amount": 120
                        },
                        {
                            "id": 6,
                            "type": "LOAD",
                            "clientName": "Ivanov",
                            "date": "2025-01-13",
                            "truckCount": 7,
                            "boxCount": 15,
                            "segmentCount": 158,
                            "amount": 120
                        }
                    ],
                    "pageable": {
                        "pageNumber": 0,
                        "pageSize": 20,
                        "sort": {
                            "sorted": false,
                            "empty": true,
                            "unsorted": true
                        },
                        "offset": 0,
                        "paged": true,
                        "unpaged": false
                    },
                    "last": true,
                    "totalElements": 6,
                    "totalPages": 1,
                    "first": true,
                    "size": 20,
                    "number": 0,
                    "sort": {
                        "sorted": false,
                        "empty": true,
                        "unsorted": true
                    },
                    "numberOfElements": 6,
                    "empty": false
                }
                """.replace(" ", "").replace("\n", "");

        mockMvc.perform(get("/api/v1/billing")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(resultForCheck));
    }

    @Test
    void testFindByNameWithPeriod() throws Exception {
        String resultForCheck = """
                [
                    {
                        "id": 1,
                        "type": "LOAD",
                        "clientName": "Petrov",
                        "date": "2025-01-11",
                        "truckCount": 5,
                        "boxCount": 20,
                        "segmentCount": 101,
                        "amount": 120
                    },
                    {
                        "id": 2,
                        "type": "UNLOAD",
                        "clientName": "Petrov",
                        "date": "2025-01-11",
                        "truckCount": 5,
                        "boxCount": 20,
                        "segmentCount": 101,
                        "amount": 120
                    },
                    {
                        "id": 3,
                        "type": "LOAD",
                        "clientName": "Petrov",
                        "date": "2025-01-12",
                        "truckCount": 6,
                        "boxCount": 17,
                        "segmentCount": 133,
                        "amount": 120
                    }
                ]
                """.replace(" ", "").replace("\n", "");

        String clientName = "Petrov";
        LocalDate startDate = LocalDate.of(2025, 1, 11);
        LocalDate endDate = LocalDate.of(2025, 1, 12);

        mockMvc.perform(get("/api/v1/billing/find")
                        .param("clientName", clientName)
                        .param("startDate", startDate.toString())
                        .param("endDate", endDate.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(resultForCheck));
    }
}