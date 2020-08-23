/**
 * @param {number[]} arr
 * @return {number[]}
 */
let maxProfit = function (arr) {
    if (arr === undefined || arr === null || arr.length <= 0) {
        return arr;
    }

    let total = 0;
    let buyIdx = 0;
    while (buyIdx < arr.length) {
        let sold = false;
        let sellIdx = buyIdx;
        let max = 0;
        while(sellIdx < arr.length && !sold) {
            //console.log([total, buyIdx, sellIdx]);
            // should we sell?
            if(sellIdx < arr.length-1) {
                // there are more days for selling, peek ahead
                if(arr[sellIdx+1] >= arr[sellIdx]) {
                    // don't sell yet
                    sold = false;
                    sellIdx = sellIdx + 1;
                } else {
                    // sell!
                    sold = true;
                    total = total + arr[sellIdx] - arr[buyIdx];
                    buyIdx = sellIdx + 1;
                }
            } else {
                // no days left, only sell if stock went up
                if(arr[sellIdx] > max) {
                    sold = true;
                    total = total + arr[sellIdx] - arr[buyIdx];
                    buyIdx = sellIdx + 1;
                } else {
                    sellIdx = sellIdx + 1;
                    buyIdx = buyIdx + 1;
                }
            }
        }
    }
    return total;
};

module.exports = {
    maxProfit: maxProfit
};
