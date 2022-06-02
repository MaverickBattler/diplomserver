package edu.leti.diplomserver.service;

import edu.leti.diplomserver.domain.Training;
import edu.leti.diplomserver.domain.User;
import edu.leti.diplomserver.dto.TrainingDto;
import edu.leti.diplomserver.dto.TrainingsRequestDto;
import edu.leti.diplomserver.repository.TrainingRepository;
import edu.leti.diplomserver.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.util.Calendar.getInstance;

//Сервис, в котором представлены методы, связанные с выполнением тренировок
@Service
public class TrainingService {

    private static final long DAY_IN_MILLIS = 86400000L;

    private final TrainingRepository trainingRepository;

    private final UserRepository userRepository;

    //Constructor Dependency Injection
    public TrainingService(TrainingRepository trainingRepository,
                           UserRepository userRepository) {
        this.trainingRepository = trainingRepository;
        this.userRepository = userRepository;
    }

    //Сохранение отметки о выполнении одного из упражнений в trainingRepository
    public void addTraining(TrainingDto trainingDto) {

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
            //Получаем время создания этой записи
            long lastTrainingStart = lastTraining.getStarted().getTime();
            //Имеется время последнего прохождения анкеты OKS lastOks
            Long lastOks = trainingDto.getLastOks();
            long dayStart;
            long dayEnd;
            Calendar calendar = getInstance();
            //Получаем текущее время
            long currentTime = calendar.getTimeInMillis();
            for (int day = 0; day < 7; day++) {
                dayStart = lastOks + day * DAY_IN_MILLIS;
                dayEnd = dayStart + DAY_IN_MILLIS;
                //Смотрим, в какой день после lastOks последняя запись попадает
                //Если текущее время попадает в тот же день, значит запись с сегодняшними
                if ((lastTrainingStart >= dayStart && lastTrainingStart < dayEnd)
                        && (currentTime >= dayStart && currentTime < dayEnd)) {
                    toUpdate = true;
                    break;
                }
            }
        }
        //Если запись идет в уже существующую запись
        if (toUpdate) {//берется уже имеющаяся запись
            trainingToSave = lastTraining;
        } else {//иначе создается новая запись
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
        } //Устанавливаем отметку о выполнении нужной тренировки
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
        }//сохранение в trainingRepository
        trainingRepository.save(trainingToSave);
    }

    //Получение списка выполнения тренировок в течение недели после последнего заполнения OKS
    public List<List<Boolean>> getTrainings(TrainingsRequestDto trainingsRequestDto) {
        List<List<Boolean>> trainings = new ArrayList<>();
        Long lastOks = trainingsRequestDto.getLastOks();
        User user = userRepository.findByEmail(trainingsRequestDto.getEmail());
        List<Training> userTrainings = trainingRepository.findAllByUser(user);
        Calendar calendarOks = Calendar.getInstance();
        calendarOks.setTimeInMillis(lastOks);
        if (lastOks != 0) { //Если пользователь проходил OKS раньше, то есть должен был выполнять тренировки
            if (!userTrainings.isEmpty()) { //Если он тренировался когда-то
                boolean foundTrainingForTheDay; //Нашли ли тренировку для одного из 7 дней
                for (int day = 0; day < 7; day++) { //0 to 6
                    foundTrainingForTheDay = false;
                    long dayStart = lastOks + day * DAY_IN_MILLIS;
                    long dayEnd = dayStart + DAY_IN_MILLIS;
                    //Отметки о выполнении упражнений в текущий день
                    List<Boolean> trainingDay = new ArrayList<>();

                    for (Training training : userTrainings) { //От 0 до n (количество тренировок пользователя)
                        if (!foundTrainingForTheDay) {
                            //Если тренировка выполнена в текущий день
                            if (training.getStarted().getTime() >= dayStart
                                    && training.getStarted().getTime() < dayEnd) {
                                System.out.println("Добавляем тренировку с id " + training.getId());
                                trainingDay.add(training.getExercise1());
                                trainingDay.add(training.getExercise2());
                                trainingDay.add(training.getExercise3());
                                trainingDay.add(training.getExercise4());
                                trainingDay.add(training.getExercise5());
                                trainingDay.add(training.getExercise6());
                                trainingDay.add(training.getExercise7());
                                trainingDay.add(training.getExercise8());
                                trainingDay.add(training.getExercise9());
                                trainingDay.add(training.getExercise10());
                                trainingDay.add(training.getExercise11());
                                trainingDay.add(training.getExercise12());
                                trainingDay.add(training.getExercise13());

                                trainings.add(trainingDay);
                                foundTrainingForTheDay = true;
                            }
                        }

                    }
                    //Если он не тренировался в этот день, то создается запись со всеми невыполненными упражнениями
                    if (!foundTrainingForTheDay) {
                        for (int i = 0; i < 13; i++) {
                            trainingDay.add(false);
                        }
                        trainings.add(trainingDay);
                    }
                }
            } else {//Если пользователь не тренировался ни разу
                //Весь двумерный массив заполняется false
                for (int day = 0; day < 7; day++) { //0 to 6
                    ArrayList<Boolean> trainingDay = new ArrayList<>();
                    for (int i = 0; i < 13; i++) {
                        trainingDay.add(false);
                    }
                    trainings.add(trainingDay);
                }
            }
            //Возвращается двумерный массив с отметками о тренировках за неделю
            return trainings;
            //Иначе возвращается пустой массив
        } else return new ArrayList<>();
    }
}
