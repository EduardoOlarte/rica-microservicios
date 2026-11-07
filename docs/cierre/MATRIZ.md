# Matriz de decisión ponderada

## Criterios y pesos

Los pesos priorizan integridad, el plazo académico y la capacidad del equipo.
No se pondera “modernidad” porque no es un resultado de negocio.

| Criterio | Peso | Justificación |
|---|---:|---|
| Integridad de datos | 5 | Una publicación no debe quedar asociada a un investigador inválido. |
| Tiempo de entrega | 4 | El proyecto tiene plazo académico limitado. |
| Modificabilidad | 4 | Se espera separar consultas y evolución de modelos. |
| Resiliencia | 4 | Kafka y la Saga deben tolerar fallas parciales. |
| Complejidad operativa | 3 | El equipo debe poder ejecutar y diagnosticar el sistema. |
| Escalabilidad independiente | 2 | Es útil, pero la carga del curso no exige escalar cada servicio. |

## Alternativas

- **A:** monolito modular con una base relacional.
- **B:** microservicios completos con infraestructura distribuida para todo.
- **C:** arquitectura híbrida actual de RICA.

La celda muestra `puntaje -> puntaje × peso`.

| Criterio | Peso | A: monolito modular | B: microservicios | C: híbrida |
|---|---:|---:|---:|---:|
| Integridad de datos | 5 | 5 -> 25 | 3 -> 15 | 4 -> 20 |
| Tiempo de entrega | 4 | 5 -> 20 | 2 -> 8 | 3 -> 12 |
| Modificabilidad | 4 | 3 -> 12 | 5 -> 20 | 4 -> 16 |
| Resiliencia | 4 | 2 -> 8 | 5 -> 20 | 4 -> 16 |
| Complejidad operativa | 3 | 5 -> 15 | 1 -> 3 | 3 -> 9 |
| Escalabilidad independiente | 2 | 2 -> 4 | 5 -> 10 | 4 -> 8 |
| **TOTAL** |  | **84** | **76** | **81** |

## Sensibilidad

Al aumentar en 2 el peso de integridad, los totales quedan A=94, B=82 y C=89:
el monolito gana. Al reducirlo en 2, quedan A=74, B=70 y C=73: el monolito
continúa ganando. Al aumentar en 2 modificabilidad, quedan A=90, B=86 y C=89:
la diferencia se reduce, pero no cambia el ganador.

La matriz revela que la arquitectura actual no gana solo por puntuación. Elegimos
C porque permite demostrar límites, eventos y CQRS sin pagar toda la complejidad
de microservicios completos, aceptando que el monolito modular sería más simple
para el tamaño actual del equipo. Reducir esta incertidumbre requiere medir carga
real y validar el costo operativo con Testcontainers.
