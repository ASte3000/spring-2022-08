package com.stquiz.config;

import com.stquiz.dao.QuizDao;
import com.stquiz.dao.QuizDaoImpl;
import com.stquiz.io.IOService;
import com.stquiz.io.ConsoleIOServiceImpl;
import com.stquiz.service.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@SuppressWarnings("SpringFacetCodeInspection")
@Configuration
@PropertySource("classpath:quiz.properties")
public class ServicesConfig {

    @Bean
    public QuizService quizService(QuizElementsService quizElementsService, QuizAnswersTaker answersTaker,
                                   QuizAnswersChecker answersChecker)
    {
        return new QuizServiceImpl(quizElementsService, answersTaker, answersChecker);
    }

    @Bean
    public QuizDao quizDao(@Value("${data.resource.key}") String dataResourceKey) {
        return new QuizDaoImpl(dataResourceKey);
    }

    @Bean
    public QuizElementsService quizElementsPublisher(QuizDao dao) {
        return new QuizElementsServiceImpl(dao);
    }

    @Bean
    public QuizAnswersTaker quizAnswersTaker(IOService ioService) {
        return new QuizAnswersTakerImpl(ioService);
    }

    @Bean
    public QuizAnswersChecker quizAnswersChecker(IOService ioService, @Value("${min.pass.score}") int minPassScore) {
        return new QuizAnswersCheckerImpl(ioService, minPassScore);
    }

    @Bean
    public IOService quizIOService() {
        return new ConsoleIOServiceImpl();
    }

}
