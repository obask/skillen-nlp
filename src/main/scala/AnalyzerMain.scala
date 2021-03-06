import java.io.StringReader

import org.apache.lucene.analysis.{Analyzer, TokenStream}
import org.apache.lucene.analysis.en.EnglishAnalyzer
import org.apache.lucene.analysis.tokenattributes._

// From chapter 4
object AnalyzerMain {
  private val examples: Array[String] = Array("The quick brown fox jumped over the lazy dog", "Please switch the light's color", "XY&Z Corporation - xyz@example.com")
  private val analyzers: Array[Analyzer] = Array[Analyzer](new EnglishAnalyzer)

  def displayTokens(analyzer: Analyzer, text: String) {
    displayTokens(analyzer.tokenStream("contents", new StringReader(text)))
  }

  def displayTokens(stream: TokenStream) {
    stream.reset()
    val charTermAttribute: CharTermAttribute = stream.addAttribute(classOf[CharTermAttribute])
    val offsetAttribute: OffsetAttribute = stream.addAttribute(classOf[OffsetAttribute])
    while (stream.incrementToken) {
      {
        System.out.print("[")
        System.out.print(":" + charTermAttribute.toString)
        System.out.print(":" + offsetAttribute.startOffset)
        System.out.print(":" + offsetAttribute.endOffset)
        System.out.print("] ")
      }
    }
    stream.close()
  }

  def main(args: Array[String]) {
    var strings: Array[String] = examples
    if (args.length > 0) {
      strings = args
    }
    for (text <- strings) {
      analyze(text)
    }
  }

  private def analyze(text: String) {
    System.out.println("Analyzing \"" + text + "\"")
    for (analyzer <- analyzers) {
      val name: String = analyzer.getClass.getSimpleName
      System.out.println("  " + name + ":")
      System.out.print("    ")
      displayTokens(analyzer, text)
      System.out.println("\n")
    }
  }
}