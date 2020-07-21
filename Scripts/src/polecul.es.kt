package PolyculeGrapher

import jQueryInterface.jq
import org.w3c.dom.Window
import kotlin.browser.window
import kotlin.js.Math.log


private data class Graph(
        var scale: Double,
        var translate: Pair<Double, Double>,
        val links: List<PolyculeConnectionModel>,
        val nodes: List<PolyculeNodeModel>
)

private var windowGraph: Graph? = null

private var Window.graph: Graph?
    get() = windowGraph
    set(newValue) { windowGraph = newValue }


private var PolyculeConnectionModel.source: PolyculeNodeModel
    get() = this.members.first
    set(newValue) { this.members.first = newValue }

private var PolyculeConnectionModel.target: PolyculeNodeModel
    get() = this.members.second
    set(newValue) { this.members.second = newValue }


fun runPolyculEs() {
// set up SVG for D3
var width: Double  = 960.0
var height: Double = 500.0
var selected_node = null
var selected_link = null
var mousedown_link = null
var mousedown_node = null
var mouseup_node = null
var editing = false
var scale = window.graph?.scale ?: 1.0
var translate = window.graph?.translate ?: Pair(0.0, 0.0)

var panel = jq("#panel")
        .attr("oncontextmenu", "return false;")
        .attr("width", width)
        .attr("height", height)
var translateContainer = panel.append("<g></g>")
        .attr("transform", "translate(" + translate + ")")
var scaleContainer = translateContainer.append("<g></g>")
        .attr("transform", "scale(" + scale + ")")
var svg = scaleContainer.append("<g></g>")

fun zoom(newScale: Double) {
    var oldscale = scale
    scale += newScale
    window.graph?.scale = scale
    scaleContainer.attr("transform",  "scale(" + scale + ")")

    translate = Pair(
        translate.first + ((width * oldscale) - (width * scale)),
        translate.second + ((height * oldscale) - (height * scale))
    )
    window.graph?.translate = translate
    translateContainer.attr("transform", "translate(" + translate + ")");

    try {
        writeGraph()
    } catch (e: Exception) {
        console.error(e)
    }
}

fun pan(vert: Double, horiz: Double) {
    translate = Pair(
        translate.first + horiz,
        translate.second + vert
    )
    window.graph?.translate = translate;
    translateContainer.attr("transform", "translate(" + translate + ")");

    try {
        writeGraph();
    } catch (e: Exception) {
        console.error(e)
    }
}


window.graph?.links?.forEach { link ->
    window.graph?.nodes?.forEach { node ->
        if (node.id == link.source.id) {
            link.source = node;
        }
        if (node.id == link.target.id) {
            link.target = node;
        }
    }
}

jq("#in").click { zoom(0.1) }
jq("#out").click { zoom(-0.1) }

jq("#up"   ).click { pan( 10.0,   0.0) }
jq("#down" ).click { pan(-10.0,   0.0) }
jq("#left" ).click { pan(  0.0,  10.0) }
jq("#right").click { pan(  0.0, -10.0) }

//// init D3 force layout
//var force = d3.layout.force()
//        .nodes(window.graph?.nodes)
//        .links(window.graph?.links)
//        .size(Pair(width / scale, height / scale))
//        .linkDistance { log(3.0 / d.strength * 10.0) * 50.0 }
//        .charge(-500)
//        .on("tick", tick)


// line displayed when dragging new nodes
var drag_line = svg.append("<line></line>")
        .attr("class", "link dragline hidden");

// handles to link and node element groups
var path = jq(".link", svg.append("<g></g>"))
var node = jq(".node", svg.append("<g></g>"))

// update force layout (called automatically each iteration)
fun tick() {
    if (!drag_line.hasClass("hidden")) {
        return;
    }
    jq("line", path)
            .attr("x1", path.source.x )
            .attr("y1", path.source.y )
            .attr("x2", path.target.x )
            .attr("y2", path.target.y )
    path.select(".source-text")
            .attr("dx", fun(d) { return d.source.x})
            .attr("dy", fun(d) { return d.source.y + d.source.r * 2});
    path.select(".target-text")
            .attr("dx", fun(d) { return d.target.x})
            .attr("dy", fun(d) { return d.target.y + d.target.r * 2});
    path.select(".center-text")
            .attr("dx", fun(d) {
                return (d.source.x + ((d.target.x - d.source.x) / 2));
            })
            .attr("dy", fun(d) {
                return (d.source.y + ((d.target.y - d.source.y) / 2)) - 10;
            });
    node.attr("transform", fun(d) {
        return "translate(" + d.x + "," + d.y + ")";
    });
}

// update graph (called when needed)
fun restart() {
    // path (link) group
    path = path.data(window.graph.links);

    // update existing links
    path.classed("selected", fun(d) { return d === selected_link; });


    // add new links
    var pathG = path.enter()
            .append("<g></g>")
            .classed("link", true)
            .classed("selected", fun(d) { return d === selected_link; });
    pathG.append("<line></line>")
            .attr("x1", fun(d) { return d.source.x; })
            .attr("y1", fun(d) { return d.source.y; })
            .attr("x2", fun(d) { return d.target.x; })
            .attr("y2", fun(d) { return d.target.y; })
            .attr("stroke-width", fun(d) { return d.strength; })
            .attr("stroke-dasharray", fun(d) {
                if (d.dashed) {
                    return '' + [d.strength / 1.5, d.strength / 1.5];
                }
            });
    pathG.append("<text></text>")
            .attr("class", "center-text meaning hidden");
    pathG.append("<text></text>")
            .attr("class", "source-text meaning hidden");
    pathG.append("<text></text>")
            .attr("class", "target-text meaning hidden");
    // remove old links
    path.exit().remove();

    path.select("line")
            .attr("stroke-width", fun(d) { return d.strength; })
            .attr("stroke-dasharray", fun(d) {
                if (d.dashed) {
                    return '' + [d.strength / 1.5, d.strength / 1.5];
                }
            });
    path.select(".center-text")
            .text(fun(d) { return d.centerText; });
    path.select(".source-text")
            .text(fun(d) { return d.sourceText; });
    path.select(".target-text")
            .text(fun(d) { return d.targetText; });
    path.on("mouseover", fun(d) {
        d3.select(this).selectAll(".meaning")
                .classed("hidden", false);
    })
            .on("mouseout", fun(d) {
                d3.select(this).selectAll(".meaning")
                        .classed("hidden", true);
            });

    // circle (node) group
    // NB: the fun arg is crucial here! nodes are known by id, not by index!
    node = node.data(window.graph.nodes, fun(d) { return d.id; });

    // add new nodes
    var nodeG = node.enter()
            .append("<g></g>")
            .classed("node", true);

    nodeG.append("<circle></circle>")
            .attr("class", "node")
            .attr("r", fun(d) { return d.r; })
            .attr("style", fun(d) {
                if (d.dashed) {
                    return "fill:#ccc!important";
                }
            })
            .attr("stroke-dasharray", fun(d) {
                if (d.dashed) {
                    return '' + [d.r / 4, d.r / 4];
                }
            });

    // show node IDs
    nodeG.append("<text></text>")
            .attr("x", 0)
            .attr("y", fun(d) { return -d.r - 2; })
            .attr("class", "id")
            .attr("text-anchor", "middle")
            .text(fun(d) { return d.name; });

    node.select("circle")
            .attr("r", fun(d) { return d.r; })
            .attr("style", fun(d) {
                if (d.dashed) {
                    return "fill:#ccc!important";
                }
            })
            .attr("stroke-dasharray", fun(d) {
                if (d.dashed) {
                    return '' + [d.r / 4, d.r / 4];
                }
            });

    node.select(".id")
            .attr("y", fun(d) { return -d.r - 2; })
            .text(fun(d) { return d.name; });

    // remove old nodes
    node.exit().remove();

    // set the graph in motion
    force.start();
    try {
        writeGraph();
    } catch(e) {
        node.call(force.drag);
    }
}

fun panzoom() {
    d3.event.preventDefault()
    switch (d3.event.key) {
        case "ArrowUp":
        case "w":
        case "k":
        pan(10, 0);
        break;
        case "ArrowDown":
        case "s":
        case "j":
        pan(-10, 0);
        break;
        case "ArrowLeft":
        case "a":
        case "h":
        pan(0, 10);
        break;
        case "ArrowRight":
        case "d":
        case "l":
        pan(0, -10);
        break;
        case "+":
        zoom(0.1);
        break;
        case "-":
        zoom(-0.1);
        break;
    }
}

d3.select(window)
.on("keydown", panzoom);

// app starts here
restart()
}