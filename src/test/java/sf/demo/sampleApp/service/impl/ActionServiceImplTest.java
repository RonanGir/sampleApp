package sf.demo.sampleApp.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import sf.demo.sampleApp.model.Dog;

import static org.junit.Assert.*;
import static org.mockito.Mockito.description;
import static org.mockito.Mockito.times;

//@RunWith(PowerMockRunner.class)
@PrepareForTest({Dog.class, ActionServiceImpl.class})
public class ActionServiceImplTest {

    @Spy
    @InjectMocks
    private ActionServiceImpl bfSvc;

    private ActionServiceImpl bfSvcTest = new ActionServiceImpl();

    @Before
    public void initialize() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void sayHiToHuman() {
        assertEquals("Hi HumanName !", bfSvcTest.sayHiToHuman("HumanName"));

        Mockito.when(bfSvc.sayHiToHuman(Mockito.anyString())).thenReturn("Hello Human");
        assertEquals("Hello Human", bfSvc.sayHiToHuman("HumanName"));

    }

    @Test
    public void play() throws Exception {
        assertEquals("Thank you Human I'm done playing", bfSvcTest.play());

        // Mock partiel
        ActionServiceImpl mockActions = PowerMockito.spy(bfSvcTest);
        PowerMockito.when(mockActions, "playWithBall").thenReturn("Go play video games Human");

        String playStory = mockActions.play();

        PowerMockito.verifyPrivate(mockActions).invoke("playWithBall");
        assertEquals("Go play video games Human I'm done playing", playStory);
    }

    @Test
    public void testPoop() {
        bfSvc.poop();
        Mockito.verify(bfSvc, times(1)).goToThePark();
        Mockito.verify(bfSvc, times(2)).findHidePlaces();
        Mockito.verify(bfSvc, times(1)).cleanAfterPoop();
    }

}