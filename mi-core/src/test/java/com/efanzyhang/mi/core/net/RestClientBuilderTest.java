package com.efanzyhang.mi.core.net;

import com.efanzyhang.mi.core.net.callback.ISuccess;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.core.net
 * 文件名：RestClientBuilderTest
 * 创建者：efan.zyhang
 * 创建时间：2018/8/10 15:44
 * 描述： TODO
 */
public class RestClientBuilderTest {
    RestClientBuilder restClientBuilder = new RestClientBuilder();

    @Test
    public void url() {
        RestClientBuilder builder = restClientBuilder.Url("sdsa");
        System.out.println(builder.toString());
        builder = restClientBuilder.params("asd","asda");
        System.out.println(builder.toString());

    }
}