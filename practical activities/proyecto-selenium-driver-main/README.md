# Documentación básica: HTML, DOM, CSS, ID, Clases, JavaScript

## ¿Qué es el DOM?
El DOM (Document Object Model) es una representación estructurada del documento HTML en forma de árbol. Permite a los lenguajes de programación (como JavaScript) acceder, modificar y manipular los elementos de la página web.

## ¿Qué es una clase CSS?
Una clase CSS es un selector que se utiliza para aplicar estilos a uno o varios elementos HTML. Se define en el atributo `class` del elemento y en la hoja de estilos CSS con un punto (`.`) antes del nombre.

**Ejemplo HTML:**
```html
<div class="mi-clase">Contenido</div>
```
**Ejemplo CSS:**
```css
.mi-clase {
  color: red;
}
```

## ¿Qué es un ID?
Un ID es un identificador único para un elemento HTML. Se define con el atributo `id` y en CSS se selecciona con el símbolo `#`.

**Ejemplo HTML:**
```html
<input id="nombre" type="text">
```
**Ejemplo CSS:**
```css
#nombre {
  border: 1px solid blue;
}
```

## ¿Qué es CSS?
CSS (Cascading Style Sheets) es el lenguaje que se utiliza para definir la presentación y el diseño de los elementos HTML en una página web. Permite cambiar colores, tamaños, posiciones, márgenes, etc.

## ¿Qué es JavaScript?
JavaScript es un lenguaje de programación que permite agregar interactividad y dinamismo a las páginas web. Puede modificar el DOM, validar formularios, crear animaciones, manejar eventos y mucho más.

## Relación entre HTML, DOM, CSS, ID, Clases y JavaScript
- **HTML** define la estructura y el contenido de la página.
- **DOM** es la representación interna de ese HTML, accesible por JavaScript.
- **CSS** aplica estilos visuales a los elementos HTML usando selectores como clases (`.clase`) e IDs (`#id`).
- **ID** identifica de forma única un elemento en el DOM y HTML.
- **Clase** permite agrupar varios elementos para aplicarles el mismo estilo.
- **JavaScript** accede y manipula el DOM para cambiar el contenido, estilos o comportamiento de los elementos HTML.

---