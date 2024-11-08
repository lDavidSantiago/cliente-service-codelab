package com.denkitronik.clienteservice;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ClienteServiceApplication.class)
class ClienteServiceApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * Contenedor de Postgresql que se utilizará para las pruebas
     * Se utiliza la imagen de PostgreSQL 14
     * Se inicia el contenedor antes de todas las pruebas
     */
    private static final PostgreSQLContainer<?> postgresqlDB = new PostgreSQLContainer<>(
            DockerImageName.parse("postgres:14")
                    .asCompatibleSubstituteFor("postgres")
    );

    /**
     * Inicia el contenedor de PostgreSQL antes de todas las pruebas
     */
    @BeforeAll
    static void setup() {
        postgresqlDB.start();  // Inicia el contenedor antes de todas las pruebas
    }

    /**
     * Configura las propiedades de la base de datos de forma dinámica antes de que se cargue el contexto de Spring
     * @param registry Registro de propiedades dinámicas
     */
    @DynamicPropertySource
    static void registerProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresqlDB::getJdbcUrl);
        registry.add("spring.datasource.username", postgresqlDB::getUsername);
        registry.add("spring.datasource.password", postgresqlDB::getPassword);
    }

    /**
     * Verifica que el contenedor de PostgreSQL esté en ejecución
     */
    @Test
    void testContainerIsRunning() {
        assertTrue(postgresqlDB.isRunning(), "El contenedor de TimescaleDB debería estar ejecutándose");
    }

    @Test
    void contextLoads() {
        assertNotNull(applicationContext, "El contexto de la aplicación no debería ser nulo");
    }

}
