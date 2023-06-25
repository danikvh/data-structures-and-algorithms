// Importar la clase Node
const Node = require('./Node.js');

class Tree {
    constructor(arr) {
        // Order and delete duplicates
        arr = Array.from(new Set(arr)).sort((a, b) => a - b);

        this.root = this.buildTree(arr)
    }

    buildTree(arr) {
        // Caso base
        if (arr.length == 0) return null

        const middle = Math.floor(arr.length / 2);
        const raiz = new Node(arr[middle])

        raiz.left = this.buildTree(arr.slice(0,middle))
        raiz.right = this.buildTree(arr.slice(middle + 1))

        return raiz
    }

    // Accepts a value to insert
    insert(value, root = this.root) {
        // Empty tree
        if (root === null) {
            root = new Node(value)
            return root
        }

        if (value < root.data) {
            root.left = this.insert(value, root.left)
        } else if (value > root.data) {
            root.right = this.insert(value, root.right)
        }

        return root
    }

    /*// Accepts a value to insert
    insert(value) {
        // Empty tree
        if (!this.root) {
            this.root = new Node(value)
            return
        }

        let actual = this.root
        while (actual) {
            if (actual.data == value) return
            else if (value < actual.data) {
                if (!actual.left) {
                    actual.left = new Node(value)
                    return
                }
                else actual = actual.left
            } else {
                if (!actual.right) {
                    actual.right = new Node(value)
                    return
                }
                else actual = actual.right
            }
        }
    }*/

    // Accepts a value to delete (you’ll have to deal with several cases such as when a node has children or not)
    delete(value) {

    }

    static prettyPrint (node, prefix = "", isLeft = true) {
        if (node === null) {
          return;
        }
        if (node.right !== null) {
          Tree.prettyPrint(node.right, `${prefix}${isLeft ? "│   " : "    "}`, false);
        }
        console.log(`${prefix}${isLeft ? "└── " : "┌── "}${node.data}`);
        if (node.left !== null) {
            Tree.prettyPrint(node.left, `${prefix}${isLeft ? "    " : "│   "}`, true);
        }
    };
}

module.exports = Tree;