/**
 * @param {string} version1
 * @param {string} version2
 * @return {number}
 */
let compareVersion = function(version1, version2) {
    if((version1 === undefined && version2 === undefined) || (version1.length <= 0 && version2.length <= 0)) {
        return 0;
    }
    let v1 = version1.split(".");
    let v2 = version2.split(".");

    let i=0;
    let r1 = parseInt(v1[i], 10);
    while(i<v1.length) {
        r1 = parseInt(v1[i], 10);
        if(i>=v2.length) {
            if(r1 !== 0) {
                return 1;
            }
        }
        let r2 = parseInt(v2[i], 10);
        if(r1 > r2) {
            return 1
        }
        if(r2 > r1) {
            return -1
        }
        i++;
    }
    for(let j = i; j<v2.length; j++) {
        let r2 = parseInt(v2[j], 10);
        if(r2 > 0) { return -1 }
    }
    return 0;
};

module.exports = {
    compareVersionNumbers : compareVersion
};
