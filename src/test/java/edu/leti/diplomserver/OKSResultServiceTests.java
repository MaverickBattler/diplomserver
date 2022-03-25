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
                            .answer1((short) (i + 1)).answer2((short) (i + 1))
                            .answer3((short) (i + 1)).answer4((short) (i + 1))
                            .answer5((short) (i + 1)).answer6((short) (i + 1))
                            .answer7((short) (i + 1)).answer8((short) (i + 1))
                            .answer9((short) (i + 1)).answer10((short) (i + 1))
                            .answer11((short) (i + 1)).answer12((short) (i + 1)).build();
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
            assertEquals(oksResult.getId(), gottenOKSResults.get(i++).getId());
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
                        .answer1((short) 4).answer2((short) 4).answer3((short) 4)
                        .answer4((short) 4).answer5((short) 4).answer6((short) 4)
                        .answer7((short) 4).answer8((short) 4).answer9((short) 4)
                        .answer10((short) 4).answer11((short) 4).answer12((short) 4).build();
        addedOKSResults.add(oksResult);

        when(oksResultRepository.save(oksResult))
                .thenReturn(addedOKSResults.get(addedOKSResults.size() - 1));
        when(oksResultRepository.getById(oksResult.getId()))
                .thenReturn(addedOKSResults.get(addedOKSResults.size() - 1));

        oksResultService.addOKSResult(oksResult);

        assertEquals(oksResult, oksResultService
                .getOKSResult(addedOKSResults.get(addedOKSResults.size() - 1).getId()));
    }

    @Test
    @Order(4)
    public void removeOKSResultTest() {
        int index = 0;
        oksResultService.removeOKSResult(addedOKSResults.get(index).getId());
        Mockito.verify(oksResultRepository).deleteById(any());
        addedOKSResults.remove(index);
    }
}
