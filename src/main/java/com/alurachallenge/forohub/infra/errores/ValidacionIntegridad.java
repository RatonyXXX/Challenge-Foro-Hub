package com.alurachallenge.forohub.infra.errores;

public class ValidacionIntegridad extends RuntimeException {

    public ValidacionIntegridad(String s) {
        super(s);
    }

}