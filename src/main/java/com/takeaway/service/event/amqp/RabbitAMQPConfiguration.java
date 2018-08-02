package com.takeaway.service.event.amqp;

import com.takeaway.service.event.amqp.message.EventMessageConverter;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class RabbitAMQPConfiguration {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public SimpleRabbitListenerContainerFactory eventConsumerFactory (
            SimpleRabbitListenerContainerFactoryConfigurer configurer) {
                SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
                configurer.configure(factory, connectionFactory);
                factory.setMessageConverter(customMessageConverter());
            return factory;
    }

    @Bean
    public MessageConverter customMessageConverter()
    {
        return new EventMessageConverter();
    }
}
