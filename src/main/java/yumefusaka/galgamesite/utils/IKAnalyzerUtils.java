package yumefusaka.galgamesite.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class IKAnalyzerUtils {

    /**
     * IK分词
     * @param target
     * @return
     */
    public static List<String> iKSegmenterToList(String target) throws Exception {
        if (StringUtils.isEmpty(target)){
            return new ArrayList<String>();
        }
        List<String> result = new ArrayList<>();
        StringReader sr = new StringReader(target);
        // 关闭智能分词 (对分词的精度影响较大)
        IKSegmenter ik = new IKSegmenter(sr, false);
        Lexeme lex;
        while((lex=ik.next())!=null) {
            String lexemeText = lex.getLexemeText();
            result.add(lexemeText);
        }

        //LOGGER.info("company:{}, iKSegmenterToList:{}", target, JSON.toJSON(result));
        return result;
    }

}
