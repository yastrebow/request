package ru.yastrebov.request.service;

import ru.yastrebov.request.model.RequestDTO;

public interface RequestService {
    RequestDTO postRequest(RequestDTO requestDTO);
}
