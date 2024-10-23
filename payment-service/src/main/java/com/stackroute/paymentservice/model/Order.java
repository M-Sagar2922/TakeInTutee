package com.stackroute.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String firstName;
    private String lasName;
    private double price;
    private String currency;
    private String payment_method;
    private String intent;
    private String description;
}
