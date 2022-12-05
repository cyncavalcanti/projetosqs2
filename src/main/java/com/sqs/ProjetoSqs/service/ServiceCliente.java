package com.sqs.ProjetoSqs.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.QueueAttributeName;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SetQueueAttributesRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sqs.ProjetoSqs.Config.ConfigCliente;
import com.sqs.ProjetoSqs.Models.ClienteModel;
import org.springframework.stereotype.Service;


@Service
public class ServiceCliente {




        AmazonSQS sqsClient = new ConfigCliente().sqsClient();

        ClienteModel cliente = new ClienteModel();

        ObjectMapper mapper = new ObjectMapper();

        String queue = sqsClient.getQueueUrl("compra_cartao_credito").getQueueUrl();

        String json;

        private SetQueueAttributesRequest logRequest ;

        private SendMessageRequest sendMessageRequest = new SendMessageRequest();

        public ServiceCliente(){
            this.logRequest = new SetQueueAttributesRequest().withQueueUrl(queue)
                    .addAttributesEntry(QueueAttributeName.VisibilityTimeout.toString(), "5000");
            sqsClient.setQueueAttributes(logRequest);
        }

        public void SendMessage(){
            cliente.setNome("Teste José");
            cliente.setEmail("Jose@gmail.com");

            try {
                json = mapper.writeValueAsString(cliente);//mapear o valor da string cliente
                System.out.println("ResultingJSONstring = " + json);

            } catch (Exception e) {
                e.printStackTrace();
            }

            sendMessageRequest.withMessageBody(json);
            sendMessageRequest.withQueueUrl(queue);
            //sendMessageRequest.withMessageAttributes(messageAttributes);
            sqsClient.sendMessage(sendMessageRequest);
        }


    }
