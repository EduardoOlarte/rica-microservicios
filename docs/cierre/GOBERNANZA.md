# Gobernanza de arquitectura de RICA

## Guardarraíles de arquitectura

1. **Regla:** el dominio no importa adaptadores HTTP, JPA, Kafka ni clases de
   infraestructura. **Se verifica con:** ArchUnit. **Cuándo:** cada push en CI.
2. **Regla:** un servicio no accede directamente a la base de datos de otro.
   **Se verifica con:** revisión de dependencias y prueba de arquitectura.
   **Cuándo:** cada Pull Request.
3. **Regla:** todo evento tiene productor, consumidores, campos requeridos y
   estrategia de idempotencia documentados. **Se verifica con:** pruebas de
   contratos JSON. **Cuándo:** al cambiar un evento.
4. **Regla:** los servicios compilan y ejecutan sus pruebas unitarias en la
   matriz de GitHub Actions. **Se verifica con:** `.github/workflows/ci.yml`.
   **Cuándo:** cada push y Pull Request.
5. **Regla:** las operaciones de Saga deben conservar `correlationId`.
   **Se verifica con:** prueba de integración y revisión de logs. **Cuándo:**
   antes de una entrega.

## Cómo se toman las decisiones

- Las decisiones locales de un módulo las toma quien implementa y las comunica.
- Las decisiones que cruzan servicios, eventos, APIs o bases de datos requieren
  un ADR revisado por al menos dos integrantes.
- Una excepción a un guardarraíl se registra como deuda, con responsable y fecha
  de revisión.
- Un cambio que modifique un contrato debe incluir pruebas de compatibilidad y
  actualizar `ARQUITECTURA.md`.

## Deuda técnica reconocida

| Deuda | Por qué se acepta | Riesgo | Revisión |
|---|---|---|---|
| Sin Testcontainers en CI | El curso priorizó pruebas unitarias | Errores de integración tardíos | Antes de producción |
| Sin esquema formal de Kafka | Pocos eventos y equipo pequeño | Consumidor incompatible | Al agregar el próximo evento |
| Sin trazas distribuidas | Actuator y MDC cubren la demo | Diagnóstico incompleto | Si crece la Saga |
| Configuración local de secretos | Entorno académico controlado | Exposición o divergencia | Antes de desplegar |

## Radar tecnológico

- **Adoptamos:** Spring Boot, PostgreSQL, MongoDB, Kafka y GitHub Actions porque
  ya resuelven los contratos, persistencia y automatización del proyecto.
- **Evaluaríamos:** Testcontainers y OpenTelemetry para integración reproducible
  y trazas distribuidas.
- **Evitaríamos por ahora:** service mesh, Kubernetes y data mesh; añaden costo
  operativo sin una escala o equipo que los justifique.
