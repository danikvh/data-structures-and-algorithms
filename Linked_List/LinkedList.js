// Importar la clase Node
const Node = require('./Node.js');

// Definición de la clase LinkedList
class LinkedList {
    constructor() {
        this.head = null
    }

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
}

module.exports = LinkedList;