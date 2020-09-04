/**
 * @param {string} S
 * @return {number[]}
 */
let partitionLabels = function (s) {
    if (s === undefined || s === null || s.length <= 0) {
        return [];
    }

    let result = [];

    let leftIdx = 0;
    let rightIdx = 0;
    let left = 0;
    let right = s.length - 1;

    while (rightIdx < s.length) {
        let found = false;
        while (right > left && !found) {
            found = s[left] === s[right];
            if (!found) right--;
        }

        if (right <= rightIdx && left >= rightIdx) {
            // We found a partition
            //console.log(s.slice(leftIdx, rightIdx+1));
            result.push(rightIdx + 1 - leftIdx);

            // reset to find next partition
            leftIdx = rightIdx + 1;
            left = rightIdx + 1;
            rightIdx = leftIdx;
            right = s.length - 1;

        } else {
            if(right <= rightIdx) {
                // keep checking
                left = left + 1;
            } else {
                // possibly found rightIdx, start over checking all letters between leftIdx and rightIndx
                rightIdx = right;
                left = leftIdx + 1;
            }
            right = s.length - 1;
        }
    }

    return result;
};

module.exports = {
    partitionLabels: partitionLabels
};
