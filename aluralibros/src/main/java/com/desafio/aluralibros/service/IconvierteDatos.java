package com.desafio.aluralibros.service;

public interface IconvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
