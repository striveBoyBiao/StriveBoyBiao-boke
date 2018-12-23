package com.activemq.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class FilterSender {

    /**消息服务器的连接地址*/
    public static final String BROKER_URL="tcp://192.168.16.159:61616";
    
    public static void main(String[] args) {
        FilterSender filterSender=new FilterSender();
        filterSender.sendMessage("Hello world");
    }
    
    
    private void sendMessage(String message) {
        Connection connection=null;
        Session session=null;
        MessageProducer messageProducer=null;
        
        
          try {
              /*创建工厂连接*/
              ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
               /*创建连接*/
              connection=connectionFactory.createConnection();
              /*获取session*/
              session=connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
              /*5.创建一个目的地*/
               Destination destination = session.createQueue("queue");
               /*6.创建一个消息的生产者（发送者）*/
               MessageProducer createProducer = session.createProducer(destination);
               
               for(int i=0;i<=20;i++){
                   /*创建消息*/
                   Message textMessage = session.createTextMessage(message+i);   
                 //将消息设置一个特有的标识
                   textMessage.setIntProperty("id", i);
                   createProducer.send(textMessage);
               }
               
               
              
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            try { 
                  //关闭连接释放资源
                    if (null != messageProducer) {
                    messageProducer.close();
                    }
                    if (null != session) {
                    session.close();
                    }
                    if (null != connection) {
                    connection.close();
                    }
            } catch (JMSException e) {
                    e.printStackTrace();
            }
        }
      
        
    }

}
