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
	* [Requisitos](#requisitos)
		* [Obligatorios](#obligatorios)
		* [Opcionales](#opcionales)
	* [Problemas, decisiones y resolución](#problemas)
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

### APUNTES

- Seguir con la implementación del SharedPreferences. ✅
- Acabar de implementar el Login y pasar al listado de Héroes

Minuto 1.38 del video de android intermedio de Aristidevs

<a name="descripcion"></a>
### Descripción

Dragon Ball Battle Simulator es una aplicación Android desarrollada en Kotlin que permite a los usuarios simular batallas con personajes de Dragon Ball. La aplicación sigue un patrón de diseño MVVM y cumple con una serie de requisitos obligatorios y opcionales, brindando una experiencia de juego coherente.

<a name="requisitos"></a>
### Requisitos

<a name="obligatorios"></a>
#### Obligatorios

1. **Ventanas y actividades:**
	* Ventana 1: Actividad de inicio de sesión.
	* Ventanas 2 y 3: Fragmentos compartidos en una segunda actividad.
2. **MVVM:**
	* Implementación del patrón de diseño Modelo-Vista-VistaModelo.
3. **Diseño coherente y eficaz:**
	* Diseño libre, pero con atención a la coherencia y eficacia en la presentación de elementos.
4. **Clases de Personajes:**
	* `CharacterDTO`: representa el JSON descargado del servidor.
	* `Character`: clase propia de la aplicación, con puntos de vida y veces seleccionado añadidos. Utilización de la función `map` para transformar de `CharacterDTO` a `Character` con las modificaciones correspondientes.

<a name="opcionales"></a>
#### Opcionales

1. **Restaurar puntos de vida:**
	* Botón en Ventana 2 que, al ser pulsado, restaura todos los puntos de vida de los personajes a 100.
2. **Persistencia del juego:**
	* Los puntos de vida de los personajes se conservan incluso si el usuario cierra la aplicación.
3. **Estadísticas de selección:**
	* Botón en Ventana 3 que muestra el número de veces que el usuario ha seleccionado ese personaje mediante un mensaje estilo Toast.

<a name="problemas"></a>
### Problemas, decisiones y resolución

* Podemos mencionar el problema del manejo del token al gestionarlo con el context y la facilidad que tiene su implmentaciñón con DaggerHilt

#### Manejo del Token en SharedPreferences

Al abordar la persistencia del token en SharedPreferences, se reconoce que esta práctica no es óptima, pero se ha implementado utilizando un enfoque que utiliza un `object` a modo de *Singleton* denominada `SharedPreferencesService`. Aunque inicialmente se intentó gestionar el token directamente en la clase `APIClient` mediante una variable con acceso personalizado (`get` y `set`), se encontró dificultad al tratar con el contexto necesario para interactuar con SharedPreferences. Intentar pasar el contexto al `APIClient` generó problemas, especialmente al necesitarlo desde el *ViewModel*, lo cual representa una mala práctica ya que podría causar fugas de memoria (*memory leaks*).

Para superar este desafío, y como ya he comentado, se optó por utilizar un objeto *Singleton* que permitiera acceder a los datos de *SharedPreferences* desde cualquier parte de la aplicación. Aunque esto resolvió el problema inmediato, es importante tener en cuenta las consideraciones de seguridad y explorar alternativas más robustas para gestionar el almacenamiento seguro de datos sensibles.

#### Elección e inicialización de Vistas en LoginActivity

En la implementación actual, la decisión de inicializar la vista principal de la aplicación (*Home*) o la del *Login* se realiza en el método `onCreate` de la actividad `LoginActivity`. Esta elección se basa en la simplicidad y en las limitadas exigencias arquitectónicas de la aplicación. Sin embargo, se reconoce que, dependiendo de las funcionalidades del proyecto, otras estrategias podrían haberse considerado.

Alternativas como la inclusión de un `SplashScreen` con lógica de enrutamiento o la creación de una `RouteActivity` para gestionar estas decisiones podrían haber sido exploradas. Estas opciones ofrecen una mayor flexibilidad y escalabilidad, especialmente en proyectos que pueden evolucionar con requisitos más complejos.

#### Gestión de Activities y Fragments en el Logout

Al tratar con la gestión de la destrucción de *activities* y *fragments* durante el *logout*, surgió un problema al navegar desde el primer fragment de `HomeActivity` al `LoginActivity`. Se observó que, aunque se realizaba la navegación al `LoginActivity`, el *fragment* no se destruía adecuadamente. Esto resultaba en comportamientos inesperados al realizar un *"back"* desde el dispositivo después de un nuevo inicio de sesión, ya que se volvía al *fragment* previamente no destruido.

Para abordar esta situación, es crucial garantizar la destrucción adecuada de *fragments* y *activities* al realizar acciones como el *logout*. Se podría explorar el uso de *popBackStack* o estrategias similares para asegurar que la pila de fragmentos se gestione correctamente y se evite la reaparición de *fragments* no deseados después de ciertas transiciones, como el *login*. La comprensión profunda de los ciclos de vida de *activities* y *fragments* es esencial para evitar problemas relacionados con la navegación en la aplicación.

<a name="instalacion"></a>
### Instalación

1. Clona el repositorio.
2. Abre el proyecto en Android Studio.
3. Conecta un dispositivo Android o utiliza un emulador.
4. Ejecuta la aplicación.

<a name="licencia"></a>
### Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE.md](https://github.com/salvaMsanchez/DragonBallApp-Android/blob/main/LICENSE.md) para más detalles.

---

[Subir ⬆️](#top)

---


