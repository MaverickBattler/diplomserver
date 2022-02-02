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
public class PatientDataServiceTests {

    private final int N = 5;

    @MockBean
    private PatientDataRepository patientDataRepository;

    @Autowired
    private PatientDataService patientDataService;

    private List<PatientData> addedPatientsData;

    @BeforeAll
    public void setup() {
        patientDataRepository.deleteById(1L);
        addedPatientsData = new ArrayList<>(N);

        for (int i = 0; i < N; i++) {
            PatientData patientData =
                    PatientData.builder()
                            .answer1(i + 1).answer2(i + 1).answer3(i + 1)
                            .answer4(i + 1).answer5(i + 1).answer6(i + 1)
                            .answer7(i + 1).answer8(i + 1).answer9(i + 1)
                            .answer10(i + 1).answer11(i + 1).answer12(i + 1).build();
            addedPatientsData.add(patientData);
        }
    }

    @Test
    @Order(1)
    public void getAllPatientsDataTest() {
        when(patientDataRepository.findAll()).thenReturn(addedPatientsData);

        List<PatientData> gottenPatientsData = patientDataService.getAllPatientsData();

        assertEquals(N, gottenPatientsData.size());
        int i = 0;
        for (PatientData patientData : addedPatientsData) {
            assertEquals(patientData.getPatientId(), gottenPatientsData.get(i++).getPatientId());
        }
    }

    @Test
    @Order(2)
    public void getPatientDataTest() {
        when(patientDataRepository.getById(4L)).thenReturn(addedPatientsData.get(3));

        assertEquals(
                addedPatientsData.get(3).getAnswer1(),
                patientDataService.getPatientData(4L).getAnswer1()
        );
    }

    @Test
    @Order(3)
    public void addPatientDataTest() {
        PatientData patientData =
                PatientData.builder()
                        .answer1(4).answer2(4).answer3(4)
                        .answer4(4).answer5(4).answer6(4)
                        .answer7(4).answer8(4).answer9(4)
                        .answer10(4).answer11(4).answer12(4).build();
        addedPatientsData.add(patientData);

        when(patientDataRepository.save(patientData))
                .thenReturn(addedPatientsData.get(addedPatientsData.size() - 1));
        when(patientDataRepository.getById(patientData.getPatientId()))
                .thenReturn(addedPatientsData.get(addedPatientsData.size() - 1));

        patientDataService.addPatientData(patientData);

        assertEquals(patientData, patientDataService
                .getPatientData(addedPatientsData.get(addedPatientsData.size() - 1).getPatientId()));
    }

    @Test
    @Order(4)
    public void removePatientDataTest() {
        int index = 0;
        patientDataService.removePatientData(addedPatientsData.get(index).getPatientId());
        Mockito.verify(patientDataRepository).deleteById(any());
        addedPatientsData.remove(index);
    }
}
