package sf.demo.sampleApp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sf.demo.sampleApp.model.Dog;
import sf.demo.sampleApp.service.ActionService;

public class ActionServiceImpl implements ActionService {

    private Logger logger = LoggerFactory.getLogger(ActionServiceImpl.class);

    public ActionServiceImpl() { }

    @Override
    public String sayHiToHuman(String name) {
        return "Hi "+ name +" !";
    }

    @Override
    public String play() {
        return playWithBall() + " I'm done playing";
    }

    @Override
    public String clean() {
        logger.info("washing...");
        return "Your dog is clean";
    }

    private String playWithBall() {
        logger.info("Get in position to play");
        logger.info("Human Human Human, throw the ball");
        logger.info("Go get the ball");
        logger.info("Give it to Human");
        return "Thank you Human";
    }
}
