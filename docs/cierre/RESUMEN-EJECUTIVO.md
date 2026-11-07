# Resumen ejecutivo - RICA

**Pedimos:** apoyar la consolidación de RICA como plataforma académica para
gestionar investigadores y publicaciones, manteniendo la arquitectura actual y
financiando una fase corta de pruebas de integración.

**Por qué:** el sistema necesita registrar información confiable y permitir que
las consultas sigan funcionando aunque una parte de la propagación de eventos
se retrase. También debe poder evolucionar sin rehacer todo el sistema.

**Qué proponemos:** RICA usa un punto de entrada único y servicios separados
para investigadores, publicaciones y consultas. Las validaciones inmediatas usan
una comunicación directa; los cambios que pueden propagarse después usan eventos.
Una proyección de lectura permite consultar sin cargar el modelo operacional.

**Qué ganamos / qué cuesta:**

- **Ganamos:** límites claros, consultas independientes, recuperación ante fallas
  de mensajería y trazabilidad de una eliminación.
- **Cuesta:** más procesos, bases de datos diferentes, consistencia eventual y
  necesidad de mantener contratos entre servicios.

**Principales riesgos y cómo los controlamos:**

1. Una Saga puede quedar a medias: alertas, reintentos y compensación.
2. Un cambio de evento puede romper consumidores: ADRs y pruebas de contratos.
3. El entorno local puede ocultar fallas: Testcontainers en la siguiente fase.

**Siguiente paso:** el equipo debe completar la revisión cruzada, ensayar la
defensa de diez minutos y ejecutar una medición de carga; el responsable se
asigna en `REPARTO.md`.
