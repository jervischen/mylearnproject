package com.example.demo;

import com.example.demo.util.DateUtil;
import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created in 2018-08-23 15:58.
 *
 * @author chenxiao
 */
public class PkFlag {
    public static void main(String[] args) {
        int zdds = 0x01;
        int yyht = 0x02;

        System.out.println(zdds | yyht);

        String[] sts = {"a", "b", "c"};

       /* String join = Joiner.on(",").join(sts);
        System.out.println(join);*/
        Joiner joiner = Joiner.on(",");
        for (String st : sts) {

        }
        List<Long> userIds = new ArrayList<>();
        System.out.println(joiner.join(userIds));


        System.out.println(DateUtil.formatCurrentTime("yyyy_MM_dd_HH"));


        /** 语音互通 */
        int CONNECT_VOICE = 0x02;

        int pkFlag = 0;
        /** 指定对手  */
        int DESIGNATED_RECEIVER = 0x01;

       /* pkFlag |= CONNECT_VOICE;
        System.out.println(pkFlag);*/
        pkFlag |= DESIGNATED_RECEIVER;
        System.out.println(pkFlag);


        System.out.println(0 | 1);
        double num = 2.3;
        String msg = String.format("恭喜主播排位3连胜获得了1个秋游碎片，主播今天一共收到了%f个碎片了，继续3连胜还可以获得更多碎片。", num);
    }
}
