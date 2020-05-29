package com.luffy.view.service;

public interface MqService {
    void product(Integer exchange, String routKey, String message);

    void delayProduct(String message);
}
