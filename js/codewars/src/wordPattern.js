/**
 * @param {string} pattern
 * @param {string} str
 * @return {boolean}
 */
let wordPattern = function (pattern, str) {
    if (pattern === undefined || str === undefined) {
        return false;
    }

    let arr = str.split(" ");
    if (pattern.length !== arr.length) {
        return false;
    }

    let p1 = {};
    let p2 = {};
    for (let i = 0; i < pattern.length; i++) {
        let t1 = p1[pattern[i]];
        let t2 = p2[arr[i]];

        if (t1 === undefined && t2 === undefined) {
            p1[pattern[i]] = arr[i];
            p2[arr[i]] = pattern[i];
        } else if (t2 !== pattern[i] || t1 !== arr[i]) {
            return false;
        }
    }
    return true;
};

module.exports = {
    wordPattern: wordPattern
};
