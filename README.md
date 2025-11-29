# README – Sistema de Gestión de Stock e Inventario (Bazar)
## 1. Descripción del proyecto

Sistema backend para la administración integral de productos, ventas y clientes en un bazar. Permite gestionar el stock, registrar ventas, calcular totales y obtener métricas operativas clave.
El proyecto expone 19 endpoints y utiliza arquitectura multicapa orientada a buenas prácticas corporativas.

## 2. Requerimientos funcionales (resumen ejecutivo)

CRUD completo de productos y gestión de stock.

CRUD completo de clientes.

Registro de ventas y sus detalles (sin update).

Cálculo automático del total de la venta, subtotales y cantidades.

Descuento de stock solo si hay disponibilidad.

Búsqueda de productos con stock menor a 5.

Obtención del listado de productos de una venta específica.

Reportes operativos:

Monto total y cantidad de ventas en un día.

Venta con monto más alto (incluye cliente y cantidad de productos).

## 3. Cómo levantar el entorno

Requisitos previos:

JDK 17+

Maven

MySQL (o tu motor configurado)

## Pasos:

Clonar el repositorio.

Configurar application.properties con la conexión a la base de datos.

### Ejecutar:

mvn clean install

### Levantar el servidor:

mvn spring-boot:run

## 5. Endpoints principales y colección Postman

El proyecto expone 19 endpoints agrupados en:

Productos: CRUD, búsqueda, edición, baja, productos con stock < 5.

Clientes: CRUD, búsqueda individual y general.

Ventas: creación, listado completo, detalle de una venta, reportes diarios, venta más alta.

## Colección Postman:
Link a la colección →  https://rzz-matias18-7061175.postman.co/workspace/Matias-Rodriguez's-Workspace~ab5a65d3-1bae-4284-83bf-2262438b3e42/collection/49727979-6aeaaca6-7cd5-4e45-9bd3-da7c1d5d3f01?action=share&creator=49727979

## 6. Arquitectura general

Arquitectura multicapa corporativa:

controller

service

repository

dto

model

### Relaciones principales:

1 Producto ↔ N DetalleVenta

1 Venta ↔ N DetalleVenta

1 Venta ↔ 1 Cliente

Patrones utilizados:

DTO para desacoplar modelos y payloads.

Lógica de negocio concentrada en services.

Persistencia controlada vía Spring Data JPA.

## 7. Notas operativas clave

Validaciones mínimas: el sistema solo verifica stock disponible antes de descontar.

No implementa aún manejo de excepciones personalizado.

El front-end previsto será simple, profesional y adaptado a las funciones principales.

Versión sugerida:

Spring Boot: 3.5.7

Java: 17

Base de datos: MySQL 8+
