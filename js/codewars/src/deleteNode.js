//Definition for a binary tree node.
function TreeNode(val, left, right) {
    this.val = (val === undefined ? 0 : val)
    this.left = (left === undefined ? null : left)
    this.right = (right === undefined ? null : right)
}

let traverseTreeNode = function (node, result) {
    if (node === null) {
        return result;
    }

    if (node.left !== null || node.right !== null) {
        result.push(node.left === null ? null : node.left.val);
        result.push(node.right === null ? null : node.right.val);
    }

    if (node.left !== null) {
        result.concat(result, traverseTreeNode(node.left, result));
    }

    if (node.right !== null) {
        result.concat(result, traverseTreeNode(node.right, result));
    }

    return result;
}

/**
 * @param {TreeNode} treeNode
 */
let treeNodeToArray = function (treeNode) {
    return traverseTreeNode(treeNode, [treeNode.val]);
}

/**
 * @param {TreeNode} root
 * @param {number} val
 * @return {TreeNode}
 */
//TODO: Incomplete!!
let deleteNode = function (root, val) {
    if (root === null) {
        return null;
    }

    if(root.val === val) {
        return new TreeNode(null, null, null);
    }

    let foundNode = null;

    if (root.left !== null && root.left.val === val) {
        foundNode = root.left;
    }

    if (root.right !== null && root.right.val === val) {
        foundNode = root.right;
    }

    if (foundNode != null) {
        root.left = foundNode.right;
        root.left.left = foundNode.left;
        return root;
    } else {
        // keep searching
        let foundInLeft = null;
        if (root.left !== null) {
            foundInLeft = deleteNode(root.left, val);
        }
        if (root.right !== null && foundInLeft===null) {
            deleteNode(root.right, val);
        }
    }
    return root;
}

module.exports = {
    TreeNode: TreeNode,
    deleteNode: deleteNode,
    treeNodeToArray: treeNodeToArray
};
