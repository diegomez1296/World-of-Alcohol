package dreamteam;

import org.junit.Assert;
import org.junit.Test;

public class ExampleTests {

    public ExampleTests() {
    }

    @Test
    public void shoudReturnString() {

        //Given
        ExampleClass exampleClass = new ExampleClass();

        //Then when
        Assert.assertEquals("Hello Example Method!", exampleClass.exampleMethod(1));
    }
}
