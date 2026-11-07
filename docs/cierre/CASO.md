# Caso de estudio: modularidad de Shopify

## Contexto

Shopify describe un monolito Rails de más de 2,8 millones de líneas y 500.000
commits, mantenido por cientos de desarrolladores. El problema no era solo el
tamaño: el equipo necesitaba reducir el área que cada desarrollador debía
entender y evitar que nuevas funcionalidades degradaran el diseño.

## Decisión

En lugar de dividir inmediatamente el sistema en microservicios, Shopify creó
componentes independientes dentro del monolito. Estos componentes delimitan
responsabilidades, contratos y ownership, y permiten ejecutar pruebas sobre el
subconjunto afectado.

## Resultado

La fuente reporta mejoras de comprensión, discusiones de diseño más profundas,
triage de excepciones por componente y distribución del trabajo de mantenimiento.
También reconoce que la transformación requiere años, disciplina organizacional
y tooling; no es una extracción automática de servicios.

## Aplicación a RICA

La lección sí aplica parcialmente. RICA ya separó investigadores, publicaciones
y consultas porque tienen modelos y necesidades de comunicación distintas, pero
el equipo académico es pequeño y la infraestructura es limitada. Antes de crear
más servicios, debemos reforzar fronteras, contratos, ADRs y reglas de CI. Si la
carga o el equipo no crecen, volver a un monolito modular sería una alternativa
razonable y no un fracaso.

## Referencia IEEE

[1] Shopify Engineering, “The Modular Monolith,” Shopify Engineering, 2020.
[Online]. Available: https://shopify.engineering/shopify-monolith. [Accessed:
Nov. 7, 2026].
