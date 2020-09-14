/**
 * @param {number[][]} intervals
 * @param {number[]} newInterval
 * @return {number[][]}
 */
let insert = function (intervals, newInterval) {

    if(intervals.length === 0) return [newInterval];

    let low = newInterval[0];
    let high = newInterval[1];

    let result = [];
    let i = 0;
    let added = false;
    while (i < intervals.length) {
        let thisLow = intervals[i][0];
        let thisHigh = intervals[i][1];

        if (thisLow > high) {
            if(!added) {
                result.push([low, high]);
                added = true;
            }
            // we're done, just add the rest of the intervals to result
            result.push(intervals[i]);
        } else if (thisHigh < low) {
            // this interval is lower, so add.
            result.push(intervals[i]);
        } else {
            low = low <= thisLow ? low : thisLow;
            high = high >= thisHigh ? high : thisHigh;

            while (!added) {

                if (i < intervals.length - 1) {
                    i++;
                    let nextLow = intervals[i][0];
                    let nextHigh = intervals[i][1];

                    if (nextLow > high) {
                        result.push([low, high]);
                        result.push(intervals[i]);
                        added = true;
                    } else {
                        high = high > nextHigh ? high : nextHigh;
                    }
                } else {
                    result.push([low, high]);
                    added = true;
                }
            }
        }
        i++;
    }
    if(!added) {
        result.push([low, high]);
    }
    return result;
}

module.exports = {
    insertInterval: insert
};
