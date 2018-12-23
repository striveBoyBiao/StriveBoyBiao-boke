package com.activemq.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class FilterReceiver {

    /**消息服务器的连接地址*/
    public static final String BROKER_URL="tcp://192.168.16.159:61616";
    
    public static void main(String[] args) {
        FilterReceiver filterReceiver=new FilterReceiver();
        filterReceiver.receiveMessage();
    }
    
    private void  receiveMessage () {
        Connection connection=null;
        Session session=null;
        MessageConsumer messageConsumer=null;
        
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
            connection=connectionFactory.createConnection();
            session=connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("queue");
            
            String messageSelector="id >=10 and id<=15";
            MessageConsumer createConsumer = session.createConsumer(destination, messageSelector);
          //接收消息之前，需要把连接启动一下 
            connection.start();
            
            while(true){
              //6.接收消息 同步接收 
                Message message = createConsumer.receive();
                
                if(message instanceof TextMessage){
                    String string=((TextMessage)message).getText();
                    System.out.println("接收到的消息内容是:"+string);
                }
            }
            
            
            
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
                try {
                    if (messageConsumer!=null) {
                        messageConsumer.close();
                    }
                    if (session!=null) {
                        session.close();
                    }
                    if (connection!=null) {
                        connection.close();
                    }
                    
                } catch (JMSException e) {
                    e.printStackTrace();
                }
         
        }
        
    }
}
