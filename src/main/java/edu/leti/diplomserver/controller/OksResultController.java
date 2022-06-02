package edu.leti.diplomserver.controller;

import edu.leti.diplomserver.dto.OksResultDto;
import edu.leti.diplomserver.service.OksResultService;
import org.springframework.web.bind.annotation.*;

//Контроллер, обрабатывающий входящие REST API запросы, связанные с результатами OKS
@RestController
public class OksResultController {

    private final OksResultService oksResultService;
    //Constructor Dependency Injection
    public OksResultController(OksResultService oksResultService) {
        this.oksResultService = oksResultService;
    }

    //Сохранение результат заполнения анкеты OKS
    @PostMapping("/oksResult")
    public Long addOksResult(@RequestBody OksResultDto oksResultDto) {
        return oksResultService.addOksResult(oksResultDto);
    }
}
