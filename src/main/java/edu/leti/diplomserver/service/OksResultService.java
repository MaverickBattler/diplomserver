package edu.leti.diplomserver.service;

import edu.leti.diplomserver.domain.OksResult;
import edu.leti.diplomserver.domain.User;
import edu.leti.diplomserver.dto.OksResultDto;
import edu.leti.diplomserver.repository.OksResultRepository;
import edu.leti.diplomserver.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OksResultService {

    private final OksResultRepository oksResultRepository;

    private final UserRepository userRepository;

    //Constructor Dependency Injection
    public OksResultService(OksResultRepository oksResultRepository,
                            UserRepository userRepository) {
        this.oksResultRepository = oksResultRepository;
        this.userRepository = userRepository;
    }

    public OksResult addOksResult(OksResultDto oksResultDto) {
        User user = userRepository.findByEmail(oksResultDto.getEmail());
        OksResult oksResult = OksResult.builder()
                .user(user)
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
        return oksResultRepository.save(oksResult);
    }

    public OksResult getOksResult(Long oksResultId) {
        return oksResultRepository.getById(oksResultId);
    }

    public List<OksResult> getAllOksResults() {
        return oksResultRepository.findAll();
    }

    public void removeOksResult(Long oksResultId) {
        oksResultRepository.deleteById(oksResultId);
    }
}
