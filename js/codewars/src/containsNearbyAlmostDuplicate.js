/**
 * @param {number[]} arr
 * @param {number} k
 * @param {number} t
 * @return {boolean}
 */
let containsNearbyAlmostDuplicate = function (arr, k, t) {
    if (arr === undefined || arr === null || arr.length <= 0) {
        return false;
    }
    let found = false;
    for (let i = 0; i < arr.length; i++) {
        for(let j=i+1; j<arr.length; j++) {
            if(Math.abs(arr[i] - arr[j]) <= t && Math.abs(i - j) <= k) {
                //console.log(`i:${i},j:${j}`);
                return true;
            }
        }
    }
    return found;
};

module.exports = {
    containsNearbyAlmostDuplicate: containsNearbyAlmostDuplicate
};
