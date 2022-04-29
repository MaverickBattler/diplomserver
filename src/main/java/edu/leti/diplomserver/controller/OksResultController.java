package edu.leti.diplomserver.controller;

import edu.leti.diplomserver.dto.OksResultDto;
import edu.leti.diplomserver.service.OksResultService;
import org.springframework.web.bind.annotation.*;

@RestController
public class OksResultController {

    private final OksResultService oksResultService;

    public OksResultController(OksResultService oksResultService) {
        this.oksResultService = oksResultService;
    }

    @PostMapping("/oksResult")
    public Long addOksResult(@RequestBody OksResultDto oksResultDto) {
        return oksResultService.addOksResult(oksResultDto);
    }
}
