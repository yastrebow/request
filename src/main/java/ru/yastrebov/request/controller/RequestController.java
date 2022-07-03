package ru.yastrebov.request.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yastrebov.request.model.RequestDTO;
import ru.yastrebov.request.service.RequestService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/request")
public class RequestController {

    private final RequestService requestService;

    @PostMapping
    public ResponseEntity<RequestDTO> postRequest(@RequestBody RequestDTO requestDTO) {

        return new ResponseEntity<>(requestService.postRequest(requestDTO), HttpStatus.OK);


    }


}
