package sf.demo.sampleApp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import sf.demo.sampleApp.exception.NoMoneyException;
import sf.demo.sampleApp.model.Dog;
import sf.demo.sampleApp.service.ActionService;
import sf.demo.sampleApp.service.BuyService;
import sf.demo.sampleApp.util.PaymentUtil;

public class BuyServiceImpl implements BuyService {

    private Logger logger = LoggerFactory.getLogger(ActionServiceImpl.class);

    @Autowired
    ActionService actionSvc;

    @Override
    public Dog buyADog(Integer price, String name) {

        if (price == null || price < 0) {
            throw new NoMoneyException("No money! no doggy!");
        }

        if (name == null || name.isEmpty()) {
            name = "DogWithNoName";
        }

        Dog yourDog = null;
        if (PaymentUtil.priceIsPaid(price)) {
            logger.info("Thanks for the money");
            logger.info("We are preparing your little {}", name);

            if (PaymentUtil.paidForCleaning(price)) {
                logger.info(actionSvc.clean());
            }

            if (!PaymentUtil.paidForCleaning(price)) {
                logger.info("Your dog is dirty but cool either");
            }

            yourDog = new Dog(name);
        } else {
            logger.info("No money! no {}!", name);
        }

        return yourDog;
    }

}
