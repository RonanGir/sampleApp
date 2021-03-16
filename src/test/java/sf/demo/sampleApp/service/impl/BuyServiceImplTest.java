package sf.demo.sampleApp.service.impl;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import sf.demo.sampleApp.exception.NoMoneyException;
import sf.demo.sampleApp.model.Dog;
import sf.demo.sampleApp.util.PaymentUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "sf.demo.sampleApp.util.*")
public class BuyServiceImplTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Mock
    ActionServiceImpl actionService;

    @InjectMocks
    BuyServiceImpl buyService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void buyACleanDog() {
        Integer priceInEuro = 150;
        String name = "Snoopy";

        Dog boughtDog = buyService.buyADog(priceInEuro, name);

        assertEquals(name, boughtDog.getName());

    }

    @Test
    public void buyADirtyDog() {
        Integer priceInEuro = 70;
        String name = "Dirty Snoopy";

        Mockito.when(actionService.clean()).thenCallRealMethod();
        Dog boughtDog = buyService.buyADog(priceInEuro, name);

        assertEquals(name, boughtDog.getName());

    }

    @Test
    public void buyACleanDogWhenYouKnowTheSeller() {
        Integer priceInEuro = 70;
        String name = "Clean Snoopy";

        PowerMockito.mockStatic(PaymentUtil.class);
        PowerMockito.when(PaymentUtil.priceIsPaid(Mockito.anyInt())).thenReturn(true);
        PowerMockito.when(PaymentUtil.paidForCleaning(Mockito.anyInt())).thenReturn(true);
        Mockito.when(actionService.clean()).thenReturn("Clean Dog");

        Dog boughtDog = buyService.buyADog(priceInEuro, name);

        assertEquals(name, boughtDog.getName());

    }

    @Test
    public void buyACleanDogWhenTheSellerHateYou() {
        Integer priceInEuro = 150;
        String name = "Dirty Snoopy";

        PowerMockito.mockStatic(PaymentUtil.class);
        PowerMockito.when(PaymentUtil.priceIsPaid(Mockito.anyInt())).thenCallRealMethod();
        PowerMockito.when(PaymentUtil.paidForCleaning(Mockito.anyInt())).thenReturn(false);

        Dog boughtDog = buyService.buyADog(priceInEuro, name);

        assertEquals(name, boughtDog.getName());

    }

    @Test
    public void buyACleanDogWithNotEnoughMoney() {
        Integer priceInEuro = 0;
        String name = "Snoopy";

        PowerMockito.mockStatic(PaymentUtil.class);
        PowerMockito.when(PaymentUtil.priceIsPaid(Mockito.anyInt())).thenCallRealMethod();
        PowerMockito.when(PaymentUtil.paidForCleaning(Mockito.anyInt())).thenCallRealMethod();
        Mockito.when(actionService.clean()).thenReturn("Dirty Dog");

        Dog boughtDog = buyService.buyADog(priceInEuro, name);

        assertNull(boughtDog);

    }

    @Test
    public void buyACleanDogWithForFree() {
        Integer priceInEuro = -1;
        String name = "Snoopy";

        exceptionRule.expect(NoMoneyException.class);
        exceptionRule.expectMessage("No money! no doggy!");

        buyService.buyADog(priceInEuro, name);

    }

}