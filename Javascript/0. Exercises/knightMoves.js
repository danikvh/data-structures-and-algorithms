// Odin's Project: Knights Travails
// Shows the shortest possible way to get from one square to another 
// by outputting all squares the knight will stop on along the way.

const possibleX = [-2, -2, -1, -1, 1, 1, 2, 2]
const possibleY = [-1, 1, -2, 2, -2, 2, -1, 1]
let vistos = []
let queue = []

// Obtains the possible moves given a position
function getPossibleMoves(x, y, possibleMoves = []) {
    for (let i = 0; i < 8; i++) {
        let X = x + possibleX[i]
        let Y = y + possibleY[i]
        if (X >= 0 && X <= 7 && Y >= 0 && Y <= 7) {
            possibleMoves.push([X,Y])
        }
    }
    return possibleMoves
}

// Obtains a path to get from the initial position to the end position
function getPath(inPos, endPos) {
    // Almacenamos recorridos
    const queue = [[inPos]]
    const visited = new Set([inPos])

    while (queue.length > 0) {
        const path = queue.shift()
        const currentPos = path[path.length - 1]
        
        if (currentPos.every((value, index) => value === endPos[index])) return path

        let newMoves = getPossibleMoves(currentPos[0], currentPos[1])
        for (let move of newMoves) {
            if (!vistos.includes(move)) {
                vistos.push(move)
                queue.push([...path, move])
            }
        }
    }

    return []
}

// Main function
function knightMoves(inPos, endPos) {
    let path = getPath(inPos, endPos)
    const moves = path.length - 1

    console.log(`You made it in ${moves} moves! Here's your path:`)
    for (let move of path) {
        console.log(move)
    }
}



console.log(knightMoves([3,3],[4,3]))
console.log(knightMoves([0,0],[1,2]))
console.log(knightMoves([0,0],[3,3]))
console.log(knightMoves([3,3],[0,0]))