const LinkedList = require('./Linked_List/LinkedList.js');
const Tree = require('./Balanced_Binary_Search_Tree/Tree.js');


// Función auxiliar para mostrar los resultados de manera atractiva en la consola
function mostrarResultado(titulo, resultado) {
    console.log(`\n${titulo}:`);
    if (resultado instanceof LinkedList) {
        console.log(resultado.toString())
        console.log("Tamaño: " + resultado.size())
        console.log("Head: " + resultado.getHead().value)
        console.log("Tail: " + resultado.getTail().value)
    }
    if (resultado instanceof Tree) {
        console.log(Tree.prettyPrint(resultado.root))
    }
}

// Prueba de LinkedList
function probarLinkedList() {
    const list = new LinkedList();
    list.append(10);
    list.append(20);
    list.append(30);
    list.append(40);
    list.prepend(5);
    mostrarResultado('LinkedList', list);
}

// Prueba de otras estructuras de datos
function probarBST() {
    arr = [1, 7, 4, 23, 8, 9, 4, 3, 5, 7, 9, 67, 6345, 324]
    tree = new Tree(arr)
    tree.insert(2)
    mostrarResultado('Balanced Binary Search Tree', tree);
}

// Ejecutar las pruebas
function ejecutarPruebas() {
    probarLinkedList();
    probarBST();
}

// Ejecutar las pruebas al cargar la página
ejecutarPruebas();