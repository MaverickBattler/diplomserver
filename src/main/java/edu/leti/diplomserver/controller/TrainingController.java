package edu.leti.diplomserver.controller;

import edu.leti.diplomserver.dto.TrainingDto;
import edu.leti.diplomserver.service.TrainingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrainingController {

    private final TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @PostMapping("/training")
    public void addTraining(@RequestBody TrainingDto trainingDto) {
        trainingService.addTraining(trainingDto);
    }
}
