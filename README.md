# PAE_Eventos_JavaFX_AlexaAdolfo
# Retos de Programación con JavaFX

Proyecto desarrollado como parte de la asignatura de programación de aplicaciones de escritorio, compuesto por tres aplicaciones realizadas con JavaFX.

El objetivo de los retos es aplicar diferentes componentes, eventos y controles de JavaFX mediante situaciones prácticas.

## Integrantes

- Alexa Sofia Loaisiga Torrez
- Adolfo Carlos Ramírez Aráuz

---

# Reto 1. Inventario de pulpería

## Descripción

Aplicación para registrar y consultar productos de una pulpería.

Permite almacenar información básica de cada producto y visualizar los productos registrados.

## Funcionalidades principales

- Registro de código del producto.
- Registro del nombre.
- Registro del precio.
- Registro de cantidad disponible.
- Visualización de los productos registrados.
- Búsqueda rápida de productos.
- Validación de campos vacíos.
- Validación de valores numéricos.

## Eventos utilizados

### ActionEvent

Se utiliza principalmente para ejecutar acciones mediante botones, como guardar un nuevo producto.

### KeyEvent

Permite realizar la búsqueda de productos utilizando la tecla `ENTER`.

## Objetivo del reto

Aplicar eventos básicos de JavaFX y validaciones de datos dentro de una aplicación de inventario.

---

# Reto 2. Recepción de café

## Descripción

Aplicación para una cooperativa que necesita registrar y administrar los lotes de café entregados por los productores.

Los lotes registrados se muestran mediante un `TableView`.

## Funcionalidades principales

- Registro de lotes de café.
- Visualización de lotes en un `TableView`.
- Consulta de información de los lotes.
- Edición de registros.
- Eliminación de registros.
- Confirmación antes de eliminar un lote.

## Eventos y componentes utilizados

### MouseEvent

Se utiliza para seleccionar un lote de la tabla y mostrar sus detalles.

### ContextMenu

Permite acceder a las opciones disponibles sobre un lote seleccionado:

- Editar.
- Eliminar.

### Alert

Antes de eliminar un registro se muestra una alerta de confirmación para evitar eliminaciones accidentales.

## Objetivo del reto

Aplicar eventos de mouse, tablas, menús contextuales y alertas de confirmación utilizando JavaFX.

---

# Reto 3. Tienda de artesanías

## Descripción

Aplicación para administrar el catálogo de una tienda de artesanías nicaragüenses.

Permite registrar productos artesanales, mostrar su información y asociar una imagen a cada producto.

## Funcionalidades principales

- Registro de artesanías.
- Visualización de productos en un `TableView`.
- Visualización de la imagen de cada producto.
- Búsqueda de productos.
- Selección de imágenes.
- Menú de navegación.
- Barra de herramientas para las principales acciones.

## Menús

La aplicación contiene un `MenuBar` con las siguientes opciones:

- Catálogo.
- Ventas.
- Ayuda.

## Barra de herramientas

Se implementó un `ToolBar` con las acciones:

- Nuevo.
- Guardar.
- Buscar.

Cada opción ejecuta su acción mediante eventos de JavaFX.

## TableView

Los productos registrados se muestran de manera organizada dentro de un `TableView`, incluyendo información como:

- Imagen.
- Código.
- Nombre.
- Tipo de artesanía.
- Precio.

## Objetivo del reto

Integrar diferentes controles de JavaFX en una aplicación más completa, utilizando menús, barras de herramientas, eventos, imágenes y tablas.

---

# Estructura del repositorio

El repositorio contiene los tres programas desarrollados durante la práctica.

```text
Repositorio/
│
├── Reto 1 Inventario/
│
├── Reto 2 Recepcion de cafe/
│
├── Reto 3 Tienda de artesanias/
│
└── README.md
