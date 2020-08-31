/**
 * @param {number[]} a
 * @param {number[]} b
 * @return {boolean}
 */
function pathsEqual(a,b) {
    return (a[0] === b[0] && a[1] === b[1]) || (a[0] === b[1] && a[1] === b[0]);
}

/**
 * @param {number} a
 * @param {number} b
 * @return {boolean}
 */
let shareFactors = function(a, b) {
    if(a===0 && b > 1 || b===0 && a > 1) {
        return true;
    } else if(a===0 || b===0) {
        return false;
    }

    let r = a % b;
    return shareFactors(b,r);
}

/**
 * @param {number[]} arr
 * @return {Object}
 */
let findEdges = function(arr) {
    let edges = {};
    for(let i=0; i<arr.length; i++) {
        for(let j=0; j<arr.length; j++) {
            if(i!==j) {
                let s = arr[i] > arr[j] ? shareFactors(arr[i],arr[j]) : shareFactors(arr[j],arr[i]);
                if(s) {
                    //console.log(`found edge between: ${arr[i]} and ${arr[j]}`);
                    let e = edges[arr[i]];
                    if(e == null) {
                        e = new Set();
                    }
                    e.add(arr[j]);
                    edges[arr[i]] = e;
                }
            }
        }
    }
    return edges;
}

/**
 * @param {Object} graph
 * @param {Object[]} vertex
 * @param {number[][]} visited
 * @return {number[][]}
 */
let findLongestPath = function(graph, vertex, visited) {

    if(visited.length >= Object.entries(graph).length) {
        return visited;
    }

    let neighbors = vertex[1];
    let result = visited;
    for(let next of neighbors) {
        let curr = [vertex[0], next];
        let found = false;
        let i=0;
        while(i<visited.length && !found) {
            found = pathsEqual(visited[i], curr);
            i++;
        }
        if(!found) {
            let visitedCopy = visited.slice(0);
            visitedCopy.push(curr);
            let tmp = findLongestPath(graph, [next, graph[next]], visitedCopy);
            result = tmp.length > result.length ? tmp : result;
            if(result.length >= Object.entries(graph).length) {
                return result;
            }
        }
    }
    return result;
}

let arrayMatch = function(a, b) {
    let found = a.length && b.length;
    let i=0;
    while(found && i<a.length) {
        found = a[i] === b[i];
        i++;
    }
    return found;
}

/**
 * @param {number[]} arr
 * @return {number}
 */
let largestComponentSize = function(arr) {
    if(arr === undefined || arr === null || arr.length <= 0) {
        return 0;
    }

    let edges = findEdges(arr);
    let limit = Object.entries(edges).length;
    let len = 0;
    for(const [key, value] of Object.entries(edges)) {
        let visited = findLongestPath(edges, [parseInt(key), value], []);
        let totalNodes = visited.length % 2 === 0 ? visited.length : visited.length + 1;
        len = totalNodes > len ? totalNodes : len;
        if(len >= limit) return limit;
    }
    return len;
};

module.exports = {
    largestComponentSize : largestComponentSize,
    shareFactors: shareFactors,
    pathsEqual: pathsEqual
};
