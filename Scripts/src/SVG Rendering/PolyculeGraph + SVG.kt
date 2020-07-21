package PolyculeGrapher

import org.w3c.dom.Element
import org.w3c.dom.svg.*
import kotlin.browser.document
import kotlin.dom.appendElement


fun PolyculeGraphModel.svgContents(): Element {
    return document.createElement("g").apply {
        appendElement("circle") {
            className = "node"
            setAttribute("r", "16")
        }
    }
}
