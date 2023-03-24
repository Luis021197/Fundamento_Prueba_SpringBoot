package com.fundamentosprueba.springboot.fundamentos.component;

import org.springframework.stereotype.Component;

@Component
public class ComponentTwoImplement implements ComponentDependency{
    @Override
    public void Saludar() {
        System.out.println("Hola Mundo desde mi segundo componente");
    }
}
