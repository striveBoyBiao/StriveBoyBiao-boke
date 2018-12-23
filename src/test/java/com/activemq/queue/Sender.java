package com.activemq.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 生产者即消息发送者 
 *
 *Sender.java 文件使用说明
 * 说明：<br/>
 *  公开方法：
 * <ul>
 * <li>XXXX：</li>
 * </ul>
 *
 * @version ver 4.0.0
 * @author Shanghai Kinstar Winning Software .co.ltd. hebiao
 * @since 作成日期：2018年7月12日（hebiao）<br/>
 *        改修日期：
 */
public class Sender {

    /**消息服务器的连接地址*/
    public static final String BROKER_URL="tcp://192.168.16.159:61616";
    
    public static void main(String[] args) {
      Sender sender = new Sender();
       sender.sendMessage("Hello ActiveMQ.");
    }
    
    /**
     * 
     * <p> 发送消息</p>
     * @param msg
     * @author: hebiao
     */
    public void sendMessage(String msg){
        Connection connection=null;
        Session session=null;
        MessageProducer messageProducer=null;
        
        
        try {
            /*1.创建一个连接工厂*/
            ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(BROKER_URL);
            
            /*2.创建一个连接*/
            connection=connectionFactory.createConnection();
            
            /*3.创建一个Session*/
            session=connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            
            /*4.创建消息，此处创建了一个文本消息*/
            Message message = session.createTextMessage(msg);
            
            /*5.创建一个目的地*/
            Destination destination = session.createQueue("myQueue");
            
            /*6.创建一个消息的生产者（发送者）*/
            messageProducer=session.createProducer(destination);
            
            messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);//持久化
            
            /*7.发送消息*/
            messageProducer.send(message);
            
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            try {
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
