/**
 * @param {number[]} arr
 * @return {number[]}
 */
let removeDuplicates = function(arr) {
    if(arr === undefined || arr === null || arr.length <= 1) {
        return arr;
    }

    let prev = arr[0];
    for(let i=1; i<arr.length; i++) {
        while(arr[i] === prev) {
            arr.splice(i, 1);
        }
        prev = arr[i];
    }
};

module.exports = {
    removeDuplicates : removeDuplicates
};
