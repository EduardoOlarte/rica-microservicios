package com.rica.consultasservice.consultas;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "estadisticas")
public class Estadisticas {

    @Id
    private String id;
    private long investigadoresRegistrados;

    public long getInvestigadoresRegistrados() {
        return investigadoresRegistrados;
    }
}
