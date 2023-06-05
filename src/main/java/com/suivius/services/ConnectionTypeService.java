package com.suivius.services;


import com.suivius.models.ConnectionType;
import com.suivius.repo.ConnectionTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectionTypeService {

    ConnectionTypeRepository connectionTypeRepository;

    public ConnectionTypeService(ConnectionTypeRepository connectionTypeRepository){
        this.connectionTypeRepository =connectionTypeRepository;
    }

    public List<ConnectionType> getAll(){
        return connectionTypeRepository.findAll();
    }

}
