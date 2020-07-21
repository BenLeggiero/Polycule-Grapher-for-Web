if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'Scripts'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'Scripts'.");
}var Scripts = function (_, Kotlin) {
  'use strict';
  var defineInlineFunction = Kotlin.defineInlineFunction;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var Unit = Kotlin.kotlin.Unit;
  var appendElement = Kotlin.kotlin.dom.appendElement_ldvnw0$;
  var emptyList = Kotlin.kotlin.collections.emptyList_287e2$;
  var get_parentElement = defineInlineFunction('Scripts.jQueryInterface.get_parentElement_s15u7w$', function ($receiver) {
    return $receiver.parentElement;
  });
  function PolyculeGraphModel(nodes, connections) {
    this.nodes = nodes;
    this.connections = connections;
  }
  PolyculeGraphModel.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PolyculeGraphModel',
    interfaces: []
  };
  PolyculeGraphModel.prototype.component1 = function () {
    return this.nodes;
  };
  PolyculeGraphModel.prototype.component2 = function () {
    return this.connections;
  };
  PolyculeGraphModel.prototype.copy_4gdegq$ = function (nodes, connections) {
    return new PolyculeGraphModel(nodes === void 0 ? this.nodes : nodes, connections === void 0 ? this.connections : connections);
  };
  PolyculeGraphModel.prototype.toString = function () {
    return 'PolyculeGraphModel(nodes=' + Kotlin.toString(this.nodes) + (', connections=' + Kotlin.toString(this.connections)) + ')';
  };
  PolyculeGraphModel.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.nodes) | 0;
    result = result * 31 + Kotlin.hashCode(this.connections) | 0;
    return result;
  };
  PolyculeGraphModel.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.nodes, other.nodes) && Kotlin.equals(this.connections, other.connections)))));
  };
  function PolyculeNodeModel(id, name) {
    this.id = id;
    this.name = name;
  }
  PolyculeNodeModel.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PolyculeNodeModel',
    interfaces: []
  };
  PolyculeNodeModel.prototype.component1 = function () {
    return this.id;
  };
  PolyculeNodeModel.prototype.component2 = function () {
    return this.name;
  };
  PolyculeNodeModel.prototype.copy_19mbxw$ = function (id, name) {
    return new PolyculeNodeModel(id === void 0 ? this.id : id, name === void 0 ? this.name : name);
  };
  PolyculeNodeModel.prototype.toString = function () {
    return 'PolyculeNodeModel(id=' + Kotlin.toString(this.id) + (', name=' + Kotlin.toString(this.name)) + ')';
  };
  PolyculeNodeModel.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.id) | 0;
    result = result * 31 + Kotlin.hashCode(this.name) | 0;
    return result;
  };
  PolyculeNodeModel.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.id, other.id) && Kotlin.equals(this.name, other.name)))));
  };
  function PolyculeConnectionModel(memberIds) {
    this.memberIds = memberIds;
  }
  PolyculeConnectionModel.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PolyculeConnectionModel',
    interfaces: []
  };
  PolyculeConnectionModel.prototype.component1 = function () {
    return this.memberIds;
  };
  PolyculeConnectionModel.prototype.copy_1fzo63$ = function (memberIds) {
    return new PolyculeConnectionModel(memberIds === void 0 ? this.memberIds : memberIds);
  };
  PolyculeConnectionModel.prototype.toString = function () {
    return 'PolyculeConnectionModel(memberIds=' + Kotlin.toString(this.memberIds) + ')';
  };
  PolyculeConnectionModel.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.memberIds) | 0;
    return result;
  };
  PolyculeConnectionModel.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.memberIds, other.memberIds))));
  };
  function svgContents$lambda$lambda($receiver) {
    $receiver.className = 'node';
    $receiver.setAttribute('r', '16');
    return Unit;
  }
  function svgContents($receiver) {
    var $receiver_0 = document.createElement('g');
    appendElement($receiver_0, 'circle', svgContents$lambda$lambda);
    return $receiver_0;
  }
  function main$lambda() {
    $('#polyculeGraph').append(svgContents(new PolyculeGraphModel(emptyList(), emptyList())));
    return Unit;
  }
  function main() {
    $(main$lambda);
  }
  var package$jQueryInterface = _.jQueryInterface || (_.jQueryInterface = {});
  package$jQueryInterface.get_parentElement_s15u7w$ = get_parentElement;
  var package$PolyculeGrapher = _.PolyculeGrapher || (_.PolyculeGrapher = {});
  package$PolyculeGrapher.PolyculeGraphModel = PolyculeGraphModel;
  package$PolyculeGrapher.PolyculeNodeModel = PolyculeNodeModel;
  package$PolyculeGrapher.PolyculeConnectionModel = PolyculeConnectionModel;
  package$PolyculeGrapher.svgContents_2z7udp$ = svgContents;
  package$PolyculeGrapher.main = main;
  main();
  Kotlin.defineModule('Scripts', _);
  return _;
}(typeof Scripts === 'undefined' ? {} : Scripts, kotlin);
