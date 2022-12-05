package com.sqs.ProjetoSqs.Config;

import com.amazonaws.services.sqs.AmazonSQS;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

@Configuration
public class ConfigCliente {

        private final AmazonSQS sqsClient;
        private static final Logger LOG = LoggerFactory.getLogger(ConfigCliente.class);
        //Definição do Client do SQS
        public ConfigCliente(){
            this.sqsClient = AmazonSQSClientBuilder
                    .standard()
                    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:9324", Regions.SA_EAST_1.US_GOV_EAST_1.getName()))
                    .withCredentials(new DefaultAWSCredentialsProviderChain())
                    .build();

            LOG.info("Client Criado!!!");

        }

        @Bean
        public AmazonSQS sqsClient(){
            return this.sqsClient;
        }
}
