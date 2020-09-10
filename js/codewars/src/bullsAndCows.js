/**
 * @param {string} secret
 * @param {string} guess
 * @return {string}
 */
let getHint = function(secret, guess) {
    let secretMap = {};
    let bulls = 0;
    let cows = 0;
    for(let i=0; i<secret.length; i++) {
        if(secret[i] === guess[i]) {
            bulls++;
        } else {
            if(!secretMap[secret[i]]) {
                secretMap[secret[i]] = 0;
            }
            secretMap[secret[i]] = secretMap[secret[i]] + 1;
        }
    }

    for(let i=0; i<guess.length; i++) {
        if(secret[i] !== guess[i] && secretMap[guess[i]] > 0) {
            cows++;
            secretMap[guess[i]] = secretMap[guess[i]] - 1;
        }
    }
    return `${bulls}A${cows}B`;
};

module.exports = {
    bullsAndCows : getHint
};
