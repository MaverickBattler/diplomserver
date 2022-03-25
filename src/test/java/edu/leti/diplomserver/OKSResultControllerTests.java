package edu.leti.diplomserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.leti.diplomserver.domain.OKSResult;
import edu.leti.diplomserver.service.OKSResultService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(classes = {DiplomserverApplication.class})
@AutoConfigureMockMvc
@EnableWebMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OKSResultControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OKSResultService oksResultService;

    @Test
    @Order(1)
    public void addOKSResultTest() throws Exception {
        OKSResult oksResult = OKSResult.builder()
                .answer1((short) 1).answer2((short) 2).answer3((short) 3)
                .answer4((short) 4).answer5((short) 5).answer6((short) 1)
                .answer7((short) 2).answer8((short) 3).answer9((short) 4)
                .answer10((short) 5).answer11((short) 1).answer12((short) 2).build();

        when(oksResultService.addOKSResult(oksResult)).thenReturn(oksResult);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/oksResult")
                .content(asJsonString(oksResult))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    public void deleteOKSResultTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/oksResult/{id}", 1))
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
