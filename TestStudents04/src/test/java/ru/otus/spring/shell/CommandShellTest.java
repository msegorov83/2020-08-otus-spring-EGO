package ru.otus.spring.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.CommandNotCurrentlyAvailable;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.domain.Student;
import ru.otus.spring.service.ExamService;
import ru.otus.spring.service.QuestionService;
import ru.otus.spring.service.StudentServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Тест команд shell")

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
public class CommandShellTest {

    private static final String COMMAND_LOGIN = "login";
    private static final String COMMAND_START = "start";
    private static final String COMMAND_START_EXPECTED_RESULT = "started";
    private static final String COMMAND_LOGIN_EXPECTED_RESULT = "signedin";
    private static final String USER_NAME = "student";


    @ComponentScan("ru.otus.spring")
    @Configuration
    static class TestConfiguration {
    }

    @Mock
    private StudentServiceImpl StudentService;

    @Mock
    private ExamService examService;

    @Mock
    private QuestionService questionService;


    @Autowired
    private Shell shell;

    @DisplayName("Тест должен возвращать CommandNotCurrentlyAvailable если при попытке выполнения команды start пользователь не выполнил вход")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void testIsStartCommandAvailable() {
        Object res =  shell.evaluate(() -> COMMAND_START);
        assertThat(res).isInstanceOf(CommandNotCurrentlyAvailable.class);
    }


    @DisplayName("Тест должен выполнил вход под УЗ student")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void testLogonStudent() throws Exception {

        Student student = new Student("student", false);
        when(StudentService.logonStudent(USER_NAME)).thenReturn(student.getFullName());
        String resCommand = (String)shell.evaluate(() -> COMMAND_LOGIN);
        var res = StudentService.logonStudent(USER_NAME);
        verify(StudentService,times(1)).logonStudent(USER_NAME);
        assertEquals(student.getFullName(), res);
        assertThat(resCommand).isEqualTo(COMMAND_LOGIN_EXPECTED_RESULT);
    }


    @DisplayName("Тест должен выполнять старт тестирования")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void testStartExam() throws Exception {

        boolean ExamIstrue = true;
        when(examService.startExam(USER_NAME)).thenReturn(ExamIstrue);
        var res = examService.startExam(USER_NAME);
        verify(examService, times(1)).startExam(USER_NAME);
        assertEquals(ExamIstrue, res);

    }


}