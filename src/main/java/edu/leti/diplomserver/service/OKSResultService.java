package edu.leti.diplomserver.service;

import edu.leti.diplomserver.domain.OKSResult;
import edu.leti.diplomserver.repository.OKSResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OKSResultService {

    private final OKSResultRepository oksResultRepository;
    //Constructor Dependency Injection
    public OKSResultService(OKSResultRepository oksResultRepository) {
        this.oksResultRepository = oksResultRepository;
    }

    public OKSResult addOKSResult(OKSResult oksResult) {
        return oksResultRepository.save(oksResult);
    }

    public OKSResult getOKSResult(Long oksResultId) {
        return oksResultRepository.getById(oksResultId);
    }

    public List<OKSResult> getAllOKSResults() {
        return oksResultRepository.findAll();
    }

    public void removeOKSResult(Long oksResultId) {
        oksResultRepository.deleteById(oksResultId);
    }
}
