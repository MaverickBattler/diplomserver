package edu.leti.diplomserver;

import edu.leti.diplomserver.domain.OksResult;
import edu.leti.diplomserver.domain.User;
import edu.leti.diplomserver.dto.OksResultDto;
import edu.leti.diplomserver.repository.OksResultRepository;
import edu.leti.diplomserver.service.OksResultService;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest(classes = {DiplomserverApplication.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OksResultServiceTests {

    private final int N = 5;

    @MockBean
    private OksResultRepository oksResultRepository;

    @Autowired
    private OksResultService oksResultService;

    private List<OksResult> addedOksResults;

    @BeforeAll
    public void setup() {
        oksResultRepository.deleteById(1L);
        addedOksResults = new ArrayList<>(N);

        for (int i = 0; i < N; i++) {
            OksResultDto oksResultDto =
                    OksResultDto.builder()
                            .email("email" + i + "@tmail.com")
                            .answer1((short) (i + 1)).answer2((short) (i + 1))
                            .answer3((short) (i + 1)).answer4((short) (i + 1))
                            .answer5((short) (i + 1)).answer6((short) (i + 1))
                            .answer7((short) (i + 1)).answer8((short) (i + 1))
                            .answer9((short) (i + 1)).answer10((short) (i + 1))
                            .answer11((short) (i + 1)).answer12((short) (i + 1)).build();
            OksResult oksResult = OksResult.builder()
                    .user(new User())
                    .answer1(oksResultDto.getAnswer1())
                    .answer2(oksResultDto.getAnswer2())
                    .answer3(oksResultDto.getAnswer3())
                    .answer4(oksResultDto.getAnswer4())
                    .answer5(oksResultDto.getAnswer5())
                    .answer6(oksResultDto.getAnswer6())
                    .answer7(oksResultDto.getAnswer7())
                    .answer8(oksResultDto.getAnswer8())
                    .answer9(oksResultDto.getAnswer9())
                    .answer10(oksResultDto.getAnswer10())
                    .answer11(oksResultDto.getAnswer11())
                    .answer12(oksResultDto.getAnswer12()).build();
            addedOksResults.add(oksResult);
        }
    }

    @Test
    @Order(1)
    public void getAllOksResultsTest() {
        when(oksResultRepository.findAll()).thenReturn(addedOksResults);

        List<OksResult> gottenOksResults = oksResultService.getAllOksResults();

        assertEquals(N, gottenOksResults.size());
        int i = 0;
        for (OksResult oksResult : addedOksResults) {
            assertEquals(oksResult.getId(), gottenOksResults.get(i++).getId());
        }
    }

    @Test
    @Order(2)
    public void getOksResultTest() {
        when(oksResultRepository.getById(4L)).thenReturn(addedOksResults.get(3));

        assertEquals(
                addedOksResults.get(3).getAnswer1(),
                oksResultService.getOksResult(4L).getAnswer1()
        );
    }

    @Test
    @Order(3)
    public void addOksResultTest() {
        OksResultDto oksResultDto =
                OksResultDto.builder()
                        .email("email2@mai.ru")
                        .answer1((short) 4).answer2((short) 4).answer3((short) 4)
                        .answer4((short) 4).answer5((short) 4).answer6((short) 4)
                        .answer7((short) 4).answer8((short) 4).answer9((short) 4)
                        .answer10((short) 4).answer11((short) 4).answer12((short) 4).build();
        OksResult oksResult = OksResult.builder()
                .user(new User())
                .answer1(oksResultDto.getAnswer1())
                .answer2(oksResultDto.getAnswer2())
                .answer3(oksResultDto.getAnswer3())
                .answer4(oksResultDto.getAnswer4())
                .answer5(oksResultDto.getAnswer5())
                .answer6(oksResultDto.getAnswer6())
                .answer7(oksResultDto.getAnswer7())
                .answer8(oksResultDto.getAnswer8())
                .answer9(oksResultDto.getAnswer9())
                .answer10(oksResultDto.getAnswer10())
                .answer11(oksResultDto.getAnswer11())
                .answer12(oksResultDto.getAnswer12()).build();
        addedOksResults.add(oksResult);

        when(oksResultRepository.save(oksResult))
                .thenReturn(addedOksResults.get(addedOksResults.size() - 1));
        when(oksResultRepository.getById(oksResult.getId()))
                .thenReturn(addedOksResults.get(addedOksResults.size() - 1));

        oksResultService.addOksResult(oksResultDto);

        assertEquals(oksResult, oksResultService
                .getOksResult(addedOksResults.get(addedOksResults.size() - 1).getId()));
    }

    @Test
    @Order(4)
    public void removeOksResultTest() {
        int index = 0;
        oksResultService.removeOksResult(addedOksResults.get(index).getId());
        Mockito.verify(oksResultRepository).deleteById(any());
        addedOksResults.remove(index);
    }
}
