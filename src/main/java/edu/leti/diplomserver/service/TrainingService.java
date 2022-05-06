package edu.leti.diplomserver.service;

import edu.leti.diplomserver.domain.Training;
import edu.leti.diplomserver.domain.User;
import edu.leti.diplomserver.dto.TrainingDto;
import edu.leti.diplomserver.repository.TrainingRepository;
import edu.leti.diplomserver.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class TrainingService {
    private final TrainingRepository trainingRepository;

    private final UserRepository userRepository;

    //Constructor Dependency Injection
    public TrainingService(TrainingRepository trainingRepository,
                           UserRepository userRepository) {
        this.trainingRepository = trainingRepository;
        this.userRepository = userRepository;
    }

    public void addTraining(TrainingDto trainingDto) {
        //Имеется дата lastOks
        //Находим последнюю запись с тренировками для данного пользователя
        //Смотрим на дату создания этой записи
        //Получаем текущую дату
        //Смотрим, в какой день после lastOks она попадает
        //Получаем текущее время
        //Если текущее время попадает в тот же день, значит запись с сегодняшними
        //Тренировками уже существует и необходимо добавить в нее
        //Иначе необходимо создать новую запись
        User user = userRepository.findByEmail(trainingDto.getEmail());
        List<Training> userTrainings = trainingRepository.findAllByUser(user);
        Training lastTraining = null;
        Training trainingToSave;
        boolean toUpdate = false;
        if (!userTrainings.isEmpty()) { // не первая тренировка пользователя
            lastTraining = userTrainings.get(0); // нахождение последней тренировки
            for (Training training : userTrainings) {
                if (training.getStarted().getTime() > lastTraining.getStarted().getTime()) {
                    lastTraining = training;
                }
            }
            long lastTrainingStart = lastTraining.getStarted().getTime();
            Long lastOks = trainingDto.getLastOks();
            long dayStart;
            long dayEnd;
            Calendar calendar = Calendar.getInstance();
            long currentTime = calendar.getTimeInMillis();
            for (int day = 0; day < 7; day++) {
                dayStart = lastOks + day * /*86400000*/60000;
                dayEnd = dayStart + /*86400000*/60000;
                if ((lastTrainingStart >= dayStart && lastTrainingStart < dayEnd)
                        && (currentTime >= dayStart && currentTime < dayEnd)) {
                    toUpdate = true;
                    break;
                }
            }
        }

        if (toUpdate) {
            trainingToSave = lastTraining;
        } else {
            trainingToSave = Training.builder()
                    .user(user)
                    .exercise1(false)
                    .exercise2(false)
                    .exercise3(false)
                    .exercise4(false)
                    .exercise5(false)
                    .exercise6(false)
                    .exercise7(false)
                    .exercise8(false)
                    .exercise9(false)
                    .exercise10(false)
                    .exercise11(false)
                    .exercise12(false)
                    .exercise13(false).build();
        }
        switch (trainingDto.getExerciseNumber()) {
            case 0:
                trainingToSave.setExercise1(true);
                break;
            case 1:
                trainingToSave.setExercise2(true);
                break;
            case 2:
                trainingToSave.setExercise3(true);
                break;
            case 3:
                trainingToSave.setExercise4(true);
                break;
            case 4:
                trainingToSave.setExercise5(true);
                break;
            case 5:
                trainingToSave.setExercise6(true);
                break;
            case 6:
                trainingToSave.setExercise7(true);
                break;
            case 7:
                trainingToSave.setExercise8(true);
                break;
            case 8:
                trainingToSave.setExercise9(true);
                break;
            case 9:
                trainingToSave.setExercise10(true);
                break;
            case 10:
                trainingToSave.setExercise11(true);
                break;
            case 11:
                trainingToSave.setExercise12(true);
                break;
            case 12:
                trainingToSave.setExercise13(true);
                break;
        }
        System.out.println("Saving " + trainingToSave.getExercise1() +
                trainingToSave.getExercise2() +trainingToSave.getExercise3() +
                trainingToSave.getExercise4() +trainingToSave.getExercise5() +
                trainingToSave.getExercise6() +trainingToSave.getExercise7() +
                trainingToSave.getExercise8() +trainingToSave.getExercise9() +
                trainingToSave.getExercise10() +trainingToSave.getExercise11() +
                trainingToSave.getExercise12() +trainingToSave.getExercise13());
        trainingRepository.save(trainingToSave);
    }
}
