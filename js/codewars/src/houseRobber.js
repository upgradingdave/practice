/**
 * @param {number} i
 * @param {number[]} nums
 * @return {number}
 */
// Note: this was my failed brute force attempt.
let trySum = function (i, nums) {

    if (nums.length - i === 1) {
        return nums[i];
    } else if (nums.length - i === 2) {
        let t0 = nums[i];
        let t1 = nums[i + 1];
        return t0 > t1 ? t0 : t1;
    } else {
        let t0 = trySum(i + 1, nums);
        let t1 = nums[i] + trySum(i + 2, nums);
        return t0 > t1 ? t0 : t1;
    }
}

/**
 * @param {number[]} nums
 * @return {number}
 */
let rob = function (nums) {
    if (nums === null || nums === undefined || nums.length <= 0) return 0;
    if (nums.length === 1) return nums[0];

    let result = [];
    result.push(nums[0]);
    result.push(Math.max(nums[0], nums[1]));

    for (let i = 2; i < nums.length; i++) {
        result.push(Math.max(result[i - 2] + nums[i], result[i - 1]));
    }
    return result[nums.length - 1];
};

module.exports = {
    houseRobber: rob
};
