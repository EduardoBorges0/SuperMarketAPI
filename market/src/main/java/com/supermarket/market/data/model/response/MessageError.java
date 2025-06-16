package com.supermarket.market.data.model.response;

import lombok.Data;

@Data
public class MessageError {
    public String message;
    public Integer code;
}
