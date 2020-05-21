package co.za.casestudy.mdb.bean;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

@Stateless
public class TestQuestionProducer {

    @Resource(mappedName = "java:/jboss/WMQConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "java:/queue/test")
    Queue queue;

    public void pushMessage() {
        try (JMSContext context = connectionFactory.createContext()) {
            //context.createProducer().send(queue, "Hello World !");
            StringBuilder message = new StringBuilder();
            message.append("Testing TestQuestion");
            context.createProducer().send(queue, message.toString());
        }
    }

}
