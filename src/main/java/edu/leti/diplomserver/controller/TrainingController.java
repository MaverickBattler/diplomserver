package edu.leti.diplomserver.controller;

import edu.leti.diplomserver.dto.TrainingDto;
import edu.leti.diplomserver.dto.TrainingsRequestDto;
import edu.leti.diplomserver.service.TrainingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @PostMapping("/get-trainings")
    public List<List<Boolean>> getWeekTrainings(@RequestBody TrainingsRequestDto trainingsRequestDto) {
        return trainingService.getTrainings(trainingsRequestDto);
    }
}
