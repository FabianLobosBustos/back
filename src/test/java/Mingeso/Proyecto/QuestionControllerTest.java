package Mingeso.Proyecto;

import Mingeso.Proyecto.model.Question;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Question.class, loader = AnnotationConfigContextLoader.class)
@SpringBootTest
public class QuestionControllerTest {


    @Test
    public void newQuestionTest(){
        Question question = new Question();
        question.setCodeBody("123");

        Assert.assertNotNull(question);
    }

}
