# ATAM-lite de RICA

## Árbol de utilidad

| Atributo | Escenario: estímulo, respuesta y medida | Importancia | Dificultad |
|---|---|---:|---:|
| Integridad | Si se registra una publicación con un investigador inexistente, gRPC rechaza la operación y no se persiste la publicación. Medida: 0 publicaciones inválidas en pruebas de contrato. | A | M |
| Resiliencia | Si Kafka se detiene después de guardar un investigador, el evento permanece en Outbox y se publica al recuperarse Kafka. Medida: 100% de eventos pendientes publicados sin pérdida. | A | M |
| Interoperabilidad | Si un cliente usa el Gateway, puede acceder a los tres APIs sin conocer direcciones internas. Medida: rutas REST disponibles mediante el puerto 8000. | A | B |
| Modificabilidad | Si cambia la proyección de consultas, los servicios de escritura no se modifican. Medida: cambio aislado en consultas-service y CI verde. | M | M |
| Observabilidad | Si se elimina un investigador con `X-Correlation-Id`, el mismo id aparece en los logs de los participantes. Medida: coincidencia del id en investigadores y publicaciones. | M | M |

Los escenarios A/A y A/M reciben prioridad porque afectan directamente la
confianza en los datos y la continuidad del flujo.

## Análisis de escenarios prioritarios

### Integridad — A/M

- **No-riesgo:** el contrato gRPC valida al investigador antes de persistir.
- **Riesgo:** la dependencia síncrona puede fallar o agotar su tiempo de espera.
- **Sensibilidad:** timeout y configuración del Circuit Breaker.
- **Compromiso:** consistencia inmediata a cambio de acoplamiento temporal.
- **Mitigación:** probar timeout, fallback y errores de contrato en CI.

### Resiliencia — A/M

- **No-riesgo:** Transactional Outbox conserva el evento junto al cambio.
- **Riesgo:** el poller puede acumular eventos o publicar duplicados.
- **Sensibilidad:** disponibilidad de Kafka y frecuencia del polling.
- **Compromiso:** durabilidad y recuperación a cambio de consistencia eventual.
- **Mitigación:** métricas de lag, reintentos, consumidores idempotentes y alerta
  sobre eventos pendientes antiguos.

### Interoperabilidad — A/B

- **No-riesgo:** Gateway y Eureka ocultan ubicaciones internas al cliente.
- **Riesgo:** una ruta, nombre de servicio o contrato puede cambiar sin aviso.
- **Sensibilidad:** configuración de Eureka y compatibilidad de payloads.
- **Compromiso:** menor acoplamiento del cliente a cambio de un componente extra.
- **Mitigación:** pruebas de rutas y contratos versionados.

### Modificabilidad — M/M

- **No-riesgo:** consultas-service mantiene un modelo de lectura separado.
- **Riesgo:** cambios de esquema de eventos pueden romper consumidores.
- **Sensibilidad:** campos obligatorios y evolución de JSON.
- **Compromiso:** independencia de lectura a cambio de reproyección y duplicación.
- **Mitigación:** contratos de eventos, compatibilidad hacia atrás y replay.

### Observabilidad — M/M

- **No-riesgo:** Actuator y correlation id hacen visible el flujo de la Saga.
- **Riesgo:** consumidores que no propaguen el MDC rompen la trazabilidad.
- **Sensibilidad:** propagación del campo `correlationId` en cada evento.
- **Compromiso:** más información en eventos y logs a cambio de diagnóstico.
- **Mitigación:** prueba de integración que busque un mismo id en todos los logs.

## Tres riesgos principales

1. **Saga atascada en `EN_ELIMINACION`.** Añadir alerta por antigüedad, reintento
   controlado y una compensación explícita antes de ampliar la Saga.
2. **Contratos Kafka incompatibles.** Versionar eventos y agregar pruebas de
   consumidor/productor antes de aceptar cambios de esquema.
3. **Dependencia de infraestructura local.** Usar Testcontainers en CI y
   documentar una configuración reproducible de PostgreSQL, MongoDB y Kafka.
