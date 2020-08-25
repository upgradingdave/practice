//https://leetcode.com/explore/challenge/card/august-leetcoding-challenge/552/week-4-august-22nd-august-28th/3436/

/**
 * @param {number[]} x
 */
let isNull = function (x) {
    return x === undefined || x === null || x.length <= 0
}

/**
 * @param {number[]} days
 * @param {number[]} costs
 * @param {number[]} periodLengths
 * @param {number} startIdx
 * @param {number} costsIdx
 */
let findPossible = function (days, costs, periodLengths,
                             startIdx, costsIdx) {
    let end = startIdx;
    let numDays = 0;
    while (end - startIdx < periodLengths[costsIdx]) {
        if(days[end] === 1) {
            numDays = numDays + 1;
        }
        end = end + 1;
    }

    //console.log(`startIdx: ${startIdx}, end: ${end}, numDays: ${numDays}, dayRange: ${end - startIdx}`);

    let ppd1 = costs[costsIdx] / numDays;
    let ppd2 = costs[costsIdx - 1] / periodLengths[costsIdx - 1];
    if(costsIdx <= 0 && numDays === 1) {
        console.log(`Buy a ${periodLengths[costsIdx]} ticket on day ${end}`)
        return true;
    } else if ( ppd1 < ppd2) {
        // yes this is worth it. average cost/day is less than average cost/day of smaller period ticket
        console.log(`Buy a ${periodLengths[costsIdx]} ticket on day ${end}`)
        return true;
    } else {
        //console.log(`Not worth a ${periodLengths[costsIdx]} ticket`);
        return false;
    }
}

/**
 * @param {number[]} days
 * @param {number[]} costs
 * @return {number}
 */
let mincostTickets = function (days, costs) {
    if (isNull(days) || isNull(costs)) {
        return 0;
    }

    let j = 0;
    let days365 = [];
    for(let i=0; i<365; i++) {
        if(i === days[j]) {
            days365[i] = 1;
            j = j + 1;
        } else {
            days365[i] = 0;
        }
    }

    let periodLengths = [1, 7, 30];
    let total = 0;
    for(let startIdx = 0; startIdx < 365; startIdx++) {
        let found = false;
        for (let costsIdx = 2; costsIdx >= 0 && !found; costsIdx--) {
            let found = findPossible(days365, costs, periodLengths, startIdx, costsIdx);
            //console.log(`startIdx: ${startIdx}, costsIdx: ${costsIdx}, found: ${found}`);
            if (found) {
                total = total + costs[costsIdx];
                startIdx = startIdx + periodLengths[costsIdx]-1;
            }
        }
    }
    return total;
};

module.exports = {
    mincostTickets: mincostTickets
};
