# Manual Técnico

_Realizado por Gonzalo Montezuma - 202504046_

## `Practica2.java`

### Atributos

| Atributo      | Tipo           | Descripción                                                                               |
| ------------- | -------------- | ----------------------------------------------------------------------------------------- |
| `ASCENDENTE`  | `boolean`      | Define la dirección del ordenamiento, se actualiza desde `configPanel` al aplicar cambios |
| `VELOCIDAD`   | `int`          | Velocidad de animación en ms, se actualiza desde `configPanel` al aplicar cambios         |
| `ALGORITMO`   | `String`       | Nombre del algoritmo seleccionado en el combo, determina cuál clase instanciar            |
| `configPanel` | `getData`      | Panel de configuración: ingreso de datos, dirección y velocidad                           |
| `stats`       | `estadisticas` | Panel de estadísticas: contadores, log y botón de reportes                                |

### Métodos

| Método                    | Descripción                                                                                                   |
| ------------------------- | ------------------------------------------------------------------------------------------------------------- |
| `iniciarAlgoritmo()`      | Lee los valores de `configPanel`, resetea `stats`, e instancia el algoritmo correspondiente según `ALGORITMO` |
| `jButton1ActionPerformed` | Llama a `iniciarAlgoritmo()` al presionar el botón "Aplicar cambios"                                          |
| `main(String[])`          | Punto de entrada de la aplicación, aplica el LookAndFeel Nimbus y lanza la ventana principal                  |

## `sortVisualizer.java`

### Atributos

| Atributo      | Tipo                     | Descripción                                                                             |
| ------------- | ------------------------ | --------------------------------------------------------------------------------------- |
| `dataset`     | `DefaultCategoryDataset` | Conjunto de datos que alimenta el gráfico de barras                                     |
| `lblEstado`   | `JLabel`                 | Muestra el mensaje del paso actual del ordenamiento                                     |
| `lblTipo`     | `JLabel`                 | Muestra la dirección del ordenamiento mientras está en progreso                         |
| `btnOrdenar`  | `JButton`                | Botón que inicia o reinicia el ordenamiento                                             |
| `panel`       | `JPanel`                 | Panel principal donde se renderiza el gráfico                                           |
| `arreglo`     | `int[]`                  | Arreglo original recibido como entrada                                                  |
| `velocidad`   | `int`                    | Tiempo de pausa en ms entre cada paso visual                                            |
| `ascendente`  | `boolean`                | Define si el orden es ascendente o descendente                                          |
| `ordenados`   | `int[]`                  | Copia del arreglo ya ordenada, usada para colorear barras en su posición final          |
| `movimientos` | `int[]`                  | Contador de tres posiciones: `[0]` comparaciones, `[1]` intercambios, `[2]` iteraciones |
| `statsPanel`  | `estadisticas`           | Referencia al panel de estadísticas para actualizar contadores y log                    |

### Métodos

| Método                                                              | Descripción                                                                                                                                                           |
| ------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `sortVisualizer(panel, arreglo, ascendente, velocidad, statsPanel)` | Constructor completo, inicializa todos los atributos y llama a `inicializarUI()`                                                                                      |
| `sortVisualizer(panel, arreglo, statsPanel)`                        | Constructor simplificado, usa ascendente=true y velocidad=300 por defecto                                                                                             |
| `inicializarUI()`                                                   | Construye el gráfico de barras, los labels y el botón, y los agrega al panel                                                                                          |
| `iniciarOrdenamiento(int[])`                                        | Guarda el arreglo original, resetea contadores, mide el tiempo, lanza el hilo de ordenamiento y al terminar escribe el resumen en el log y llama a `agregarResumen()` |
| `actualizarDataset(int[], int, int)`                                | Redibuja el gráfico coloreando en rojo los índices comparados y en verde los ya ordenados                                                                             |
| `debeIntercambiar(int, int)`                                        | Evalúa si dos elementos deben intercambiarse según la dirección, e incrementa el contador de comparaciones                                                            |
| `notificarComparacion()`                                            | Incrementa `movimientos[0]` y actualiza el label de comparaciones en `statsPanel`                                                                                     |
| `notificarIntercambio()`                                            | Incrementa `movimientos[1]` y actualiza el label de intercambios en `statsPanel`                                                                                      |
| `notificarIteracion()`                                              | Incrementa `movimientos[2]` y actualiza el label de iteraciones en `statsPanel`                                                                                       |
| `pausa()`                                                           | Duerme el hilo actual `velocidad` milisegundos para controlar la velocidad de animación                                                                               |
| `getNombre()`                                                       | Abstracto — cada subclase retorna su nombre (ej. `"Bubble Sort"`)                                                                                                     |
| `ordenar(int[])`                                                    | Abstracto — cada subclase implementa su algoritmo de ordenamiento                                                                                                     |



## `quickSort.java`

| **Atributo** | **Descripción**                                              |
| ------------ | ------------------------------------------------------------ |
| `panel`      | Contenedor gráfico donde se visualiza el ordenamiento.       |
| `arreglo`    | El conjunto de datos numéricos que se están ordenando.       |
| `statsPanel` | Panel de control para mostrar logs y métricas del algoritmo. |
| `ascendente` | Define el criterio de orden (de menor a mayor o viceversa).  |
| `velocidad`  | Tiempo de espera entre cada operación para la animación.     |



| **Método**                                   | **Descripción**                                                                                     |
| -------------------------------------------- | --------------------------------------------------------------------------------------------------- |
| `quickSort(...)`                             | Constructores que inicializan la visualización invocando a `super()`.                               |
| `getNombre()`                                | Sobreescritura que retorna el string `"Quick Sort"` para la interfaz.                               |
| `ordenar(int[] arr)`                         | Punto de entrada que inicia el proceso recursivo del algoritmo.                                     |
| `quickSort_run(int[] arr, int izq, int der)` | Ejecuta la lógica recursiva de división y conquista del algoritmo.                                  |
| `partition(int[] arr, int izq, int der)`     | Organiza los elementos alrededor de un pivote y gestiona la actualización visual en el `UI Thread`. |
| `swap(int[] arr, int a, int b)`              | Realiza el intercambio de valores en el arreglo mediante operaciones aritméticas.                   |

## `bubbleSort.java`

| **Atributo** | **Descripción**                                                     |
| ------------ | ------------------------------------------------------------------- |
| `panel`      | Lienzo gráfico donde se dibujan las barras o elementos del arreglo. |
| `arreglo`    | Conjunto de datos sobre el cual se aplica el algoritmo de burbuja.  |
| `statsPanel` | Referencia al panel de métricas para escribir logs y resultados.    |
| `ascendente` | Indicador booleano para determinar la dirección del ordenamiento.   |
| `velocidad`  | Valor entero que controla el retardo (`pausa`) entre comparaciones. |

| **Método**                      | **Descripción**                                                                                                                                 |
| ------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------- |
| `bubbleSort(...)`               | Constructores sobrecargados que configuran la superclase con los parámetros del visualizador.                                                   |
| `getNombre()`                   | Retorna el nombre del algoritmo (`"Bubble Sort"`) para fines de visualización.                                                                  |
| `ordenar(int[] arr)`            | Implementación del método abstracto que dispara el hilo de ejecución de la burbuja.                                                             |
| `bubbleSort_run(int[] datos)`   | Contiene el bucle anidado del algoritmo. Incluye una optimización mediante la variable `changes` para detenerse si el arreglo ya está ordenado. |
| `swap(int[] arr, int a, int b)` | Intercambia dos valores en el arreglo utilizando operaciones de suma y resta (sin variable temporal).                                           |

## `shellSort.java`

| **Atributo** | **Descripción**                                                               |
| ------------ | ----------------------------------------------------------------------------- |
| `panel`      | Panel gráfico utilizado para renderizar la representación visual del arreglo. |
| `arreglo`    | Referencia al arreglo de enteros que se ordenará mediante el algoritmo.       |
| `statsPanel` | Panel destinado a la bitácora de eventos y visualización de estadísticas.     |
| `ascendente` | Determina si el orden de los elementos será de menor a mayor o viceversa.     |
| `velocidad`  | Controla el tiempo de retardo en las animaciones entre cada comparación.      |

| **Método**                          | **Descripción**                                                                                                                               |
| ----------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------- |
| `shellSort(...)`                    | Constructores que inicializan la clase base y configuran el entorno de visualización.                                                         |
| `getNombre()`                       | Sobrescribe el método base para devolver la etiqueta `"Shell Sort"`.                                                                          |
| `ordenar(int[] arr)`                | Invoca la ejecución del algoritmo pasando un denominador inicial de `2` para el cálculo del intervalo (gap).                                  |
| `shellSort_run(int[] arr, int den)` | Implementa la lógica de Shell Sort. Reduce el intervalo progresivamente y aplica un ordenamiento por inserción sobre los subgrupos generados. |

## `getData.java`

### Atributos

| Atributo         | Tipo            | Descripción                                                                        |
| ---------------- | --------------- | ---------------------------------------------------------------------------------- |
| `jTextField1`    | `JTextField`    | Campo de texto donde el usuario ingresa los números separados por coma             |
| `jToggleButton1` | `JToggleButton` | Botón que alterna entre ASCENDENTE y DESCENDENTE                                   |
| `jComboBox1`     | `JComboBox`     | Lista desplegable para seleccionar la velocidad de animación (500ms, 100ms, 020ms) |

### Métodos

| Método                          | Descripción                                                                                      |
| ------------------------------- | ------------------------------------------------------------------------------------------------ |
| `asegurarArchivoExiste()`       | Crea el archivo `DATOS.txt` si no existe                                                         |
| `is_digit(String)`              | Verifica si un `String` puede convertirse a entero, retorna `boolean`                            |
| `validate(String)`              | Divide el texto por comas y valida que cada elemento sea un entero, retorna `boolean`            |
| `write_file(String)`            | Divide el texto por comas y escribe cada número en una línea de `DATOS.txt`                      |
| `clearFile()`                   | Vacía el contenido de `DATOS.txt` antes de escribir nuevos datos                                 |
| `contarLineas()`                | Lee `DATOS.txt` y retorna el número de líneas como `int`                                         |
| `getDataArray()`                | Lee `DATOS.txt` y retorna su contenido como `int[]`                                              |
| `getAscendente()`               | Retorna `true` si el toggle está en ASCENDENTE, `false` si está en DESCENDENTE                   |
| `getVelocidad()`                | Extrae los primeros 3 caracteres del item seleccionado en el combo y retorna el valor como `int` |
| `jButton1ActionPerformed`       | Valida el input, pide confirmación y si se acepta llama a `clearFile()` y `write_file()`         |
| `jToggleButton1ActionPerformed` | Cambia el texto del toggle entre ASCENDENTE y DESCENDENTE según su estado                        |

## `estadisticas.java`

### Atributos

| Atributo        | Tipo          | Descripción                                                                    |
| --------------- | ------------- | ------------------------------------------------------------------------------ |
| `historial`     | `String`      | Acumula el resumen indexado de cada ejecución para la pestaña Historial        |
| `historialHTML` | `String`      | Acumula el resumen de cada ejecución como filas `<tr>` para la pestaña Reporte |
| `numEjecucion`  | `int`         | Contador que se incrementa con cada llamada a `agregarResumen()`               |
| `jTextArea1`    | `JTextArea`   | Área de texto de solo lectura que muestra el log paso a paso del ordenamiento  |
| `jScrollPane1`  | `JScrollPane` | Contenedor con scroll que envuelve el `jTextArea1`                             |

### Métodos

| Método                         | Descripción                                                                                                            |
| ------------------------------ | ---------------------------------------------------------------------------------------------------------------------- |
| `actualizarComparaciones(int)` | Actualiza el label de comparaciones en pantalla                                                                        |
| `actualizarIntercambios(int)`  | Actualiza el label de intercambios en pantalla                                                                         |
| `actualizarIteraciones(int)`   | Actualiza el label de iteraciones en pantalla                                                                          |
| `reset()`                      | Reinicia los tres labels numéricos a 0                                                                                 |
| `agregarResumen(String)`       | Incrementa el contador, añade la entrada indexada a `historial` y la fila HTML a `historialHTML`                       |
| `appendLog(String)`            | Añade una línea al `jTextArea1`                                                                                        |
| `resetLog()`                   | Limpia el contenido del `jTextArea1`                                                                                   |
| `jButton1ActionPerformed`      | Abre un `JFrame` con `JTabbedPane` de dos pestañas: Historial en `JTextArea` y Reporte en `JEditorPane` con tabla HTML |
