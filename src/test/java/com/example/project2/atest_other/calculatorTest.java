package com.example.project2.atest_other;

import com.example.project2.a_other.Calculator;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class calculatorTest {
    private Calculator calculator = new Calculator();

    @Test
    public void addTest(){
        int actual = calculator.add(1,2);
        int expected = 3;

        assertThat(actual, is(expected));
    }

    @Test
    public void multipleTest(){
        int actual = calculator.multi(1,2);
        int expected = 2;

        assertThat(actual, is(expected));
    }
}
