/**
 * @param {string} instructions
 * @return {boolean}
 */
let isRobotBounded = function(instructions) {

    let position = [0,0];
    let direction = 0;
    for(let i=0; i<instructions.length; i++) {
        let [x1,y1] = position;
        if(instructions[i] === 'G') {
            if(direction === 0) {
                position = [x1,y1+1];
            } else if(direction === 1) {
                position = [x1+1,y1];
            } else if(direction === 2) {
                position = [x1,y1-1];
            } else if(direction === 3) {
                position = [x1-1,y1];
            }
        } else if (instructions[i] === 'R') {
            if(direction === 3) {
                direction = 0;
            } else {
                direction = direction + 1;
            }
        } else if (instructions[i] === 'L') {
            if(direction === 0) {
                direction = 3;
            } else {
                direction = direction - 1;
            }
        }
        //console.log(`position: ${position}`);
        //console.log(`direction: ${direction}`);
    }

    let [x,y] = position;
    return (x === 0 && y === 0) || direction !== 0;

};

module.exports = {
    robotBoundedInCircle : isRobotBounded
};
