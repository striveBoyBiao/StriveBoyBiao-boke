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

/**
 * 接收者
 *
 *Receiver.java 文件使用说明
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
public class Receiver {
    /**消息服务器的连接地址**/ 
    public static final String BROKER_URL="tcp://192.168.16.159:61616";
    
    public static void main(String[] args){
        Receiver receiver = new Receiver();
        receiver.receiveMessage();    
    }
    
    /**
     * 接收消息
     * <p> 方法的详细说明第一行</p>
     * @author: hebiao
     */
    public void receiveMessage(){
        Connection connection=null;
        Session session=null;
        MessageConsumer messageConsumer=null;
        
        try {
            /*1.创建一个连接工厂*/
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
            /*2.创建一个连接 */
            connection=connectionFactory.createConnection();
            /*3.创建一个Session*/
            session=connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
            /*4.创建一个目的地 */
            Destination destination=session.createQueue("myQueue");
            /*5.创建一个消息的消费者（接收者） */
            messageConsumer=session.createConsumer(destination);
            /*接收消息之前，需要把连接启动一下 */
            connection.start();
            /*6.接收消息 */
            Message message=messageConsumer.receive();
            
            if (message instanceof TextMessage) {
                String text=((TextMessage)message).getText();
                System.out.println("接收到的消息内容是："+text);
            }
            
            
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            try {
              //关闭连接释放资源
              if (null != messageConsumer) {
                  messageConsumer.close();
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
