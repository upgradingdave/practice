/**
 * @param {number[]} arr
 * @param {number} i
 * @param {number} j
 */
let swap = function (arr, i, j) {
    let tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
}

/**
 * @param {number} k
 * @param {number[]} arr
 * @param {function} f
 * @return {string}
 */
let permute = function (k, arr, f) {
    if (k === 1) {
        f(arr);
    } else {
        permute(k - 1, arr, f);

        for (let i = 0; i < k - 1; i++) {
            if (k % 2 === 0) {
                swap(arr, i, k - 1);
            } else {
                swap(arr, 0, k - 1);
            }
            permute(k - 1, arr, f);
        }
    }
}

/**
 * @param {number} hours
 * @param {number} minutes
 * @return {number} seconds
 */
let toSeconds = function (hours, minutes) {
    return ((hours * 60) + minutes) * 60;
}

/**
 * @param {number[]} arr
 * @return {string}
 */
let largestTimeFromDigits = function (arr) {
    if (arr === undefined || arr === null || arr.length <= 0) {
        return "00:00";
    }

    let max = toSeconds(23, 59);
    let min = toSeconds(0, 0);
    let result = min;
    let time = "";
    permute(4, arr, function (arr) {
        let hours = parseInt("" + arr[0] + arr[1]);
        let mins = parseInt("" + arr[2] + arr[3]);
        let secs = toSeconds(hours, mins);
        if (mins >= 0 && mins < 60 && hours >= 0 && hours < 24 && secs >= min && secs <= max) {
            if (secs >= result) {
                result = secs;
                time = `${arr[0]}${arr[1]}:${arr[2]}${arr[3]}`;
            }
        }
    });

    return time;
};

module.exports = {
    largestTimeFromDigits: largestTimeFromDigits
};
