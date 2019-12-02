package com.hzysofti.saasAdmin.util;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

import java.util.ArrayList;
import java.util.List;

@EnableTransactionManagement
@Configuration
public class MyBatisPlusConfiguration {

	@Autowired
	private PreTenantHandler myTenantHandler;

	@Bean
	public ISqlInjector sqlInjector() {
		return new LogicSqlInjector();
	}

	/**
	 * 分页插件
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor page = new PaginationInterceptor();
		page.setDialectType("mysql");
		return page;
	}

	/**
	 * 乐观锁插件
	 * @return
	 */
	@Bean
	public OptimisticLockerInterceptor optimisticLockerInterceptor() {
		return new OptimisticLockerInterceptor();
	}

//	@Bean
//	public PaginationInterceptor paginationInterceptor() {
//		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
//		// SQL解析处理拦截：增加租户处理回调。
//		List<ISqlParser> sqlParserList = new ArrayList<>();
//		// 攻击 SQL 阻断解析器、加入解析链
//		sqlParserList.add(new BlockAttackSqlParser());
//		// 多租户拦截
//		TenantSqlParser tenantSqlParser = new TenantSqlParser();
//		tenantSqlParser.setTenantHandler(myTenantHandler);
//		sqlParserList.add(tenantSqlParser);
//		paginationInterceptor.setSqlParserList(sqlParserList);
//		return paginationInterceptor;
//	}
}
