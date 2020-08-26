/**
 * @param {number} number
 * @return {string[]}
 */
let fizzBuzz = function(number) {
    let results = [];
    for(let x=1; x<=number; x++) {
        results.push(x % 3 === 0 && x % 5 === 0 ? "FizzBuzz" : x % 3 === 0 ? "Fizz" : x % 5 === 0 ? "Buzz" : ""+x);
    }
    return results;
};

module.exports = {
    fizzBuzz : fizzBuzz
};
