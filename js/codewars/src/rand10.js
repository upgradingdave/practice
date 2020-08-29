/**
 * @return {number} a random integer in the range 1 to 7
 */
let rand7 = function() {
    return Math.floor(Math.random() * Math.floor(7)) + 1;
}


let coordsToIdx = function(row, col) {
    return row % 7 + (col * 7);
}

/**
 * @return {number} a random integer in the range 1 to 10
 */
let rand10 = function() {
    let x = rand7()-1;
    let y = rand7()-1;
    let n = coordsToIdx(x,y);
    while (n >= 10) {
        x = rand7()-1;
        y = rand7()-1;
        n = coordsToIdx(x,y);
    }
    return n+1;
};

let expectedValue = function(n) {
    let total = 0;
    for(let i=1; i<=n; i++) {
        total = total + (i * (1/n));
    }
    return total;
}

module.exports = {
    rand10 : rand10,
    expectedValue : expectedValue,
    rand7 : rand7
};
