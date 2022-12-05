package com.sqs.ProjetoSqs.Controller;
import com.sqs.ProjetoSqs.Config.ConfigCliente;
import com.sqs.ProjetoSqs.service.ServiceCliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/teste-sqs"})
public class clienteController {


        private static final Logger LOG = LoggerFactory.getLogger(Controller.class);

        //Rota para testar se o projeto tá mandando mensagem
        @GetMapping("/TesteSend")
        public ResponseEntity<?> TesteSend() {

            LOG.info("Teste Rota Controler");

            try {
                ServiceCliente service = new ServiceCliente();
                service.SendMessage();
                LOG.info("Mensagem Após Publicação do SQS com Sucesso!!!");
            } catch (Exception e) {
                LOG.error("Falha ao publicar a mensagem no SQS!!! Erro: " + e);
            }

            return ResponseEntity.ok("TesteRotaOk");

        }
    }


