function TreeNode(val, left, right) {
    this.val = (val === undefined ? 0 : val)
    this.left = (left === undefined ? null : left)
    this.right = (right === undefined ? null : right)
}

/**
 * @param {string} acc
 * @param {TreeNode} root
 * @return {number}
 */
let traverse = function (acc, root) {
    if (root.left === null && root.right === null) {
        return parseInt(acc + root.val, 2);
    }

    let result = 0;
    if (root.left !== null) {
        result += traverse(acc+root.val, root.left);
    }

    if (root.right !== null) {
        result += traverse(acc+root.val, root.right);
    }
    return result;
}

/**
 * @param {TreeNode} root
 * @return {number}
 */
let sumRootToLeaf = function (root) {
    return traverse("", root);
};

module.exports = {
    sumRootToLeaf: sumRootToLeaf,
    TreeNode: TreeNode
};
