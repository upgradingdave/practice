/**
 * @param {string} s
 * @return {boolean}
 */
let repeatedSubstringPattern = function (s) {
    if (s === undefined || s === null || s.length <= 0) {
        return false;
    }

    for (let i = 0; i < s.length-1; i++) {
        let possible = s.slice(0, i + 1);
        let found = true;
        //console.log(`test possible substr: ${possible}`);
        for (let j = 0; j < s.length && found; j = j + i + 1) {
            let next = s.slice(j, j + i + 1);
            //console.log(`test ${possible} === ${next}`);
            found = possible === next;
        }
        if (found) return true;
    }
    return false;
};

module.exports = {
    repeatedSubstringPattern: repeatedSubstringPattern
};
