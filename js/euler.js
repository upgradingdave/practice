module.exports = {

  problem1: function(n) {
    var result = 0;
    for(i=0; i<n; i++) {
      if(i % 3 == 0 || i % 5 ==0)
        result += i;
    }

    return result;
  }

};
