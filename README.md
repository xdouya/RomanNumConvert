Documentation
--------------------------------
1、项目介绍
	实现在银河网上时，罗马数字和单位的自动转换

2、实现
	代码实现主要由合法检测、字符串解析、以及罗马数字转阿拉伯数字3部分组成；本程序中，对于单位单词的大小写不敏感，具有一定的鲁棒性；
	
	test类为一个测试类，通过读取文本文档testParseRomanData.txt来实现自动化测试；	

	类:
		Covert：转换类，实现罗马数字与阿拉伯数字之间的转换
			getRomanNumSum函数：采用递归的方式实现罗马数字与阿拉数字之间的转换；

		ParseStr：字符串解析类
			parseRomanStr函数：类似一个简单工厂，将字符串分配给下面四个4个函数中有一个解析；
			parseWord2RomanNum函数：解析类似“tegj is L”的条件字符串，该类字符串以一个合法的罗马数字结尾；
			parseWord2ArabicNum函数：解析类似“glob glob Silver is 34 Credits”的条件字符串，该类字符串以“Credits”结尾；
			parseHowMuchQueries函数：解析类似“how much is pish tEgj glob glob ?”的询问字符串，该类字符串以”how much“开头；
			parseHowManyQueries函数：解析类似“how many Credits is glob prok Iron ?”的询问字符串，该类字符串以“how many”开头；

		MyUtil：工具类
			checkIsLegalRomanStr函数：检测是否是一个合法的罗马数字字符串；
			checkWordIsKnown函数：检测单词是否是一个已知的罗马数字；
			checkIsNumStr函数：检测是否为一个有效数字
		test：测试类

		
		
		
	