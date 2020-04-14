package spring.boot.book.microservices.configuration;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RabbitMQConfiguration {

    /*
        CachingConnectionFactory cf = new CachingConnectionFactory();
        cf.setAddresses("");
        cf.setUsername("ycpazfqr:ycpazfqr");
        cf.setPassword("");
        cf.setVirtualHost("");

        Hosts	    salamander.rmq.cloudamqp.com (Load balanced)
                    salamander-01.rmq.cloudamqp.com
        Hostname	salamander.rmq.cloudamqp.com
        Port	    1883 (8883 for TLS)
        Username	ycpazfqr:ycpazfqr
        Password	C0yp_LtVajKbRbvCmDbTb0kXTLosaH2n

        amqp://ycpazfqr:C0yp_LtVajKbRbvCmDbTb0kXTLosaH2n@salamander.rmq.cloudamqp.com/ycpazfqr
    */

    /*
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {

        CachingConnectionFactory cf = new CachingConnectionFactory();

        //amqp://takjrmwu:kALVuZ3S25lP7Y9FqKTvPb8ahPCYrG-I@baboon.rmq.cloudamqp.com/takjrmwu
        log.info("Caching --------------------------------------------------------");
//        cf.setHost("salamander-01.rmq.cloudamqp.com");
        cf.setAddresses("salamander-01.rmq.cloudamqp.com");
        cf.setVirtualHost("ycpazfqr");
        cf.setUsername("ycpazfqr");
        cf.setPassword("C0yp_LtVajKbRbvCmDbTb0kXTLosaH2n");
//        cf.setRequestedHeartBeat(30);
//        cf.setConnectionTimeout(30);
//        Connection connection = cf.createConnection();
//        Channel channel = connection.createChannel(true);

        log.info("host:"  + connectionFactory.getHost());
        log.info("vhost:" + connectionFactory.getVirtualHost());
        log.info("port:"  + connectionFactory.getPort());
        log.info("user:"  + connectionFactory.getUsername());

//        final RabbitTemplate rabbitTemplate = new RabbitTemplate(channel);
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(cf);

        log.info("Template --------------------------------------------------------");
        log.info("host:"  + rabbitTemplate.getConnectionFactory().getHost());
        log.info("vhost:" + rabbitTemplate.getConnectionFactory().getVirtualHost());
        log.info("port:"  + rabbitTemplate.getConnectionFactory().getPort());
        log.info("user:"  + rabbitTemplate.getConnectionFactory().getUsername());

        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());

        return rabbitTemplate;
    }
*/

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {

        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        log.info("Template --------------------------------------------------------");
        log.info("host:"  + rabbitTemplate.getConnectionFactory().getHost());
        log.info("vhost:" + rabbitTemplate.getConnectionFactory().getVirtualHost());
        log.info("port:"  + rabbitTemplate.getConnectionFactory().getPort());
        log.info("user:"  + rabbitTemplate.getConnectionFactory().getUsername());

        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());

        return rabbitTemplate;
    }

    @Bean
    public TopicExchange multiplicationExchange(@Value("${multiplication.exchange}") final String exchangeName) {

        return new TopicExchange(exchangeName);
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {

        return new Jackson2JsonMessageConverter();
    }
}
