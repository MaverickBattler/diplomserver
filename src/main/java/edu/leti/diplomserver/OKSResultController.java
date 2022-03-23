package edu.leti.diplomserver;

import org.springframework.web.bind.annotation.*;

@RestController
public class OKSResultController {

    private final OKSResultService oksResultService;
    //Constructor Dependency Injection
    public OKSResultController(OKSResultService oksResultService) {
        this.oksResultService = oksResultService;
    }

    @PostMapping("/oksResult")
    public void addOKSResult(@RequestBody OKSResult oksResult) {
        oksResultService.addOKSResult(oksResult);
    }

    @DeleteMapping("/oksResult/{id}")
    public void deleteOKSResult(@PathVariable(name = "id") long oksResultId) {
        oksResultService.removeOKSResult(oksResultId);
    }
}
