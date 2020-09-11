/**
 * @param {number[]} nums
 * @return {number}
 */
let maxProduct = function(nums) {
    if(nums === null || nums.length <= 0) {
        return 0;
    }

    let maxProduct = nums[0];
    for(let j=0; j<nums.length; j++) {
        let running = nums[j];
        if(running >= maxProduct) {
            maxProduct = running;
        }
        for (let i = j+1; i < nums.length; i++) {
            running = running * nums[i];
            if (running >= maxProduct) {
                maxProduct = running;
            }
        }
    }

    return maxProduct;
};

module.exports = {
    maximumProductSubarray : maxProduct
};
