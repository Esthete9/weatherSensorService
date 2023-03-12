package com.example.ProjectThreeRestApi.util;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ErrorsUtilTest {

    private ErrorsUtil errorsUtil;

    @MockBean
    private BindingResult bindingResult;

    @Test
    public void returnErrorsToClient_shouldTrowException() {
        Mockito.doReturn(true).when(bindingResult).hasErrors();
        assertThrows(NotCreatedException.class,
                () -> {ErrorsUtil.returnErrorsToClient(bindingResult);});
    }
}
