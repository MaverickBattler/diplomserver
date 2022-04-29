package edu.leti.diplomserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.leti.diplomserver.domain.OksResult;
import edu.leti.diplomserver.domain.User;
import edu.leti.diplomserver.dto.OksResultDto;
import edu.leti.diplomserver.service.OksResultService;
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

import java.util.Date;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(classes = {DiplomserverApplication.class})
@AutoConfigureMockMvc(addFilters = false)
@EnableWebMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OksResultControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OksResultService oksResultService;

    @Test
    @Order(1)
    public void addOksResultTest() throws Exception {
        OksResultDto oksResultDto = OksResultDto.builder()
                .email("email").answer1((short) 1).answer2((short) 2).answer3((short) 3)
                .answer4((short) 4).answer5((short) 5).answer6((short) 1)
                .answer7((short) 2).answer8((short) 3).answer9((short) 4)
                .answer10((short) 5).answer11((short) 1).answer12((short) 2).build();

        when(oksResultService.addOksResult(oksResultDto)).thenReturn(1651161575925L);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/oksResult")
                .content(asJsonString(oksResultDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
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
