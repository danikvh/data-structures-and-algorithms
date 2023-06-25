const LinkedList = require('./Linked_List/LinkedList.js');


// Función auxiliar para mostrar los resultados de manera atractiva en la consola
function mostrarResultado(titulo, resultado) {
    console.log(`\n${titulo}:`);
    console.log(resultado.toString());
}

// Prueba de LinkedList
function probarLinkedList() {
    const list = new LinkedList();
    list.append(10);
    list.append(20);
    list.append(30);
    list.append(40);
    mostrarResultado('LinkedList', list);
}

// Prueba de otras estructuras de datos
function probarOtrasEstructuras() {
    // Aquí puedes agregar pruebas de otras estructuras de datos
    // y mostrar los resultados utilizando la función mostrarResultado
}

// Ejecutar las pruebas
function ejecutarPruebas() {
    probarLinkedList();
    probarOtrasEstructuras();
}

// Ejecutar las pruebas al cargar la página
ejecutarPruebas();