package lib

import java.io.StringReader

import org.apache.lucene.analysis.TokenFilter
import org.apache.lucene.analysis.standard.StandardTokenizer
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute

class StemmingWrapper(filterFabric: StandardTokenizer => TokenFilter) {
  private val source = new StandardTokenizer
  private val filter = filterFabric(source)
  private val attribute = filter.addAttribute(classOf[CharTermAttribute])

  // not thread safe
  def stemWord(s: String): String = {
    source.setReader(new StringReader(s))
    filter.reset()
    filter.incrementToken()
    filter.close()
    attribute.toString
  }

}
