package edu.leti.diplomserver.controller;

import edu.leti.diplomserver.dto.TrainingDto;
import edu.leti.diplomserver.dto.TrainingsRequestDto;
import edu.leti.diplomserver.service.TrainingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//Контроллер, обрабатывающий входящие REST API запросы, связанные с прохождением тренировок
@RestController
public class TrainingController {

    private final TrainingService trainingService;
    //Constructor Dependency Injection
    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }
    //Сохранение отметки о выполнении одного из упражнения
    @PostMapping("/training")
    public void addTraining(@RequestBody TrainingDto trainingDto) {
        trainingService.addTraining(trainingDto);
    }
    //Получение тренировок за последнюю неделю
    @PostMapping("/get-trainings")
    public List<List<Boolean>> getTrainings(@RequestBody TrainingsRequestDto trainingsRequestDto) {
        return trainingService.getTrainings(trainingsRequestDto);
    }
}
