# Retrospectiva individual - Eduardo Olarte

## Autoevaluación (1 = no lo logro, 4 = lo hago con autonomía)

| Resultado | Puntaje | Evidencia |
|---|---:|---|
| RA1 Explico principios, patrones y estilos arquitectónicos | 4 | `ARQUITECTURA.md`, commit `22b8b57`. |
| RA2 Analizo ventajas, limitaciones y contextos | 4 | ADRs y matriz de decisión en el commit `22b8b57`. |
| RA3 Diseño arquitecturas con modelos y estándares | 4 | Diagrama C4 Mermaid y contratos documentados. |
| RA5 Implemento microservicios, EDA, CQRS, DDD y hexagonal | 4 | Commits `3bbe6f9`, `9e3d494` y la rama acumulativa del proyecto. |

## Tres aprendizajes que me llevo

1. Separar servicios no elimina la complejidad: la mueve a contratos, eventos,
   operación y consistencia eventual.
2. Un ADR debe explicar también el costo y la alternativa descartada.
3. Observabilidad, CI y guardarraíles son parte de la arquitectura, no tareas
   posteriores.

## Una decisión que tomaría distinto hoy

Antes de añadir más servicios mediría el costo operativo y reforzaría pruebas de
contratos. Para el tamaño de un equipo académico, un monolito modular podría
ser una alternativa más eficiente si no existe una necesidad clara de despliegue
independiente.

## Lo que me falta por aprender

Me falta experiencia con pruebas de integración reproducibles, trazas
distribuidas y despliegues en Kubernetes. Lo abordaría con Testcontainers,
OpenTelemetry y un laboratorio pequeño de despliegue antes de introducir esas
tecnologías en producción.

## Mi aporte al proyecto de grupo

- Implementación de observabilidad: Actuator, correlation id y propagación en
  la Saga, commit `97f94aa`.
- Contenerización de Eureka y CI, commit `9e3d494`.
- Documentación de arquitectura híbrida, commit `22b8b57`.
