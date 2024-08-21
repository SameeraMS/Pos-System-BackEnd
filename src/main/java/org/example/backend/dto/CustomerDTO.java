package org.example.backend.dto;

import jakarta.json.bind.annotation.JsonbProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO implements Serializable {
    @JsonbProperty("id")
    private String id;
    @JsonbProperty("name")
    private String name;
    @JsonbProperty("address")
    private String address;
    @JsonbProperty("contact")
    private String contact;
}
