package edu.leti.diplomserver;

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
public class OKSResultServiceTests {

    private final int N = 5;

    @MockBean
    private OKSResultRepository oksResultRepository;

    @Autowired
    private OKSResultService oksResultService;

    private List<OKSResult> addedOKSResults;

    @BeforeAll
    public void setup() {
        oksResultRepository.deleteById(1L);
        addedOKSResults = new ArrayList<>(N);

        for (int i = 0; i < N; i++) {
            OKSResult oksResult =
                    OKSResult.builder()
                            .answer1(i + 1).answer2(i + 1).answer3(i + 1)
                            .answer4(i + 1).answer5(i + 1).answer6(i + 1)
                            .answer7(i + 1).answer8(i + 1).answer9(i + 1)
                            .answer10(i + 1).answer11(i + 1).answer12(i + 1).build();
            addedOKSResults.add(oksResult);
        }
    }

    @Test
    @Order(1)
    public void getAllOKSResultsTest() {
        when(oksResultRepository.findAll()).thenReturn(addedOKSResults);

        List<OKSResult> gottenOKSResults = oksResultService.getAllOKSResults();

        assertEquals(N, gottenOKSResults.size());
        int i = 0;
        for (OKSResult oksResult : addedOKSResults) {
            assertEquals(oksResult.getOksResultId(), gottenOKSResults.get(i++).getOksResultId());
        }
    }

    @Test
    @Order(2)
    public void getOKSResultTest() {
        when(oksResultRepository.getById(4L)).thenReturn(addedOKSResults.get(3));

        assertEquals(
                addedOKSResults.get(3).getAnswer1(),
                oksResultService.getOKSResult(4L).getAnswer1()
        );
    }

    @Test
    @Order(3)
    public void addOKSResultTest() {
        OKSResult oksResult =
                OKSResult.builder()
                        .answer1(4).answer2(4).answer3(4)
                        .answer4(4).answer5(4).answer6(4)
                        .answer7(4).answer8(4).answer9(4)
                        .answer10(4).answer11(4).answer12(4).build();
        addedOKSResults.add(oksResult);

        when(oksResultRepository.save(oksResult))
                .thenReturn(addedOKSResults.get(addedOKSResults.size() - 1));
        when(oksResultRepository.getById(oksResult.getOksResultId()))
                .thenReturn(addedOKSResults.get(addedOKSResults.size() - 1));

        oksResultService.addOKSResult(oksResult);

        assertEquals(oksResult, oksResultService
                .getOKSResult(addedOKSResults.get(addedOKSResults.size() - 1).getOksResultId()));
    }

    @Test
    @Order(4)
    public void removeOKSResultTest() {
        int index = 0;
        oksResultService.removeOKSResult(addedOKSResults.get(index).getOksResultId());
        Mockito.verify(oksResultRepository).deleteById(any());
        addedOKSResults.remove(index);
    }
}
