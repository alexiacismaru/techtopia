package be.kdg.prog6.boundedcontextB.adapters.in.web;

import be.kdg.prog6.boundedcontextB.adapters.out.AttractionProjectionJpaEntity;
import be.kdg.prog6.boundedcontextB.ports.in.ManagePOIUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@Testcontainers
class SecureParkInfoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ManagePOIUseCase managePOIUseCase;

    @Test
    public void testGetSecureAttractions() throws Exception {
        AttractionProjectionJpaEntity attraction1 = new AttractionProjectionJpaEntity("name1", "description1", "location1", "ageGroup1", "image1", "tags1");
        AttractionProjectionJpaEntity attraction2 = new AttractionProjectionJpaEntity("name2", "description2", "location2", "ageGroup2", "image2", "tags2");
        List<AttractionProjectionJpaEntity> attractions = Arrays.asList(attraction1, attraction2);

        Mockito.when(managePOIUseCase.getAllAttractions()).thenReturn(attractions);

        mockMvc.perform(MockMvcRequestBuilders.get("/attractions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("name1")))
                .andExpect(jsonPath("$[0].location", is("location1")))
                .andExpect(jsonPath("$[0].description", is("description1")))
                .andExpect(jsonPath("$[1].name", is("name2")))
                .andExpect(jsonPath("$[1].location", is("location2")))
                .andExpect(jsonPath("$[1].description", is("description2")));

        Mockito.verify(managePOIUseCase, Mockito.times(1)).getAllAttractions();
    }
}
