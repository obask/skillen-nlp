
import java.nio.file.{Files, Paths}

import lib.StemmingWrapper
import org.apache.lucene.analysis.en.KStemFilter

import scala.collection.mutable
import scala.io.Source

object DelmeMain extends App {

  println("BEGIN")

  val pathX = "/Users/baskakov/word_count.tsv"
  private val OUTPUT_PATH = "term_count.tsv"

  //  val pathX = "/Users/baskakov/Downloads/count_1w.txt"

  val result00 = new mutable.HashMap[String, Double]

  val stemmer = new StemmingWrapper(new KStemFilter(_))

  for (line <- Source.fromFile(pathX).getLines()) {
    val Array(a, b) = line.split('\t')
    val term = stemmer.stemWord(a)
    val count = b.toDouble
    result00(term) = result00.getOrElse(term, 0.0) + count
  }
  val res = result00.toSeq.sortBy(_._2).reverse
  val out = Files.newBufferedWriter(Paths.get(OUTPUT_PATH))
  for ((x, y) <- res) {
    out.write(x + "\t" + y.toLong + "\n")
  }
  out.close()


  println("END")

}
