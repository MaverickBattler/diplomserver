package edu.leti.diplomserver;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
@SpringBootTest(classes = {DiplomserverApplication.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PatientDataRepositoryTests {
    @Autowired
    PatientDataRepository patientDataRepository;

    PatientData patientData1;
    PatientData patientData2;
    @BeforeAll
    public void setup() {
        patientData1 = PatientData.builder()
                .answer1(1).answer2(1).answer3(1)
                .answer4(1).answer5(1).answer6(1)
                .answer7(1).answer8(1).answer9(1)
                .answer10(1).answer11(1).answer12(1).build();
        patientData2 = PatientData.builder()
                .answer1(2).answer2(2).answer3(2)
                .answer4(2).answer5(2).answer6(2)
                .answer7(2).answer8(2).answer9(2)
                .answer10(2).answer11(2).answer12(2).build();
        patientDataRepository.save(patientData1);
    }

    @Test
    @Order(1)
    public void saveTest() {
        assertEquals(patientData2, patientDataRepository.save(patientData2));
    }

    @Test
    @Order(2)
    @Transactional
    public void getByIdTest()
    {
        System.out.println(patientDataRepository.findAll().size());
        assertEquals(patientData1.getAnswer1(), patientDataRepository.getById(1L).getAnswer1());
    }

    @Test
    @Order(3)
    public void findAllTest() {
        assertEquals(patientDataRepository.findAll().size(),2);
        assertEquals(patientData2.getAnswer1(), patientDataRepository.findAll().get(1).getAnswer1());
    }

    @Test
    @Order(4)
    @Transactional
    public void deleteByIdTest() {
        patientDataRepository.deleteById(1L);
        assertThrows(JpaObjectRetrievalFailureException.class, () -> patientDataRepository.getById(1L));
        assertEquals(patientDataRepository.findAll().size(),1);
    }
}
