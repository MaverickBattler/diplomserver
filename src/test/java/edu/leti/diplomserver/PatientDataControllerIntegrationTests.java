package edu.leti.diplomserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(classes = {DiplomserverApplication.class})
@AutoConfigureMockMvc
@EnableWebMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PatientDataControllerIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void addPatientTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/patientData")
                .content(asJsonString(PatientData.builder()
                        .answer1(1).answer2(2).answer3(3)
                        .answer4(4).answer5(5).answer6(1)
                        .answer7(2).answer8(3).answer9(4)
                        .answer10(5).answer11(1).answer12(2).build()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    public void deletePatientTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/patientData/{id}", 1))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
