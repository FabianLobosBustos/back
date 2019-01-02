package Mingeso.Proyecto;

import Mingeso.Proyecto.model.Quiz;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = QuizControllerTest.class, loader = AnnotationConfigContextLoader.class)
@SpringBootTest
public class QuizControllerTest {


    /*@Test
    public void newQuizTest(){
        Quiz quiz = new Quiz();
        quiz.setName("quiz 1");
        quiz.setSection("A-1");
        Assert.assertNotNull(quiz);
    }*/
}
