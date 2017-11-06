package br.com.leonardomiyagi.cinemablendfeed.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

/**
 * Created by lmiyagi on 11/5/17.
 */
@Root(name = "rss", strict = false)
class FeedResponse {

    @get:Element(name = "channel")
    @set:Element(name = "channel")
    var channel: Channel? = null

    @Root(name = "channel", strict = false)
    class Channel {

        @get:ElementList(inline = true, entry = "item")
        @set:ElementList(inline = true, entry = "item")
        var articles: List<Article>? = null
    }
}