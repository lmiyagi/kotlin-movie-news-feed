package br.com.leonardomiyagi.cinemablendfeed.model

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

/**
 * Created by lmiyagi on 11/5/17.
 */
@Root(name = "item", strict = false)
class Article {

    @get:Element(name = "title")
    @set:Element(name = "title")
    var title: String? = null
    @get:Element(name = "description")
    @set:Element(name = "description")
    var description: String? = null
    @get:Element(name = "link")
    @set:Element(name = "link")
    var link: String? = null
    @get:Element(name = "pubDate")
    @set:Element(name = "pubDate")
    var pubDate: String? = null
    @get:Element(name = "enclosure")
    @set:Element(name = "enclosure")
    var enclosure: Enclosure? = null

    @Root(name = "enclosure", strict = false)
    class Enclosure {

        @get:Attribute(name = "url")
        @set:Attribute(name = "url")
        var url: String? = null
    }
}