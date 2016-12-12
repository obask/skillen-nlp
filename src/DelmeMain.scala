
import java.io.StringReader

import org.apache.lucene.analysis.TokenFilter
import org.apache.lucene.analysis.en.EnglishMinimalStemFilter
import org.apache.lucene.analysis.standard.StandardTokenizer
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute

import scala.collection.mutable
import scala.io.Source

object DelmeMain extends App {

  class CreateStemmer(filterFabric: StandardTokenizer => TokenFilter) {
    private val source = new StandardTokenizer
    private val filter = filterFabric(source)
    private val attribute = filter.addAttribute(classOf[CharTermAttribute])

    // not thread safe
    def stem(s: String): String = {
      source.setReader(new StringReader(s))
      filter.reset()
      filter.incrementToken()
      filter.close()
      attribute.toString
    }

  }

  println("BEGIN")

  val pathX = "/Users/baskakov/word_count.tsv"
//  val pathX = "/Users/baskakov/Downloads/count_1w.txt"

  val result00 = new mutable.HashMap[String, Double]

  val stemOneWord1 = new CreateStemmer(new EnglishMinimalStemFilter(_))

  val xxx = for (line <- Source.fromFile(pathX).getLines()) yield {
    val Array(a, b) = line.split('\t')
    a
  }
  val yyy = xxx.toArray

  val t0 = System.nanoTime()

  for (p <- yyy) yield stemOneWord1.stem(p)

  val t1 = System.nanoTime()
  println("Elapsed time: " + (t1 - t0) / 1000 / 1000 + " ms")
//  println(result.last)


  println("END")

}
