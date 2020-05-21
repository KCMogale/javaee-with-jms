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
    private Queue queue;

    public void pushMessage() {
        try (JMSContext context = connectionFactory.createContext()) {
            context.createProducer().send(queue, "Testing TestQuestion");
        }
    }

}
