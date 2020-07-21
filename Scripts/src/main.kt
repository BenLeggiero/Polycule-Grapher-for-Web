
package PolyculeGrapher
import jQueryInterface.*

fun main() {
    jq {
        jq("#polyculeGraph")
                .append(PolyculeGraphModel(nodes= emptyList(), connections= emptyList())
                        .svgContents()
                )
    }
}
