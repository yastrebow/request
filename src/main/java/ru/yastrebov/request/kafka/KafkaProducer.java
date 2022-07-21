package ru.yastrebov.request.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yastrebov.requestAnalyzerLib.model.Request;

import javax.validation.constraints.NotNull;

@Component
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, Request> kafkaTemplate;

    public Request sendMessage(@RequestBody Request message) {

        ListenableFuture<SendResult<String, Request>> future = kafkaTemplate.send("requests_for_loan", message);

        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<String, Request> result) {
                System.out.println("Sent message=[" + message
                        + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(@NotNull Throwable exception) {
                System.out.println("Unable to sent message=["
                        + message + "] due to: " + exception.getMessage());
            }
        });
        return message;
    }
}
