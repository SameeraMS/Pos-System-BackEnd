package org.example.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item implements Serializable {
    private String id;
    private String description;
    private double unitPrice;
    private int qtyOnHand;
}
