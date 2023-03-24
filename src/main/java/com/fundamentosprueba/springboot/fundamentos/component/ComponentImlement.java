package com.fundamentosprueba.springboot.fundamentos.component;

import org.springframework.stereotype.Component;

@Component
public class ComponentImlement implements ComponentDependency{
    @Override
    public void Saludar() {
        System.out.println("Hola Mundo desde componente");
    }
}
