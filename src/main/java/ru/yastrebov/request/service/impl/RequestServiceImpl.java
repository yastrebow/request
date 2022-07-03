package ru.yastrebov.request.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yastrebov.request.kafka.KafkaProducer;
import ru.yastrebov.request.model.RequestDTO;
import ru.yastrebov.request.service.RequestService;

@Service
@RequiredArgsConstructor
@Slf4j
public class RequestServiceImpl implements RequestService {

    private final KafkaProducer kafkaProducer;

    @Override
    public RequestDTO postRequest(RequestDTO requestDTO) {
        log.info("postRequest - start, requestDTO = {}", requestDTO);
        Long uniqueId = System.currentTimeMillis();
        requestDTO.setId(uniqueId);
        final RequestDTO message = kafkaProducer.sendMessage(kafkaProducer.createMessageForSending(requestDTO));
        log.info("postRequest - end, requestDTO = {}", requestDTO);
        return message;
    }
}
