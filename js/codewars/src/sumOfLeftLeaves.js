//  Definition for a binary tree node.
function TreeNode(val, left, right) {
    this.val = (val === undefined ? 0 : val)
    this.left = (left === undefined ? null : left)
    this.right = (right === undefined ? null : right)
}

let computeSumOfLeftLeaves = function (total, node, type) {
    if (node == null) {
        return 0;
    } else {
        console.log("processing node" + node.val);
        if (node.left == null && node.right == null) {
            console.log("this is a leaf");
            if(type === 1) {
                return total + node.val;
            } else {
                return total;
            }
        } else {
            return total + computeSumOfLeftLeaves(total, node.left, 1) + computeSumOfLeftLeaves(total, node.right, 2);
        }
    }
}

/**
 * @param {TreeNode} root
 * @return {number}
 */
let sumOfLeftLeaves = function (root) {
    return computeSumOfLeftLeaves(0, root, 0);
};

module.exports = {
    TreeNode: TreeNode,
    sumOfLeftLeaves: sumOfLeftLeaves
};
