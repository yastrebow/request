package ru.yastrebov.request.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yastrebov.request.kafka.KafkaProducer;
import ru.yastrebov.request.mapstruct.RequestMapper;
import ru.yastrebov.request.model.RequestDTO;
import ru.yastrebov.request.service.RequestService;
import ru.yastrebov.requestAnalyzerLib.model.Request;

@Service
@RequiredArgsConstructor
@Slf4j
public class RequestServiceImpl implements RequestService {

    private final KafkaProducer kafkaProducer;

    private final RequestMapper requestMapper;

    @Override
    public RequestDTO postRequest(RequestDTO requestDTO) {
        log.info("postRequest - start, requestDTO = {}", requestDTO);
        Long uniqueId = System.currentTimeMillis();
        requestDTO.setId(uniqueId);
        final Request message = kafkaProducer.sendMessage(requestMapper.dtoToRequest(requestDTO));
        log.info("postRequest - end, requestDTO = {}", requestDTO);

        return requestMapper.requestToDto(message);
    }

}
