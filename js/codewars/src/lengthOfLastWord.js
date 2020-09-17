/**
 * @param {string} s
 * @return {number}
 */
let lengthOfLastWord = function(s) {
    if(s === undefined || s.length <= 0) {
        return 0;
    }

    let count = 0;
    let s2 = s.trimEnd();
    for(let i=s2.length-1; i>=0; i--) {
        if(s2[i] !== ' ') {
            count++;
        } else {
            break;
        }
    }
    return count;
};

module.exports = {
    lengthOfLastWord : lengthOfLastWord
};
