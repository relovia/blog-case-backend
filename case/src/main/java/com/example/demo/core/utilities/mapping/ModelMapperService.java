package com.example.demo.core.utilities.mapping;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
    ModelMapper forRequest();

    ModelMapper forResponse();
}