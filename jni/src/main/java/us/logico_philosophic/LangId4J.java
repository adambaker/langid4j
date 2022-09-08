package us.logico_philosophic;

import com.github.jfasttext.JFastText;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.io.InputStream;

final public class LangId4J {
  public static class Proba{
    public final double prob;
    public final String lang;
    public Proba(double p, String l){
      this.prob = p;
      this.lang = l;}
  }
  public static class JFT {
    private JFastText jft;
    public JFT(String modelFile){
      this.jft = new JFastText();
      this.jft.loadModel(modelFile);
    }
    public List<String> langs(String text, int num){
      List<String> labs = jft.predict(text.replace('\n', ' '), num);
      for(int i = 0; i < num; i++)
        labs.set(i, labs.get(i).substring(9)); //strip the "__label__" prefix
      return labs;
    }
    public String lang(String text){
      return jft.predict(text.replace('\n', ' ')).substring(9);
    }
    public List<Proba> probas(String text, int num){
      List<Proba> probs = new ArrayList(num);
      List<JFastText.ProbLabel> ftprobs =
        jft.predictProba(text.replace('\n', ' '), num);
      for(JFastText.ProbLabel pl: ftprobs)
        probs.add(new Proba(Math.exp(pl.logProb), pl.label.substring(9)));
      return probs;
    }
    public Proba proba(String txt){
      return probas(txt, 1).get(0);
    }
  }
  public static JFT fromResources(String resource) throws java.io.IOException {
    InputStream is = LangId4J.class.getClassLoader().getResourceAsStream(resource);
    java.io.File tmp = Files.createTempFile(null,
        resource.endsWith(".ftz") ? ".ftz" :
        resource.endsWith(".bin") ? ".bin" : null).toFile();
    Files.copy(is, tmp.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
    return new JFT(tmp.getAbsolutePath());
  }
  private static JFT ftSmall;
  static {
    try{ftSmall = fromResources("lid.176.ftz");}
    catch(java.io.IOException e){System.err.println(e);}}
  public static List<String> langs(String text, int num){
    return ftSmall.langs(text, num);}
  public static String lang(String text){ return ftSmall.lang(text); }
  public static List<Proba> probas(String text, int num){
    return ftSmall.probas(text, num);}
  public static Proba proba(String txt){return ftSmall.proba(txt);}
}
