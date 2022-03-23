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
public class OKSResultRepositoryTests {
    @Autowired
    OKSResultRepository oksResultRepository;

    OKSResult oksResult1;
    OKSResult oksResult2;
    @BeforeAll
    public void setup() {
        oksResult1 = OKSResult.builder()
                .answer1(1).answer2(1).answer3(1)
                .answer4(1).answer5(1).answer6(1)
                .answer7(1).answer8(1).answer9(1)
                .answer10(1).answer11(1).answer12(1).build();
        oksResult2 = OKSResult.builder()
                .answer1(2).answer2(2).answer3(2)
                .answer4(2).answer5(2).answer6(2)
                .answer7(2).answer8(2).answer9(2)
                .answer10(2).answer11(2).answer12(2).build();
        oksResultRepository.save(oksResult1);
    }

    @Test
    @Order(1)
    public void saveTest() {
        assertEquals(oksResult2, oksResultRepository.save(oksResult2));
    }

    @Test
    @Order(2)
    @Transactional
    public void getByIdTest()
    {
        System.out.println(oksResultRepository.findAll().size());
        assertEquals(oksResult1.getAnswer1(), oksResultRepository.getById(1L).getAnswer1());
    }

    @Test
    @Order(3)
    public void findAllTest() {
        assertEquals(oksResultRepository.findAll().size(),2);
        assertEquals(oksResult2.getAnswer1(), oksResultRepository.findAll().get(1).getAnswer1());
    }

    @Test
    @Order(4)
    @Transactional
    public void deleteByIdTest() {
        oksResultRepository.deleteById(1L);
        assertThrows(JpaObjectRetrievalFailureException.class, () -> oksResultRepository.getById(1L));
        assertEquals(oksResultRepository.findAll().size(),1);
    }
}