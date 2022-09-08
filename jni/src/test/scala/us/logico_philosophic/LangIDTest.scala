package us.logico_philosophic

import utest._
import LangId4J.{lang, langs, proba, probas}
import scala.jdk.CollectionConverters._

object LangIDTest extends TestSuite {
  val zh = "第一回　甄士隱夢幻識通靈　賈雨村風塵怀閨秀"
  val ja = "野島が初めて杉子に会ったのは帝劇の二階の正面の廊下だった。"
  val de = """
    Ihr naht euch wieder, schwankende Gestalten!
    Die früh sich einst dem trüben Blick gezeigt.
    Versuch’ ich wohl euch diesmal fest zu halten?
    Fühl’ ich mein Herz noch jenem Wahn geneigt?
    Ihr drängt euch zu! nun gut, so mögt ihr walten,
    Wie ihr aus Dunst und Nebel um mich steigt;
    Mein Busen fühlt sich jugendlich erschüttert
    Vom Zauberhauch der euren Zug umwittert."""
  val fr = """Il y avait en Vestphalie, dans le château de M. le baron de
    Thunder-ten-tronckh, un jeune garçon à qui la nature avait donné les moeurs
    les plus douces. Sa physionomie annonçait son âme."""
  val en = """It is a truth universally acknowledged, that a single man in
    possession of a good fortune, must be in want of a wife."""
  val mixed = """The first line of Voltaire's most famous novel, Candide, reads
    "Il y avait en Vestphalie, dans le château de M. le baron de
    Thunder-ten-tronckh, un jeune garçon à qui la nature avait donné les moeurs
    les plus douces. Sa physionomie annonçait son âme." If you are reading these
    tests, you might compare it to the other opening lines from some of these
    other works of literature above. Does it move you like the others do?"""
  val tests = Tests{
    test("lang"){
      lang(zh) ==> "zh"
      lang(ja) ==> "ja"
      lang(de) ==> "de"
      lang(fr) ==> "fr"
      lang(en) ==> "en"
      assert(Set("en", "fr")(lang(mixed)))}
    test("langs"){
      langs(en, 3).size ==> 3
      langs(en, 3).get(0) ==> "en"
      langs(mixed, 2).asScala.toSet ==> Set("en", "fr")}
  }}
