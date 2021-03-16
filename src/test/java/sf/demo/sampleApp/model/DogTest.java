package sf.demo.sampleApp.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "sf.demo.sampleApp.model.*")
public class DogTest {

    @Test
    public void instantiateDogWithArgument() throws Exception {
        Dog mockDog = PowerMockito.mock(Dog.class);
        PowerMockito.whenNew(Dog.class).withAnyArguments().thenReturn(mockDog);

        Dog doug = new Dog("doug");
        PowerMockito.verifyNew(Dog.class).withArguments("doug");
    }

    @Test
    public void instantiateDogNoArgument() throws Exception {
        Dog mockDog = PowerMockito.mock(Dog.class);
        PowerMockito.whenNew(Dog.class).withAnyArguments().thenReturn(mockDog);

        Dog doug = new Dog();
        PowerMockito.verifyNew(Dog.class).withNoArguments();
    }

    @Test
    public void bark() {
        Dog mockDog = PowerMockito.mock(Dog.class);

        Dog paf = new Dog();
        assertEquals("Waf waf",paf.bark());

        PowerMockito.when(mockDog.bark()).thenReturn("Woof Woof");
        assertEquals("Woof Woof",mockDog.bark());
    }

    @Test
    public void barkLikeACat() {
        assertEquals("Meow Meow", Dog.barkLikeACat());

        PowerMockito.mockStatic(Dog.class);
        PowerMockito.when(Dog.barkLikeACat()).thenReturn("Miaou Miaou");

        assertEquals("Miaou Miaou", Dog.barkLikeACat());
    }
}