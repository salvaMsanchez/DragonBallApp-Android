<a name="top"></a>

<h1 align="center">
  <strong><span>Bootcamp Desarrollo de Apps Móviles </span></strong>
</h1>

---

<p align="center">
  <strong><span style="font-size:20px;">Módulo: Android Avanzado 🤖</span></strong>
</p>

---

<p align="center">
  <strong>Autor:</strong> Salva Moreno Sánchez
</p>

<p align="center">
  <a href="https://www.linkedin.com/in/salvador-moreno-sanchez/">
    <img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn">
  </a>
</p>

## Índice
 
* [Herramientas](#herramientas)
* [Proyecto: Dragon Ball App 🦸🏼](#proyecto)
	* [Descripción](#descripcion)
		* [Pantallas principales](#pantallasPrincipales)
			* [Login](#login)
			* [Listado](#listado)
			* [Detalle](#detalle)
			* [Listado de Favoritos](#listadoFavoritos)
		* [Tecnologías destacadas](#tecnologiasDestacadas)
			* [Retrofit](#retrofit)
			* [Corrutinas](#corrutinas)
			* [Room](#room)
			* [CLEAN *Arquitecture* y Principios SOLID](#clean)
			* [Inyección de dependencias con Hilt](#hilt)
			* [Google Maps API](#maps)
			* [Testing](#testing)
	* [Requisitos](#requisitos)
		* [Obligatorios](#obligatorios)
		* [Opcionales](#opcionales)
	* [Instalación](#instalacion)
	* [Licencia](#licencia)

<a name="herramientas"></a>
## Herramientas

<p align="center">

<a href="https://www.apple.com/es/ios/ios-17/">
   <img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" alt="Android">
 </a>
  
 <a href="https://www.swift.org/documentation/">
   <img src="https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white" alt="Kotlin">
 </a>
  
 <a href="https://developer.apple.com/xcode/">
   <img src="https://img.shields.io/badge/Android%20Studio-3DDC84.svg?style=for-the-badge&logo=android-studio&logoColor=white" alt="Android Studio">
 </a>
  
</p>

* **Android SDK:** API 24 ("Nougat"; Android 7.0)
* **Kotlin:** 1.9
* **Android Studio:** Hedgehog - 2023.1.1

<a name="proyecto"></a>
## Proyecto: Dragon Ball App 🦸🏼

![Demo app Android gif](images/demoAppAndroid.gif)

<a name="descripcion"></a>
### Descripción

La aplicación Android se centra en proporcionar una experiencia intuitiva y funcional para los usuarios que deseen explorar información sobre los superhéroes de Dragon Ball. A continuación, se presenta una breve descripción de las pantallas y las tecnologías destacadas empleadas en el proyecto.

<a name="pantallasPrincipales"></a>
#### Pantallas principales

<a name="login"></a>
##### Login

Pantalla de inicio que permite a los usuarios iniciar sesión utilizando las credenciales proporcionadas en el *bootcamp*.

<a name="listado"></a>
##### Listado

Pantalla principal que presenta un listado completo de personajes de Dragon Ball. La información se obtiene de manera eficiente a través de Retrofit, asegurando una conexión rápida y fiable con la red.

<a name="detalle"></a>
##### Detalle

Pantalla detallada de un personaje, que muestra información completa con un `BottomSheetDialog` y las localizaciones en las que ha estado a través de un mapa. Además, proporciona la capacidad de marcar a un héroe como favorito o no. Las actualizaciones de favoritos se sincronizan entre la base de datos local y remota para garantizar coherencia.

<a name="listadoFavoritos"></a>
##### Listado de Favoritos

Una pantalla adicional que muestra solo los personajes marcados como favoritos, proporcionando una experiencia personalizada para los usuarios.

<a name="tecnologiasDestacadas"></a>
#### Tecnologias destacadas

<a name="retrofit"></a>
##### Retrofit

Todas las llamadas a la red se realizan utilizando Retrofit, asegurando una comunicación eficiente y segura con el *backend*.

<a name="corrutinas"></a>
##### Corrutinas

Se implementa la paralelización de corrutinas siempre que sea posible para evitar esperas innecesarias y mejorar la eficiencia de la aplicación.

<a name="room"></a>
##### Room

La base de datos local se construye con Room, permitiendo un almacenamiento eficiente y una gestión fácil de los datos a través de consultas (*queries*) de SQL.

<a name="clean"></a>
##### CLEAN *Arquitecture* y Principios SOLID

La aplicación sigue el patrón de CLEAN *Architecture* y se adhiera a los principios SOLID para garantizar la escalabilidad, mantenibilidad y flexibilidad del código. La separación por capas se mantiene rigurosamente.

<a name="hilt"></a>
##### Inyección de dependencias con Hilt

Se implementa la inyección de dependencias utilizando Hilt para simplificar la gestión de componentes y promover la modularidad del código.

<a name="maps"></a>
##### Google Maps API

Se registra la aplicación en la *API* de *Google Maps* para proporcionar información sobre las localizaciones de los personajes de manera visual en la aplicación.

<a name="testing"></a>
##### Testing

El proyecto incluye pruebas obligatorias, que abarcan desde pruebas unitarias reales hasta *mocks* y *fakes*.

<a name="requisitos"></a>
### Requisitos

<a name="obligatorios"></a>
#### Obligatorios

* Todas las llamadas a red deben realizarse con Retrofit.
* Paralelización de corrutinas para evitar esperas innecesarias.
* Sincronización entre la base de datos local y remota para las actualizaciones de favoritos.
* Implementación de Room para la base de datos local.
* Aplicación de Clean *Architecture* y principios SOLID.
* Inyección de dependencias con Hilt.
* Registro de la aplicación en la *API* de *Google Maps*.
* Testing: pruebas unitarias reales, *mocks* y *fakes*.

<a name="opcionales"></a>
#### Opcionales

* Pantalla adicional de listado de favoritos.
* Diseño visual abierto, permitiendo libertad en la elección de componentes y representación de información. Para el listado de personajes, me he inspirado en el **[concepto creativo y prototipo](https://dribbble.com/shots/2671572-Marvel-App/attachments/537660?mode=media)** del usuario llamado [Luis Herrero](https://dribbble.com/luisherrero) encontrado en la web [Dribbble](https://dribbble.com).

<a name="instalacion"></a>
### Instalación

1. Clona el repositorio.
2. Abre el proyecto en Android Studio.
3. Introduce la *key* personal de la *API* de *Google Maps* en el archivo `local.properties`.
3. Conecta un dispositivo Android o utiliza un emulador.
4. Ejecuta la aplicación.

<a name="licencia"></a>
### Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE.md](https://github.com/salvaMsanchez/DragonBallApp-Android/blob/main/LICENSE.md) para más detalles.

---

[Subir ⬆️](#top)

---


