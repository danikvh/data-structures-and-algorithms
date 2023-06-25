// Importar la clase Node
const Node = require('./Node.js');

// Definición de la clase LinkedList
class LinkedList {
    constructor() {
        this.head = null
    }

    // Adds a new node containing value to the end of the list
    append(value) {
        const nodo = new Node(value)
        // Lista vacía
        if(!this.head) {
            this.head = nodo
        } 
        // Recorrer la lista hasta llegar al último nodo
        else {
            let current = this.head
            while (current.nextNode) {
                current = current.nextNode
            }
            current.nextNode = nodo
        }
    }

    // Adds a new node containing value to the start of the list
    prepend(value) {
        const nodo = new Node(value)
        // Lista vacía
        if(!this.head) {
            this.head = nodo
        } 
        // Añadir al principio
        else {
            nodo.nextNode = this.head
            this.head = nodo
        }
    }

    // Returns the total number of nodes in the list
    size() {
        // Lista vacía
        if(!this.head) {
            return 0
        }
        let size = 1
        let actual = this.head
        while (actual.nextNode) {
            size++
            actual = actual.nextNode
        }
        return size
    }

    // Returns the first node in the list
    getHead() {
        return this.head
    }

    // Returns the last node in the list
    getTail() {
        if(!this.head) return null
        let actual = this.head
        while (actual.nextNode) {
            actual = actual.nextNode
        }
        return actual
    }

    // Returns the node at the given index
    at(index) {
        return null
    }

    // Removes the last element from the list
    pop() {
        return null
    }

    // Returns true if the passed in value is in the list and otherwise returns false.
    contains(value) {
        return null
    }

    // Returns the index of the node containing value, or null if not found.
    find(value) {
        return null
    }

    // Represents your LinkedList objects as strings
    toString() {
        let current = this.head;
        let result = '';
        while (current) {
          result += `(${current.value}) -> `;
          current = current.nextNode;
        }
        result += 'null';
        return result;
    }

    // Inserts a new node with the provided value at the given index.
    insertAt(value, index) {
        return null
    }

    // Removes the node at the given index.
    removeAt(index) {
        return null
    }
}

module.exports = LinkedList;