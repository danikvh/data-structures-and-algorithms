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
    delete(value, root = this.root, path = '') {
        // Caso base
        if (root === null) {
            return root
        }

        // Recursive calls for ancestors of node to be deleted
        if (root.data > value) {
            root.left = this.delete(value, root.left)
            return root
        } else if (root.data < value) {
            root.right = this.delete(value, root.right)
            return root
        }

        // We reach here when root is the node to be deleted.
        // If one of the children is empty
        if (root.left == null) {
            return root.right
        }
        else if (root.right == null) {
            return root.left
        }

        // If both children exist
        else {
            // El sucesor en orden es el nodo más pequeño en el 
            // subárbol derecho del nodo que deseas eliminar.
            let temp = root.right
            // Buscamos el sucesor inorder
            while (temp.left) {
                temp = temp.left
            }
            root.data = temp.data
            // Borrar el nodo copiado
            root.right = this.delete(root.data, root.right)
            return root
        }
    }

    find(value, node = this.root) {
        // Empty tree
        if (node === null || node.data === value) {
            return node
        }

        if (value < node.data) {
            return this.find(value, node.left)
        } else {
            return this.find(value, node.right)
        }
    }

    // Accepts another function as a parameter. levelOrder should traverse the tree in breadth-first 
    // level order and provide each node as the argument to the provided function. This function can 
    // be implemented using either iteration or recursion. The method should return an array of values 
    // if no function is given. Tip: You will want to use an array acting as a queue to keep track 
    // of all the child nodes that you have yet to traverse and to add new ones to the list
    levelOrder(f) {

    }

    // Accept a function parameter. Each of these functions should traverse the tree in their respective 
    // depth-first order and yield each node to the provided function given as an argument. The functions 
    // should return an array of values if no function is given.
    inorder(f) {

    }

    preorder(f) {

    }

    postorder(f) {

    }

    // Accepts a node and returns its height (number of edges in longest path from a given node to a leaf node)
    height(node = this.root) {
        if (node === null) { 
            return 0
        }
        const leftHeight = this.height(node.left)
        const rightHeight = this.height(node.right)
        
        return(Math.max(leftHeight,rightHeight)+1)
    }

    // Accepts a node and returns its depth (the number of edges in path from a given node to the tree’s root node)
    depth(node) {

    }

    // A balanced tree is one where the difference between heights of left subtree and right subtree of every node is not more than 1.
    isBalanced() {

    }

    // Rebalances an unbalanced tree. You’ll want to use a traversal method to provide a new array to the buildTree function.
    rebalance() {

    }

    static prettyPrint(node, prefix = "", isLeft = true) {
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