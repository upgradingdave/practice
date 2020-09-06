function TreeNode(val, left, right) {
    this.val = (val === undefined ? 0 : val)
    this.left = (left === undefined ? null : left)
    this.right = (right === undefined ? null : right)
}

/**
 * @param {number[]} arr
 * @param {number} low
 * @param {number}high
 * @return {number}
 */
let partition = function (arr, low, high) {
    let pivot = arr[high];
    let i = low - 1;
    for (let j = low; j <= high - 1; j++) {
        if(arr[j] < pivot) {
            i++;
            let tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    // swap arr[i+1] and arr[high]
    let tmp = arr[i+1];
    arr[i+1] = arr[high];
    arr[high] = tmp;
    return i+1;
}


/**
 * @param {number[]} arr
 * @param {number} low
 * @param {number}high
 * @return {number}
 */
let quicksort = function (arr, low, high) {

    if(low < high) {
        let pi = partition(arr, low, high);

        quicksort(arr, low, pi-1);
        quicksort(arr, pi+1, high);
    }
}

/**
 * @param {number[]} arr
 * @param {TreeNode} root
 */
let traverse = function(arr, root) {
    if(root !== null) {
        arr.push(root.val);
        traverse(arr, root.left);
        traverse(arr, root.right);
    }
}

/**
 * @param {TreeNode} root1
 * @param {TreeNode} root2
 * @return {number[]}
 */
let getAllElements = function (root1, root2) {
    let arr = [];
    traverse(arr, root1);
    traverse(arr, root2);
    quicksort(arr, 0, arr.length-1);
    return arr;
};

module.exports = {
    getAllElements: getAllElements,
    quicksort: quicksort,
    TreeNode: TreeNode,
    traverse: traverse
};
