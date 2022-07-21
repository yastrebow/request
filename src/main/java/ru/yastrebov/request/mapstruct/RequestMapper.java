package ru.yastrebov.request.mapstruct;

import org.mapstruct.Mapper;
import ru.yastrebov.request.model.RequestDTO;
import ru.yastrebov.requestAnalyzerLib.model.Request;

@Mapper(componentModel = "spring")
public interface RequestMapper {

    RequestDTO requestToDto (Request request);

    Request dtoToRequest (RequestDTO requestDTO);
}
