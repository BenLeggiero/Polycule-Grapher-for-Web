if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'Scripts'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'Scripts'.");
}var Scripts = function (_, Kotlin) {
  'use strict';
  var Unit = Kotlin.kotlin.Unit;
  function main$lambda() {
    $('main').text('Test');
    console.log('Tested');
    return Unit;
  }
  function main(args) {
    $(main$lambda);
  }
  _.main_kand9s$ = main;
  main([]);
  Kotlin.defineModule('Scripts', _);
  return _;
}(typeof Scripts === 'undefined' ? {} : Scripts, kotlin);
