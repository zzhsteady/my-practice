package com.practice.nomal.Collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zhaozh on 2016/1/7.
 */
public class IteratorTest {


    @Test
    public void test(){
            List<String> list = new ArrayList<String>();
            list.add( "0" );
            list.add( "1" );
            list.add( "2" );
            list.add( "3" );
            list.add( "4" );
            list.add( "5" );
            list.add( "6" );
            list.add( "7" );
            list.add( "8" );
            list.add( "9" );
            list.add( "10" );
            list.add( "11" );
            list.add( "12" );
            list.add( "13" );
            list.add( "14" );
            list.add( "15" );
            list.add( "16" );
            list.add( "17" );
            list.add( "18" );
            list.add( "19" );
            list.add( "20" );

            int i = 0;
            Iterator<String> it = list.iterator();
            while ( it.hasNext() ) {
                String str = it.next();

                if ( i % 2 == 0 ) {
                    System.out.println( i + "===" + str );
                    it.remove();
                }
                i++;
            }

            for ( String str : list ) {
                System.out.println( str );
            }

    }
}
