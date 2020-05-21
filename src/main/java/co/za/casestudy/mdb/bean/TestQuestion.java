package co.za.casestudy.mdb.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(name = "testMDB", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/test") })
public class TestQuestion implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(TestQuestion.class);

    @Override
    public void onMessage(Message message) {
        try {
            if ((message instanceof TextMessage) && (message.getJMSReplyTo() != null)) {
                logger.info("onMessage: received from the queue : {}", ((TextMessage) message).getText());
                for (int i = 0; i < 1000; i++) {
                    doReallyComplexProcess(i);
                }
            } else {
                logger.warn("onMessage: message of wrong type : {}", message.getClass().getName());
            }
        } catch (JMSException e) {
            logger.info("Error occurred while consuming message: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void doReallyComplexProcess(int i){
        try {
            logger.info("doReallyComplexProcess 'i' value : {}", i);
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
