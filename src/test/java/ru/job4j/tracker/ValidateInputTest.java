package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ValidateInputTest {

    @Test
    public void whenInvalidInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"one", "1"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(1));
    }

    @Test
    public void whenInvalidInputMinusNumber() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"-1"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(-1));
    }

    @Test
    public void whenInvalidInputCorrectInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(0));
    }

    @Test
    public void whenInvalidInput2CorrectInput() {
        Output out = new StubOutput();
        String[] answer = new String[] {"0", "3", "2"};
        Input in = new StubInput(
                answer
        );
        ValidateInput input = new ValidateInput(out, in);
        int[] selected = new int[answer.length];
        selected[0] = input.askInt("Enter menu:");
        selected[1] = input.askInt("Enter menu:");
        selected[2] = input.askInt("Enter menu:");
        int[] expected = new int[]{0, 3, 2};
        assertThat(selected, is(expected));
    }
}