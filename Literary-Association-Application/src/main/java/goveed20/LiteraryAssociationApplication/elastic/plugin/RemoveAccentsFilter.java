/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package goveed20.LiteraryAssociationApplication.elastic.plugin;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;

public class RemoveAccentsFilter extends TokenFilter {
    private LatCyrUtils LatCyrUtils;
    private CharTermAttribute termAttribute;

    public RemoveAccentsFilter(TokenStream input) {
        super(input);
        termAttribute = (CharTermAttribute) input.addAttribute(CharTermAttribute.class);
    }


    @SuppressWarnings("static-access")
    @Override
    public boolean incrementToken() throws IOException {
        if (input.incrementToken()) {
            String text = termAttribute.toString();
            termAttribute.setEmpty();
            termAttribute.append(LatCyrUtils.removeAccents(text).replace("Dj", "D").replace("dj", "d"));
            return true;
        }
        return false;
    }

}
