/*
 * (C) 2019 LIGHTWORKS CORP.
 * システム名 : Web入会
 * 注意事項 :
 */
package com.example.scheduler.config;

/**
 * <p>機能について短い文で「・・・。」とする。</p >
 *
 * @author NWT : gong <br />
 * 変更履歴 <br />
 * 2019/10/15 : gong: 新規<br />
 * @version 1.0
 */
public class Test {
    public static void  main(String [] args){
        String s1 = new StringBuilder("go").append("od").toString();
        System.out.println(s1.intern() == s1);
        String s2 = new StringBuilder("ja").append("va").toString();
        String s3=s2.intern();
        System.out.println(s2);System.out.println(s3);

        System.out.println(s3 == s2);
    }
}
