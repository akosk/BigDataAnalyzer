package hu.innocenter.bigdata.analyzer;



/**
 * Created by Ákos on 2015.09.22..
 *
 * #
 */




public abstract class Result {
    
    public String resultText;

    public String getResultText() {
        return resultText;
    }

    public String getResultTextAsHtml() {
        String html = resultText.replaceAll("\n", "<br/>");

        return html;

    }

    public void setResultText(String resultText) {
        this.resultText=resultText;
    }

    public void appendResultText(String text) {
        if (resultText==null) resultText="";
        this.resultText=this.resultText.concat(text);
//        this.resultText=this.resultText.concat(text).concat("\n");
    }


}
