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
        Tree.prettyPrint(resultado.root)
        console.log(resultado.find(5))
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

// Prueba BST
function probarBST() {
    arr = [1, 7, 4, 23, 8, 9, 4, 3, 5, 7, 9, 67, 6345, 324]
    tree = new Tree(arr)
    tree.insert(2)
    tree.delete(1)
    tree.delete(4)
    mostrarResultado('Balanced Binary Search Tree', tree);
}

// Prueba BST Odin's Project
function probarBST2() {
    // 1. Create a BST from a random array
    const array = [];
    for (let i = 0; i < 50; i++) {
        const numeroAleatorio = Math.floor(Math.random() * 100); // Genera un número aleatorio entre 0 y 99
        array.push(numeroAleatorio);
    }
    tree = new Tree(array)

    // 2. Confirm that the tree is balanced by calling isBalanced.
    
    // 3. Print out all elements in level, pre, post, and in order.

    // 4. Unbalance the tree by adding several numbers > 100.

    // 5. Confirm that the tree is unbalanced by calling isBalanced.

    // 6. Balance the tree by calling rebalance.

    // 7. Confirm that the tree is balanced by calling isBalanced.

    // 8. Print out all elements in level, pre, post, and in order.

    mostrarResultado('Balanced Binary Search Tree ODIN', tree);
}

// Ejecutar las pruebas
function ejecutarPruebas() {
    probarLinkedList();
    probarBST();
    probarBST2();
}

// Ejecutar las pruebas al cargar la página
ejecutarPruebas();